package br.com.clocktimeapi.clocktimeapi.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTWorkdayProvider {
    
    @Value("${jwt.secret.login.key}")
    private String secretWorkdayKey;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretWorkdayKey);

        try {
            var tokenDecoded = JWT.require(algorithm)
                .build()
                .verify(token);            
            return tokenDecoded;   
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
