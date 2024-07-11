package com.eight.palette.domain.user.service;

import com.eight.palette.domain.user.dto.LoginRequestDto;
import com.eight.palette.domain.user.dto.LoginResponseDto;
import com.eight.palette.domain.user.dto.UserRequestDto;
import com.eight.palette.domain.user.dto.UserResponseDto;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.entity.UserRoleEnum;
import com.eight.palette.domain.user.repository.UserRepository;
import com.eight.palette.global.exception.BadRequestException;
import com.eight.palette.global.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Value("${custom.manage-key}")
    private String customManageKey;

    public UserResponseDto signup(UserRequestDto requestDto) {

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

        return new UserResponseDto(user);

    }

    public LoginResponseDto login(LoginRequestDto requestDto) {

        final User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("해당 유저는 존재하지 않습니다."));

        final String accessToken = jwtProvider.createAccessToken(user.getUsername());
        final String refreshToken = jwtProvider.createRefreshToken(user.getUsername());

        userRepository.setTokenValue(user.getId(), refreshToken);

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BadRequestException("패스워드가 일치하지 않습니다.");
        }

        return new LoginResponseDto(accessToken, refreshToken, user.getUsername());

    }

    public void logout(Long id, User user) {

        if (!user.getId().equals(id)) {
            throw new BadRequestException("다른 유저가 시도했습니다.");
        }

        userRepository.setTokenValue(user.getId(), "");

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
