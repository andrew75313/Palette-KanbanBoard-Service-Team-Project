package com.eight.palette.global.exception;


import com.eight.palette.global.dto.SecurityResponse;
import com.eight.palette.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final SecurityResponse securityResponse;
    private final JwtProvider jwtProvider;

    public CustomAuthenticationEntryPoint(SecurityResponse securityResponse, JwtProvider jwtProvider) {
        this.securityResponse = securityResponse;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {

            token = token.substring(7); // Remove "Bearer " prefix
            String role = jwtProvider.getRoleFromToken(token);

            if ("USER".equals(role)) {
                securityResponse.sendResponse(response, HttpStatus.FORBIDDEN, "일반 유저는 접근이 제한된 요청 입니다.");
                return;
            }

        }

        securityResponse.sendResponse(response, HttpStatus.UNAUTHORIZED, "로그인 후 이용해 주세요.");

    }
}