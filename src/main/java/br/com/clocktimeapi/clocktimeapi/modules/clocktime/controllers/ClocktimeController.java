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
@RequestMapping("/login")
public class ClocktimeController {
 
    @Autowired
    private ClocktimeService loginService;

    @PostMapping("/")
    public ResponseEntity<Object> login(@RequestBody ClocktimeRequestDTO loginRequestDTO) throws AuthenticationException {
        try {
            ClocktimeResponseDTO loginResponse = loginService.login(loginRequestDTO);
            return ResponseEntity.ok().body(loginResponse);
        } catch (UserNotFoundException e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }

}
