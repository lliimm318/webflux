package com.example.flux.service.user;

import com.example.flux.entity.User;
import com.example.flux.exception.InvalidUserCredentialException;
import com.example.flux.exception.UserAlreadyException;
import com.example.flux.exception.UserNotFoundException;
import com.example.flux.payload.request.SignUpRequest;
import com.example.flux.payload.response.TokenResponse;
import com.example.flux.repository.UserRepository;
import com.example.flux.service.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    @Override
    public Mono createUser(SignUpRequest request) {
        User user = User.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.findById(user.getId())
                .handle((dbUser, sink) -> {
                    sink.error(new UserAlreadyException());
                }).switchIfEmpty(Mono.fromRunnable(() -> {
                    userRepository.save(user).subscribe();
                }));
    }

    @Override
    public Mono login(String id, String password) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.just(new TokenResponse(tokenService.createAccessToken(id)));
                    } else {
                        return Mono.error(new InvalidUserCredentialException());
                    }
                })
                .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }
}
