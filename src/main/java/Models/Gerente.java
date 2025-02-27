package Models;

import Exception.CPFException;

public class Gerente extends Usuario {
    
    public Gerente(String login, String senha, String nome, String cpf) throws CPFException {
        super(login, senha, nome, cpf, "Gerente");
    }
}