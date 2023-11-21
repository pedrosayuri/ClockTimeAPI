package br.com.clocktimeapi.clocktimeapi.modules.login.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.LoginRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.LoginResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.login.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
 
    @Autowired
    private LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequestDTO) throws AuthenticationException {
        try {
            LoginResponseDTO loginResponse = loginService.login(loginRequestDTO);
            return ResponseEntity.ok().body(loginResponse);
        } catch (UserNotFoundException e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }

}
