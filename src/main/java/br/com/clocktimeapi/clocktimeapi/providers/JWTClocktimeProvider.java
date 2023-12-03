package br.com.clocktimeapi.clocktimeapi.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTClocktimeProvider {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String USER_UID_CLAIM = "user_uid";
    private static final String ROLES_CLAIM = "roles";

    @Value("${jwt.secret.login.key}")
    private String secretClocktimeKey;

    public DecodedJWT validateClocktimeToken(String clocktimeToken) {
        
        if (clocktimeToken == null || clocktimeToken.isEmpty()) {
            return null;
        }

        clocktimeToken = clocktimeToken.replace(BEARER_PREFIX, "");

        Algorithm algorithm = Algorithm.HMAC256(secretClocktimeKey);

        try {
            DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(clocktimeToken);           
            
            return decodedJWT;
        } catch (com.auth0.jwt.exceptions.TokenExpiredException expiredException) {
            System.err.println("Token expirado: " + expiredException.getMessage());
            return null;
        } catch (com.auth0.jwt.exceptions.SignatureVerificationException signatureException) {
            System.err.println("Falha na verificação da assinatura: " + signatureException.getMessage());
            return null;
        } catch (JWTVerificationException exception) {
            System.err.println("Erro na verificação do token: " + exception.getMessage());
            return null;
        }
    }
    
    public String getUserUid(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(USER_UID_CLAIM).asString();
    }

    public String getRoles(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(ROLES_CLAIM).asString();
    }
}
