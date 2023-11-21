package br.com.clocktimeapi.clocktimeapi.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.users.services.UserCreateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
public class UserCreateController {

    @Autowired
    private UserCreateService userCreateService;
    
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid UserEntity userEntity) {
        try {
            var result = this.userCreateService.create(userEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }

}
