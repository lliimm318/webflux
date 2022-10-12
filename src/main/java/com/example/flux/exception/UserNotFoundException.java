package com.example.flux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "404, user not found")
public class UserNotFoundException extends RuntimeException {
}
