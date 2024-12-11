package com.irwi.assessment.application.exceptions;

import com.irwi.assessment.application.dtos.exceptions.ExceptionBasic;
import com.irwi.assessment.application.dtos.exceptions.ExceptionResponse;
import com.irwi.assessment.domain.exception.InvalidCredentialException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidCredentials {
    @ExceptionHandler(InvalidCredentialException.class)
    public ExceptionBasic invalidCredentialException(InvalidCredentialException exception){
        return ExceptionResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .message(exception.getMessage())
                .build();
    }
}
