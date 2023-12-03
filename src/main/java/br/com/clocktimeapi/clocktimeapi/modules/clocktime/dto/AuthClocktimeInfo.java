package br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthClocktimeInfo {
    
    private String userUid;
    private String roles;

}