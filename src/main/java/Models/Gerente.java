package Models;

import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;

public class Gerente extends Usuario {
    
    public Gerente(String login, String senha, String nome, String cpf) throws CPFException, CadastroException {
        super(login, senha, nome, cpf, "Gerente");
    }
}