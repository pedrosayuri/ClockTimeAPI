package br.com.clocktimeapi.clocktimeapi.modules.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.services.EmployeeCreateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeCreateController {

    @Autowired
    private EmployeeCreateService employeeCreateService;
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN_2')")
    public ResponseEntity<Object> create(@RequestBody @Valid EmployeeEntity employeeEntity) {
        try {
            var result = this.employeeCreateService.create(employeeEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }

}
