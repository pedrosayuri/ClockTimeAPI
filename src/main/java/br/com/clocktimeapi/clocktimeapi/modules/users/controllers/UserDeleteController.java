package br.com.clocktimeapi.clocktimeapi.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clocktimeapi.clocktimeapi.exceptions.DefaultErrorDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.users.services.UserDeleteService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserDeleteController {
    
    @Autowired
    private UserDeleteService userDeleteService;

    @DeleteMapping("/delete/{uid}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> delete(@PathVariable String uid, UserEntity userEntity, HttpServletRequest request) {
        try {
            var userUid = request.getAttribute("user_uid");

            if (!userUid.equals(userEntity.getUid()) || !userUid.equals(uid)) {
                DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO("Você não tem permissão para deletar este usuário");
                return ResponseEntity.badRequest().body(defaultErrorDTO);
            }

            this.userDeleteService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            DefaultErrorDTO defaultErrorDTO = new DefaultErrorDTO(e.getMessage());
            return ResponseEntity.badRequest().body(defaultErrorDTO);
        }
    }
}
