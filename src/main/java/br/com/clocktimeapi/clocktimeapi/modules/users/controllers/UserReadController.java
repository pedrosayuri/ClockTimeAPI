package br.com.clocktimeapi.clocktimeapi.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.services.UserReadService;

@RestController
@RequestMapping("/users")
public class UserReadController {
    
    @Autowired
    private UserReadService userReadService;

    @GetMapping("/read/{uid}")
    public ResponseEntity<Object> read(@PathVariable String uid) {
        try {
            var result = this.userReadService.read(uid);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }

}
