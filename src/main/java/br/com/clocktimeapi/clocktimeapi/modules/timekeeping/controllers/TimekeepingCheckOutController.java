package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services.TimekeepingCheckOutService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/timekeeping")
public class TimekeepingCheckOutController {

    @Autowired
    private TimekeepingCheckOutService workdayCheckOutService;

    @PostMapping("/check/out")
    @PreAuthorize("hasRole('ADMIN_1') or hasRole('ADMIN_2') or hasRole('WORKER_1') or hasRole('WORK2')")
    public ResponseEntity<Object> checkOut(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        try {
            var result = this.workdayCheckOutService.checkOut(token);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
