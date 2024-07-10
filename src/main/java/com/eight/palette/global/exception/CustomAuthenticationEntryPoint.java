package com.eight.palette.global.exception;


import com.eight.palette.global.dto.SecurityResponse;
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

    public CustomAuthenticationEntryPoint(SecurityResponse securityResponse) {
        this.securityResponse = securityResponse;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        securityResponse.sendResponse(response, HttpStatus.UNAUTHORIZED, "로그인 후 이용해 주세요.");
    }

}