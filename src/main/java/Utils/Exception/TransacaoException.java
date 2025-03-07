/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Exception;


public class TransacaoException extends Exception {

    public TransacaoException(String mensagem) {
        super("Transação invalido: " + mensagem);
    }
}
