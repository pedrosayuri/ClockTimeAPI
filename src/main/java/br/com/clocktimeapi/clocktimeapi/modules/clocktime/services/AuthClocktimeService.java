package br.com.clocktimeapi.clocktimeapi.modules.clocktime.services;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    EmployeeRepository employeeRepository;
    
    public AuthClocktimeResponseDTO createJWTTokenLogin(AuthClocktimeRequestDTO authClocktimeRequestDTO) throws AuthenticationException {
        var login = this.employeeRepository.findByUid(authClocktimeRequestDTO.uid())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var userJob = login.getJob();

        var roles = "";

        if (userJob.getId() == 1) {
            roles = "ADMIN_1";
        } else if (userJob.getId() == 2) {
            roles = "ADMIN_2";
        } else if (userJob.getId() == 3) {
            roles = "WORKER_1";
        } else if (userJob.getId() == 4) {
            roles = "WORKER_2";
        }
            
        var uidMatches = login.getUid().equals(authClocktimeRequestDTO.uid());
        
        if (!uidMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretUserKey);
        
        var expiresIn = Instant.now().plus(Duration.ofHours(24));
        var token = JWT.create()
                .withIssuer("auth0")
                .withSubject(login.getId().toString())
                .withClaim("uid", login.getUid())
                .withClaim("roles", roles)
                .withIssuedAt(Instant.now())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authClocktimeResponseDTO = AuthClocktimeResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authClocktimeResponseDTO;
    }

}
