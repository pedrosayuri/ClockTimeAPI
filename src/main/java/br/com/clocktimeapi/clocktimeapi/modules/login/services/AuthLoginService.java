package br.com.clocktimeapi.clocktimeapi.modules.login.services;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.clocktimeapi.clocktimeapi.modules.login.dto.AuthLoginRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.AuthLoginResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.user.repositories.UserRepository;

@Service
public class AuthLoginService {

    @Value("${jwt.secret.login.key}")
    private String secretUserKey;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder uidEncoder;
    
    public AuthLoginResponseDTO createJWTTokenLogin(AuthLoginRequestDTO authLoginRequestDTO) throws AuthenticationException {
        var login = this.userRepository.findById(authLoginRequestDTO.user_id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var userJob = login.getEmployee().getJob();

        var roles = "";

        if (userJob.getId() == 1) {
            roles = "ADMIN_1";
        } else if (userJob.getId() == 2) {
            roles = "ADMIN_2";
        }

        var idMatches = login.getId().equals(authLoginRequestDTO.user_id());
        
        if (!idMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretUserKey);
        
        var expiresIn = Instant.now().plus(Duration.ofHours(24));
        var token = JWT.create()
                .withIssuer("auth0")
                .withClaim("user_id", login.getId())
                .withIssuedAt(Instant.now())
                .withClaim("roles", roles)
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authLoginResponseDTO = AuthLoginResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        

        return authLoginResponseDTO;
    }

}
