package Utils.Exception;

public class EditarException extends Exception {

    public EditarException(String mensagem) {
        super("Erro na edicao de usuario: " + mensagem);
    }
}
