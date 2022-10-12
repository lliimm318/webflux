package com.example.flux.service.token;

public interface TokenService {
    String getIdentity(String jwt);

    String generateToken(String identity, Long expSeconds);


    String createAccessToken(String identity);

    String createRefreshToken(String identity);
}
