package br.ufjf.trabfinal.exception;

public class LoginException extends Exception {

    public LoginException() {
        super("Login ou senha incorretos!");
    }
}
