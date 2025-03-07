/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Exception;

public class EditarException extends Exception {

    public EditarException(String mensagem) {
        super("Erro na edicao de usuario: " + mensagem);
    }
}
