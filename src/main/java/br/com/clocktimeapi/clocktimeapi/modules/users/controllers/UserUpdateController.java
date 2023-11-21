package br.com.clocktimeapi.clocktimeapi.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.dto.UserUpdateDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.users.services.UserUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserUpdateController {

    @Autowired
    private UserUpdateService userUpdateService;

    @PutMapping("/update/{uid}")
    public ResponseEntity<Object> update(@PathVariable String uid, @Valid @RequestBody UserUpdateDTO userUpdateDTO, HttpServletRequest request) {
        try { 
            var userUid = request.getAttribute("user_uid");

            var userEntity = UserEntity.builder()
                .nome(userUpdateDTO.getNome())
                .email(userUpdateDTO.getEmail())
                .uid(userUpdateDTO.getUid())
                .build();

            if (!userUid.equals(userEntity.getUid()) || !userUid.equals(uid)) {
                DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO("Você não tem permissão para atualizar este usuário");
                return ResponseEntity.badRequest().body(defaultErrorDTO);
            }

            var result = this.userUpdateService.update(uid, userEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}           

