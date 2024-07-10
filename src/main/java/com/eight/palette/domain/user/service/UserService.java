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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public UserResponseDto signup(UserRequestDto requestDto) {

        if(isUserExist(requestDto.getUsername())){
            throw new BadRequestException("동일한 회원이 존재 합니다.");
        }

        final String password = passwordEncoder.encode(requestDto.getPassword());

        final User user = User.builder()
                .username(requestDto.getUsername())
                .password(password)
                .role(UserRoleEnum.USER)
                .build();

        userRepository.save(user);

        return new UserResponseDto(user);

    }

    public LoginResponseDto login(LoginRequestDto requestDto) {

        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(()->new RuntimeException("User not found"));

        String token = jwtProvider.createAccessToken(user.getUsername());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return new LoginResponseDto(token, user.getUsername());

    }

    public Boolean isUserExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            return false;
        }
        return true;
    }

}
