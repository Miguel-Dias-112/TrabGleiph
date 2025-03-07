/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Models.Bank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    final private DecimalFormat df = new DecimalFormat("000");
    private static int ultimoId = 1;
    final private String id;
    private double saldo;
    private List<Transacao> transacoes;
    // private List<Investimento> investimentos;

    public Conta() {
        this.id = "CONT" + df.format(ultimoId++);
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
        // this.investimentos = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }
    
    public String consultarExtrato() {
        String extrato = "- - - - - - - - - - - - - - - -   EXTRATO   - - - - - - - - - - - - - - - -\n";
        for (Transacao transacao : getTransacoes()) {
            extrato += "\n" + transacao;
        }
        return extrato;
    }
    public String getId() {
        return id;
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

    public String getClientId() {
        return id;
    }
}