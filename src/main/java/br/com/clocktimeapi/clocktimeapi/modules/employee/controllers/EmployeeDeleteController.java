package br.com.clocktimeapi.clocktimeapi.modules.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.services.EmployeeDeleteService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeDeleteController {
    
    @Autowired
    private EmployeeDeleteService userDeleteService;

    @DeleteMapping("/delete/{uid}")
    @PreAuthorize("hasRole('ADMIN_2')")
    public ResponseEntity<Object> delete(@PathVariable String uid, EmployeeEntity userEntity, HttpServletRequest request) {
        try {
            this.userDeleteService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
