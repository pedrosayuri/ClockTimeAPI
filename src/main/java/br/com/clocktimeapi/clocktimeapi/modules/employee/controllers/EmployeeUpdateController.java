package br.com.clocktimeapi.clocktimeapi.modules.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.dto.EmployeeUpdateDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.services.EmployeeUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeUpdateController {

    @Autowired
    private EmployeeUpdateService employeeUpdateService;

    @PutMapping("/update/{uid}")
    @PreAuthorize("hasRole('ADMIN_2')")
    public ResponseEntity<Object> update(@PathVariable String uid, @Valid @RequestBody EmployeeUpdateDTO employeeUpdateDTO, HttpServletRequest request) {
        try { 
            var userEntity = EmployeeEntity.builder()
                .name(employeeUpdateDTO.getNome())
                .email(employeeUpdateDTO.getEmail())
                .uid(employeeUpdateDTO.getUid())
                .build();

            var result = this.employeeUpdateService.update(uid, userEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}           

