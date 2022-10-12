package com.example.flux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "401 unauthorized")
public class TokenExpiredException extends RuntimeException {
}
