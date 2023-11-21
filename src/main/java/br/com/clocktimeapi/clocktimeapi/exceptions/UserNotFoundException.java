package br.com.clocktimeapi.clocktimeapi.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String uid) {
        super("Usuário não encontrado com o UID: " + uid);
    }
}