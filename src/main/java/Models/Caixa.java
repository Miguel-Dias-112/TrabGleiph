package Models;

import Exception.CPFException;

public class Caixa extends Usuario {

    public Caixa(String login, String senha, String nome, String cpf) throws CPFException {
        super(login, senha, nome, cpf);
    }
}