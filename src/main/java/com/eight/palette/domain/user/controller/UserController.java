package com.eight.palette.domain.user.controller;

import com.eight.palette.domain.user.dto.LoginRequestDto;
import com.eight.palette.domain.user.dto.LoginResponseDto;
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

        return ResponseEntity.ok(new MessageResponse(201, "회원가입에 성공 하셨습니다."));

    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {

        return ResponseEntity.ok
                (new DataResponse<>(200, "로그인에 성공 하셨습니다.", userService.login(requestDto)));

    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<MessageResponse> logout(@PathVariable Long id,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        userService.logout(id,userDetails.getUser());

        return ResponseEntity.ok(new MessageResponse(204, "로그아웃에 성공 하셨습니다."));

    }

}
