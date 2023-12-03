package br.com.clocktimeapi.clocktimeapi.modules.payroll.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollRequestDTO {

    private String employeeUid;
    private LocalDateTime referenceMonth;
    private double remuneration;

}
