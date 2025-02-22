package br.ufjf.trabfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String id;
    private double saldo;
    private List<Transacao> transacoes;

    // Construtor
    public Conta(String id) {
        this.id = id;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    // Métodos
    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
        if (transacao instanceof Saque) {
            saldo -= transacao.getValor();
        } else if (transacao instanceof Deposito) {
            saldo += transacao.getValor();
        } else if (transacao instanceof Transferencia) {
            saldo -= transacao.getValor();
        }
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}