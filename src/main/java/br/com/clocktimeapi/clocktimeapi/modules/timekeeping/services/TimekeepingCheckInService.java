package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories.TimekeepingRepository;
import br.com.clocktimeapi.clocktimeapi.providers.JWTClocktimeProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TimekeepingCheckInService {

    @Autowired
    private TimekeepingRepository timekeepingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JWTClocktimeProvider jwtClocktimeProvider;

    public TimekeepingEntity checkIn(String token, TimekeepingEntity timekeepingEntity) {
        DecodedJWT decodedJWT = (DecodedJWT) jwtClocktimeProvider.validateClocktimeToken(token);

        LocalDateTime dataEntrada = LocalDateTime.ofInstant(
            decodedJWT.getIssuedAt().toInstant(), ZoneId.systemDefault());

        EmployeeEntity employeeEntity = employeeRepository.findByUid(decodedJWT.getClaim("uid").asString())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (timekeepingRepository.findByEmployee_id(employeeEntity.getId()).isPresent()) {
            throw new UserFoundException();
        }

        timekeepingEntity.setEmployee(employeeEntity);
        timekeepingEntity.setCheck_in(dataEntrada);

        return timekeepingRepository.save(timekeepingEntity);
    }
}
