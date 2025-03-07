/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Exception;

public class CadastroException extends Exception {

    public CadastroException(String mensagem) {
        super("Erro no cadastro de usuario: " + mensagem);
    }
}
