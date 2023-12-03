package br.com.clocktimeapi.clocktimeapi.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserInfo {
    
    private String userId;
    private String roles;

}
