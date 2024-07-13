package com.eight.palette.domain.user.service;

import com.eight.palette.domain.user.dto.LoginRequestDto;
import com.eight.palette.domain.user.dto.LoginResponseDto;
import com.eight.palette.domain.user.dto.UserRequestDto;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.entity.UserRoleEnum;
import com.eight.palette.domain.user.repository.UserRepository;
import com.eight.palette.global.exception.BadRequestException;
import com.eight.palette.global.jwt.JwtProvider;
import com.eight.palette.global.redis.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisService redisService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, RedisService redisService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.redisService = redisService;
    }

    @Value("${custom.manage-key}")
    private String customManageKey;

    public void signup(UserRequestDto requestDto) {

        if (isUserExist(requestDto.getUsername())) {
            throw new BadRequestException("동일한 회원이 존재 합니다.");
        }

        UserRoleEnum userRole = getUserRole(requestDto.getManagerKey());

        final String password = passwordEncoder.encode(requestDto.getPassword());

        final User user = User.builder()
                .username(requestDto.getUsername())
                .password(password)
                .role(userRole)
                .build();

        userRepository.save(user);

    }

    public LoginResponseDto login(LoginRequestDto requestDto) {

        final User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new BadRequestException("해당 유저는 존재하지 않습니다."));

        final String accessToken = jwtProvider.createAccessToken(user.getUsername(),user.getRole());
        final String refreshToken = jwtProvider.createRefreshToken(user.getUsername());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BadRequestException("패스워드가 일치하지 않습니다.");
        }

        LoginResponseDto responseDto = new LoginResponseDto(accessToken, refreshToken, user.getUsername());

        redisService.setValue(user.getUsername(),responseDto);

        return responseDto;

    }

    public LoginResponseDto tokenRefresh(String username) {

        final User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new BadRequestException("해당유저가 없습니다."));

        final String accessToken = jwtProvider.createAccessToken(user.getUsername(),user.getRole());
        final String refreshTokenValue = jwtProvider.createRefreshToken(username);
        final LoginResponseDto responseDto = new LoginResponseDto(accessToken, refreshTokenValue, user.getUsername());

        redisService.setValue(user.getUsername(),responseDto);

        return responseDto;

    }

    public void logout(Long id, User user) {

        if (!user.getId().equals(id)) {
            throw new BadRequestException("다른 유저가 시도했습니다.");
        }

        redisService.deleteValue(user.getUsername());

    }

    public Boolean isUserExist(String username) {

        final Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent();

    }

    public UserRoleEnum getUserRole(String secretKey) {

        if (secretKey != null) {

            if (secretKey.equals(customManageKey)) {
                return UserRoleEnum.MANAGER;
            } else {
                throw new BadRequestException("유효하지 않은 매니저 키 입니다.");
            }

        }

        return UserRoleEnum.USER;

    }

}
