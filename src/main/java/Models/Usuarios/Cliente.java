package Models.Usuarios;

import Models.Bank.Conta;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;

public class Cliente extends Usuario {
    private Conta conta; 
    
    public Cliente(String login, String senha, String nome, String cpf) throws CPFException, CadastroException  {
        super(login, senha, nome, cpf, "Cliente");
        conta = new Conta();
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public double getSaldo() {
        return conta.getSaldo();
    }

    public String getId() {
        return conta.getId();
    }
}