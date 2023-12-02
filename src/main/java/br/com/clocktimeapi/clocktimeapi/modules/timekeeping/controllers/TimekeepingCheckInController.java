package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services.TimekeepingCheckInService;

@RestController
@RequestMapping("/workdays")
public class TimekeepingCheckInController {
    
    @Autowired
    private TimekeepingCheckInService workdayCreateService;

    @PostMapping("/check/in")
    public ResponseEntity<Object> checkIn(@RequestHeader("Authorization") String token) {
        try {
            TimekeepingEntity workdayEntity = new TimekeepingEntity();

            // if (workdayEntity.getId() == null && workdayEntity.getUser_id() == null && workdayEntity.getData_entrada() == null && workdayEntity.getData_saida() == null) {
            //     DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO("Você já está logado(a)");
            //     return ResponseEntity.badRequest().body(defaultErrorDTO);
            // }

            var result = this.workdayCreateService.checkIn(token, workdayEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
