package com.example.flux.service.token;

import com.example.flux.exception.TokenExpiredException;
import com.example.flux.exception.TokenForbiddenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${auth.jwt.exp.access}")
    private Long accessExp;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String getIdentity(String jwt) {
        try {
            Claims parsed = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJwt(jwt)
                    .getBody();
            if (parsed.getExpiration().before(Date.from(Instant.now()))) {
                throw new TokenExpiredException();
            }
            return parsed.getSubject();
        } catch (SignatureException e) {
            throw new TokenForbiddenException(e.getMessage());
        }
    }

    @Override
    public String generateToken(String identity, Long exp) {
        return Jwts.builder()
                .setSubject(identity)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String createAccessToken(String identity) {
        return generateToken(identity, accessExp);
    }

    @Override
    public String createRefreshToken(String identity) {
        return generateToken(identity, refreshExp);
    }

}
