package Models;

import Exception.CPFException;

public class Cliente extends Usuario {
    private Conta conta; 

    public Cliente(String login, String senha, String nome, String cpf) throws CPFException  {
        super(login, senha, nome, cpf, "Cliente");
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
}