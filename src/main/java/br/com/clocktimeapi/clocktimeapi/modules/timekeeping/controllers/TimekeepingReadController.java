package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services.TimekeepingReadService;

@RestController
@RequestMapping("/workdays")
public class TimekeepingReadController {

    @Autowired
    private TimekeepingReadService workdayReadService;
    
    @GetMapping("/read/{uid}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> read(@PathVariable Integer user_id) {
        try {
            var result = this.workdayReadService.read(user_id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
