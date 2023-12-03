package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories.TimekeepingRepository;
import br.com.clocktimeapi.clocktimeapi.providers.JWTTimekeepingProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class TimekeepingCheckOutService {

    @Autowired
    private TimekeepingRepository workdayRepository;

    @Autowired
    EmployeeRepository userRepository;

    @Autowired
    private JWTTimekeepingProvider jwtWorkdayProvider;

    public TimekeepingEntity checkOut(String token, TimekeepingEntity workdayEntity) {

        DecodedJWT decodedJWT = jwtWorkdayProvider.validateToken(token);

        String userIdFromToken = decodedJWT.getSubject();
        LocalDateTime dataSaida = LocalDateTime.ofInstant(
                decodedJWT.getIssuedAt().toInstant(), ZoneId.systemDefault());

        System.out.println("userIdFromToken: " + UUID.fromString(userIdFromToken));

        // Recupere o objeto EmployeeEntity do repositório usando o ID do usuário do token
        EmployeeEntity employeeEntity = userRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Recupere o objeto TimekeepingEntity do repositório usando o ID do funcionário
        TimekeepingEntity workday = workdayRepository.findByEmployee_id(employeeEntity.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        workday.setCheck_out(dataSaida);

        // Atribua o objeto EmployeeEntity ao campo employee
        workdayEntity.setEmployee(employeeEntity);
        workdayEntity.setCheck_out(dataSaida);

        // workdayRepository.findById(UUID.fromString(userIdFromToken)).ifPresent((workday) -> {
        //     throw new UserFoundException();
        // });

        workdayEntity.setCheck_out(dataSaida);

        return workdayRepository.save(workdayEntity);

    }
}
