package Exception;

public class CPFException extends Exception {

    public CPFException(String mensagem) {
        super("CPF inválido: " + mensagem);
    }
}
