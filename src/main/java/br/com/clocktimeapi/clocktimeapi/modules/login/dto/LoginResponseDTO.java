package br.com.clocktimeapi.clocktimeapi.modules.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String access_token;
}
