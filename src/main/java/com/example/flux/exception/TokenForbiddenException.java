package com.example.flux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "403 forbidden")
public class TokenForbiddenException extends RuntimeException {

    public TokenForbiddenException(String message) {
        super(message);
    }

}
