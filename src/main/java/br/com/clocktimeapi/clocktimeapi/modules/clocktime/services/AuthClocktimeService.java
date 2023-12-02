package br.com.clocktimeapi.clocktimeapi.modules.clocktime.services;

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

import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.AuthClocktimeRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.AuthClocktimeResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;

@Service
public class AuthClocktimeService {

    @Value("${jwt.secret.login.key}")
    private String secretUserKey;

    @Autowired
    EmployeeRepository userRepository;

    @Autowired
    PasswordEncoder uidEncoder;
    
    public AuthClocktimeResponseDTO createJWTTokenLogin(AuthClocktimeRequestDTO authLoginRequestDTO) throws AuthenticationException {
        var login = this.userRepository.findByUid(authLoginRequestDTO.uid())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var uidMatches = login.getUid().equals(authLoginRequestDTO.uid());
        
        if (!uidMatches)
            throw new AuthenticationException();

        // login.setAtivo(!login.isAtivo());
        // userRepository.save(login);

        // login.setAtivo(true);
        // userRepository.save(login);

        Algorithm algorithm = Algorithm.HMAC256(secretUserKey);
        
        var expiresIn = Instant.now().plus(Duration.ofHours(24));
        var token = JWT.create()
                .withIssuer("auth0")
                .withSubject(login.getId().toString())
                .withClaim("uid", login.getUid())
                .withIssuedAt(Instant.now())
                .withClaim("roles", Arrays.asList("USER"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authLoginResponseDTO = AuthClocktimeResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        

        return authLoginResponseDTO;
    }

}
