package Models;

import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;

public class Cliente extends Usuario {
    private Conta conta; 
    private double saldo;  
    public Cliente(String login, String senha, String nome, String cpf) throws CPFException, CadastroException  {
        super(login, senha, nome, cpf, "Cliente");
        
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
    public double getSaldo() {
        this.saldo = saldo;
        return saldo;
    }
}