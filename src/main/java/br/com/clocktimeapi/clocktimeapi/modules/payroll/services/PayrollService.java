package br.com.clocktimeapi.clocktimeapi.modules.payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;
import br.com.clocktimeapi.clocktimeapi.modules.payroll.entities.PayrollEntity;
import br.com.clocktimeapi.clocktimeapi.modules.payroll.repositories.PayrollRepository;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories.TimekeepingRepository;
import br.com.clocktimeapi.clocktimeapi.providers.JWTClocktimeProvider;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    private TimekeepingRepository timekeepingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private JWTClocktimeProvider jwtClocktimeProvider;

    public PayrollEntity calculateMonthlyRemuneration(String token, PayrollEntity payrollEntity) {
        DecodedJWT decodedJWT = jwtClocktimeProvider.validateClocktimeToken(token);

        EmployeeEntity employeeEntity = employeeRepository.findByUid(decodedJWT.getClaim("uid").asString())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<TimekeepingEntity> timekeepingEntityOptional = timekeepingRepository.findByEmployee_id(employeeEntity.getId());
        if (timekeepingEntityOptional.isEmpty()) {
            throw new RuntimeException("Não há registros de ponto para esse usuário");
        }

        TimekeepingEntity timekeepingEntity = timekeepingEntityOptional.get();
        LocalDateTime checkIn = timekeepingEntity.getCheck_in();
        LocalDateTime checkOut = timekeepingEntity.getCheck_out();

        long hoursWorked = java.time.Duration.between(checkIn, checkOut).toHours();

        if (hoursWorked <= 160) {
            payrollEntity.setRemuneration(hoursWorked * 10);
        } else {
            payrollEntity.setRemuneration(hoursWorked * 15);
        }

        payrollEntity.setReference_month(YearMonth.from(checkIn).atEndOfMonth());

        payrollEntity.setEmployee(employeeEntity);

        return payrollRepository.save(payrollEntity);
    }


}
