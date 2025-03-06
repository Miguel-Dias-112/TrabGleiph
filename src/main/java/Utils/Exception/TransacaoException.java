package Utils.Exception;


public class TransacaoException extends Exception {

    public TransacaoException(String mensagem) {
        super("Transação invalido: " + mensagem);
    }
}
