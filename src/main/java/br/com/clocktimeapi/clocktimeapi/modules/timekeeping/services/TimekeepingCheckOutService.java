package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories.TimekeepingRepository;
import br.com.clocktimeapi.clocktimeapi.providers.JWTClocktimeProvider;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TimekeepingCheckOutService {

    @Autowired
    private TimekeepingRepository timekeepingRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private JWTClocktimeProvider jwtClocktimeProvider;

    public TimekeepingEntity checkOut(String token) {

        DecodedJWT decodedJWT = (DecodedJWT) jwtClocktimeProvider.validateClocktimeToken(token);

        LocalDateTime checkOut = LocalDateTime.ofInstant(new java.util.Date().toInstant(), ZoneId.systemDefault());

        EmployeeEntity employeeEntity = employeeRepository.findByUid(decodedJWT.getClaim("uid").asString())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        TimekeepingEntity timekeepingEntity = timekeepingRepository.findByEmployee_id(employeeEntity.getId())
                .orElseThrow(() -> new RuntimeException("Entrada de horário não encontrada para este usuário"));

        if (timekeepingEntity.getCheck_out() != null) {
            throw new RuntimeException("Check-out já registrado para este usuário");
        }

        timekeepingEntity.setCheck_out(checkOut);
        
        Duration duration = Duration.between(timekeepingEntity.getCheck_in(), checkOut);
        long minutes = duration.toMinutes();

        double hours = minutes / 60.0;
        timekeepingEntity.setWork_hours(hours);
        return timekeepingRepository.save(timekeepingEntity);
    }
}

