package Models.Usuarios;

import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;

public class Caixa extends Usuario {

    public Caixa(String login, String senha, String nome, String cpf) throws CPFException, CadastroException {
        super(login, senha, nome, cpf, "Caixa");
    }
}