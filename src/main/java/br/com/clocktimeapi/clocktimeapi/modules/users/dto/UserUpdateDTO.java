package br.com.clocktimeapi.clocktimeapi.modules.users.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    
    private String nome;
    private String email;
    private String uid;

}
