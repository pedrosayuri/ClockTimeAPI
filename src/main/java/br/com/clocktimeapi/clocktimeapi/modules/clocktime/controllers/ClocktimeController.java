package br.com.clocktimeapi.clocktimeapi.modules.clocktime.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.ClocktimeRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.ClocktimeResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.services.ClocktimeService;

@RestController
@RequestMapping("/clocktime")
public class ClocktimeController {
 
    @Autowired
    private ClocktimeService clocktimeService;

    @PostMapping("/")
    public ResponseEntity<Object> clocktime(@RequestBody ClocktimeRequestDTO clocktimeRequestDTO) throws AuthenticationException {
        try {
            ClocktimeResponseDTO CcocktimeResponseDTO = clocktimeService.login(clocktimeRequestDTO);
            return ResponseEntity.ok().body(CcocktimeResponseDTO);
        } catch (UserNotFoundException e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }

}
