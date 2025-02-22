
package br.ufjf.trabfinal;

/**
 * @authors 
 * MeuPawDeAsa
 * ggiacz
 * Miguel-Dias-112
 */


import br.ufjf.trabfinal.model.Conta;
import br.ufjf.trabfinal.model.Saque;
import br.ufjf.trabfinal.model.Deposito;
import br.ufjf.trabfinal.model.Transferencia;
import br.ufjf.trabfinal.model.Transacao;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta("12345");

        Saque saque = new Saque(100.0);
        conta.adicionarTransacao(saque);

        Deposito deposito = new Deposito(200.0);
        conta.adicionarTransacao(deposito);

        Transferencia transferencia = new Transferencia(50.0, "55555");
        conta.adicionarTransacao(transferencia);

        System.out.println("saldo: " + conta.getSaldo());
        System.out.println("transacoes:");
        for (Transacao transacao : conta.getTransacoes()) {
            System.out.println(transacao);
        }
    }
}