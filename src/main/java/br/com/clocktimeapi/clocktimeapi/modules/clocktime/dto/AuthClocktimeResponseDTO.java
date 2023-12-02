package br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthClocktimeResponseDTO {
    
    private String access_token;
    private Long expires_in;

}
