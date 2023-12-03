package br.com.clocktimeapi.clocktimeapi.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.clocktimeapi.clocktimeapi.modules.user.dto.AuthUserInfo;

@Service
public class JWTLoginProvider {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String USER_ID_CLAIM = "user_id";
    private static final String ROLES_CLAIM = "roles";

    @Value("${jwt.secret.login.key}")
    private String secretLoginKey;

    public AuthUserInfo validLoginToken(String loginToken) {
        if (loginToken == null || loginToken.isEmpty()) {
            return null;
        }

        loginToken = loginToken.replace(BEARER_PREFIX, "");

        Algorithm algorithm = Algorithm.HMAC256(secretLoginKey);

        try {
            DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(loginToken);

            String userId = decodedJWT.getClaim(USER_ID_CLAIM).asString();
            String roles = decodedJWT.getClaim(ROLES_CLAIM).asString();

            return new AuthUserInfo(userId, roles);
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
}
