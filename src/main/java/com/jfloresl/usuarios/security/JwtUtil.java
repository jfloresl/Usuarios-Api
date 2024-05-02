package com.jfloresl.usuarios.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static final String SECRET_KEY="BGrzDcAja4";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String email){
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.SECONDS.toMillis(3600)))
                .sign(ALGORITHM);

    }

    public boolean isValid(String jwt){
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        }
        catch (JWTVerificationException e){
            return false;
        }

    }

    public String getEmail (String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
