package Utils.Exception;

public class CadastroException extends Exception {

    public CadastroException(String mensagem) {
        super("Erro no cadastro de usuario: " + mensagem);
    }
}
