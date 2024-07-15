package com.eight.palette.domain.user.controller;

import com.eight.palette.domain.user.dto.LoginRequestDto;
import com.eight.palette.domain.user.dto.LoginResponseDto;
import com.eight.palette.domain.user.dto.RefreshTokenDto;
import com.eight.palette.domain.user.dto.UserRequestDto;
import com.eight.palette.domain.user.entity.UserDetailsImpl;
import com.eight.palette.domain.user.service.UserService;
import com.eight.palette.global.dto.DataResponse;
import com.eight.palette.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> singup(@Valid @RequestBody UserRequestDto requestDto) {

        userService.signup(requestDto);

        return ResponseEntity.ok(new MessageResponse(201, "회원 가입 성공 \uD83C\uDF20"));

    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {

        return ResponseEntity.ok
                (new DataResponse<>(200, "로그인 성공 \uD83C\uDF89", userService.login(requestDto)));

    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        userService.logout(userDetails.getUser());

        return ResponseEntity.ok(new MessageResponse(204, "로그아웃 성공 \uD83C\uDF89"));

    }

    @PostMapping("/token/refresh")
    public ResponseEntity<DataResponse<LoginResponseDto>> tokenRefresh (@AuthenticationPrincipal UserDetailsImpl userDetails){

        LoginResponseDto responseDto = userService.tokenRefresh(userDetails.getUser().getUsername());

        return ResponseEntity.ok(new DataResponse<>
                (200, "재 로그인 성공 \uD83C\uDF89",responseDto));

    }

}
