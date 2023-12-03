package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.dto;

import java.time.LocalDateTime;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimekeepingReadDTO {
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    private LocalDateTime check_in;

    private LocalDateTime check_out;

}
