package br.ufjf.trabfinal.model;

public class Cliente extends Usuario {
    private Conta conta; 

    public Cliente(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
}