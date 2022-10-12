package com.example.flux.controller;

import com.example.flux.payload.request.SignUpRequest;
import com.example.flux.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono createUser(@RequestBody SignUpRequest signUpRequest) {
        return userService.createUser(signUpRequest);
    }

    @PostMapping("/login")
    public Mono login(@RequestBody SignUpRequest signUpRequest) {
        return userService.login(signUpRequest.getId(), signUpRequest.getPassword());
    }

}
