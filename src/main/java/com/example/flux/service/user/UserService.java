package com.example.flux.service.user;

import com.example.flux.payload.request.SignUpRequest;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono createUser(SignUpRequest request);

    Mono login(String id, String password);

}
