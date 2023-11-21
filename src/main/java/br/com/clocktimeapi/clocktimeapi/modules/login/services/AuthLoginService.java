package br.com.clocktimeapi.clocktimeapi.modules.login.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.clocktimeapi.clocktimeapi.modules.login.dto.AuthLoginRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.AuthLoginResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.repositories.UserRepository;

@Service
public class AuthLoginService {

    @Value("${jwt.secret.login.key}")
    private String secretUserKey;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder uidEncoder;
    
    public AuthLoginResponseDTO createJWTTokenLogin(AuthLoginRequestDTO authLoginRequestDTO) throws AuthenticationException {
        var login = this.userRepository.findByUid(authLoginRequestDTO.uid())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var uidMatches = login.getUid().equals(authLoginRequestDTO.uid());
        
        if (!uidMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretUserKey);
        
        var expiresIn = Instant.now().plus(Duration.ofHours(6));
        var token = JWT.create()
                .withIssuer("auth0")
                .withSubject(login.getId().toString())
                .withClaim("uid", login.getUid())
                .withClaim("email", login.getEmail())
                .withClaim("nome", login.getNome())
                .withClaim("roles", Arrays.asList("USER"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authLoginResponseDTO = AuthLoginResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authLoginResponseDTO;
    }

}
