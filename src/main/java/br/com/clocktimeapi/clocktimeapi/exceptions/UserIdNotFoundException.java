package br.com.clocktimeapi.clocktimeapi.exceptions;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException() {
        super("Usuário não encontrado.");
    }
}