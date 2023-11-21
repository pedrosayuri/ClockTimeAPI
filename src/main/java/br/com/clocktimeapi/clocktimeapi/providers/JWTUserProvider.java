package br.com.clocktimeapi.clocktimeapi.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTUserProvider {

    @Value("${jwt.secret.login.key}")
    private String secretUserKey;
    
    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretUserKey);

        try {
            var subject = JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaim("uid")
                .asString();
            return subject;
        } catch (JWTVerificationException exception) {
            return null;
        }        
    }
}
