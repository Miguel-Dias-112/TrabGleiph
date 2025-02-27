package Utils.Exception;

public class LoginException extends Exception {

    public LoginException() {
        super("Login ou senha incorretos!");
    }
}
