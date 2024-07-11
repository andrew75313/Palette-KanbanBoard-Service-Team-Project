package com.eight.palette.global.exception;

import com.eight.palette.global.dto.MessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j(topic = "Global Exception")
@RestControllerAdvice
@RestController
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonErrorResponse> handleException(final CustomException exception, final HttpServletRequest request) {
        return ResponseEntity.status(exception.getStatusCode()).body(
                CommonErrorResponse.builder()
                        .msg(exception.getMessage())
                        .status(exception.getStatusCode().value())
                        .error(exception.getStatusCode().getReasonPhrase())
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    private ResponseEntity<MessageResponse> createResponseEntity(String message, HttpStatus httpStatusCode) {

        MessageResponse response = new MessageResponse(httpStatusCode.value(), message);

        log.error(message);

        return ResponseEntity.status(httpStatusCode).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageResponse> badRequestException(BadRequestException e) {
        return createResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<MessageResponse> unauthorizedException(UnauthorizedException e) {
        return createResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageResponse> notFoundException(NotFoundException e) {
        return createResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> notValidException(MethodArgumentNotValidException e) {
        return createResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
