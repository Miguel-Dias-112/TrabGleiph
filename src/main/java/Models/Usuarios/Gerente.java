/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Models.Usuarios;

import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;

public class Gerente extends Usuario {
    
    public Gerente(String login, String senha, String nome, String cpf) throws CPFException, CadastroException {
        super(login, senha, nome, cpf, "Gerente");
    }
}