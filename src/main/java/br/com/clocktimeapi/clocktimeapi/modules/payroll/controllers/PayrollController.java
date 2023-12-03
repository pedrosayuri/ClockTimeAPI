package br.com.clocktimeapi.clocktimeapi.modules.payroll.controllers;

import br.com.clocktimeapi.clocktimeapi.modules.payroll.entities.PayrollEntity;
import br.com.clocktimeapi.clocktimeapi.modules.payroll.services.PayrollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/calculate")
    @PreAuthorize("hasRole('ADMIN_1') or hasRole('ADMIN_2') or hasRole('WORKER_1') or hasRole('WORK2')")
    public ResponseEntity<Object> calculateMonthlyRemuneration(@RequestHeader("Authorization") String token) {
        try {
            PayrollEntity payrollEntity = new PayrollEntity();

            var result = this.payrollService.calculateMonthlyRemuneration(token, payrollEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
