package br.com.clocktimeapi.clocktimeapi.modules.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginResponseDTO {
    
    private String access_token;
    private Long expires_in;

}
