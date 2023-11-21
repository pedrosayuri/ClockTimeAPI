package br.com.clocktimeapi.clocktimeapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultErrorDTO {

    private String message;

}