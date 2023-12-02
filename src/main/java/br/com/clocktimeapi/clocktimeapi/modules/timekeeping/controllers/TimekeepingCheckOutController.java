package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services.TimekeepingCheckOutService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/workdays")
public class TimekeepingCheckOutController {

    @Autowired
    private TimekeepingCheckOutService workdayCheckOutService;

    @PostMapping("/check/out")
    public ResponseEntity<Object> checkOut(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        try {
            TimekeepingEntity workdayEntity = new TimekeepingEntity();
            
            // Defina o employee_id conforme necessário
            Integer employeeId = 2;

            var workday = TimekeepingEntity.builder()
                .employee(EmployeeEntity.builder().id(employeeId).build())
                .check_in(workdayEntity.getCheck_in())
                .createdAt(workdayEntity.getCreatedAt())
                .build();

            var result = this.workdayCheckOutService.checkOut(token, workday);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
