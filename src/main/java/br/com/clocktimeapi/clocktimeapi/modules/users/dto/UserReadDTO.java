package br.com.clocktimeapi.clocktimeapi.modules.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReadDTO {
    
    private String nome;

    private String email;

}

