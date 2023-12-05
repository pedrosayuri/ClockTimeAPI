package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services.TimekeepingCheckInService;

@RestController
@RequestMapping("/timekeeping")
public class TimekeepingCheckInController {
    
    @Autowired
    private TimekeepingCheckInService timekeepingCheckInService;

    @PostMapping("/check/in")
    @PreAuthorize("hasRole('ADMIN_1') or hasRole('ADMIN_2') or hasRole('WORKER_1') or hasRole('WORKER_2')")
    public ResponseEntity<Object> checkIn(@RequestHeader("Authorization") String token) {
        try {
            TimekeepingEntity timekeepingEntity = new TimekeepingEntity();

            var result = this.timekeepingCheckInService.checkIn(token, timekeepingEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
