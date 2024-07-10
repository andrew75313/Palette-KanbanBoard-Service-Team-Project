package com.eight.palette.domain.user.controller;

import com.eight.palette.domain.user.dto.LoginRequestDto;
import com.eight.palette.domain.user.dto.LoginResponseDto;
import com.eight.palette.domain.user.dto.UserRequestDto;
import com.eight.palette.domain.user.service.UserService;
import com.eight.palette.global.dto.DataResponse;
import com.eight.palette.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.status(200).body(new MessageResponse(200,"회원가입에 성공 하셨습니다."));

    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(new DataResponse<>
                (200,"로그인에 성공 하셨습니다.",userService.login(requestDto)));

    }

}
