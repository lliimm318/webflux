package com.example.flux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "405, user already")
public class UserAlreadyException extends RuntimeException {
}
