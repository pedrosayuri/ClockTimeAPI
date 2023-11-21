package br.com.clocktimeapi.clocktimeapi.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já cadastrado.");
    }
}
