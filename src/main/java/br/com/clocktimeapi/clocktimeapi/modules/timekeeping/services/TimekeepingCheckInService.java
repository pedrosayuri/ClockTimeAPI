package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories.TimekeepingRepository;
import br.com.clocktimeapi.clocktimeapi.providers.JWTWorkdayProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class TimekeepingCheckInService {

    @Autowired
    private TimekeepingRepository workdayRepository;

    @Autowired
    private EmployeeRepository userRepository;

    @Autowired
    private JWTWorkdayProvider jwtWorkdayProvider;

    public TimekeepingEntity checkIn(String token, TimekeepingEntity workdayEntity) {
        DecodedJWT decodedJWT = jwtWorkdayProvider.validateToken(token);
        
        String userIdFromToken = decodedJWT.getSubject();
        LocalDateTime dataEntrada = LocalDateTime.ofInstant(
            decodedJWT.getIssuedAt().toInstant(), ZoneId.systemDefault());

        // Buscar informações do usuário
        EmployeeEntity employeeEntity = userRepository.findById(2)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verificar se já existe um registro para o usuário
        if (workdayRepository.findByEmployee_id(employeeEntity.getId()).isPresent()) {
            throw new UserFoundException();
        }

        // Configurar os detalhes da entrada
        workdayEntity.setEmployee(employeeEntity);
        workdayEntity.setCheck_in(dataEntrada);

        // Salvar o registro de entrada
        return workdayRepository.save(workdayEntity);
    }
}
