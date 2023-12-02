package br.com.clocktimeapi.clocktimeapi.modules.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeReadDTO {
    
    private String nome;

    private String email;

}

