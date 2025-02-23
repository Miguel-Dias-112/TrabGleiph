package Controller;

import Models.Saque;
import Models.Conta;
import Models.Transacao;
import Models.Transferencia;
import Models.Deposito;
import Persistence.TransacaoPersist;
import Persistence.TransacaoDAO;

public class TransacaoController {
    private TransacaoPersist  transacaoPersist; // final?

    public TransacaoController() {
        this.transacaoPersist = new TransacaoDAO();
    }

    public void realizarSaque(Conta conta, double valor) {
        Saque saque = new Saque(valor);
        conta.adicionarTransacao(saque);
        transacaoPersist.save(conta.getTransacoes()); 
        System.out.println("Saque realizado com sucesso!");
    }

    public void realizarDeposito(Conta conta, double valor) {
        Deposito deposito = new Deposito(valor);
        conta.adicionarTransacao(deposito);
        transacaoPersist.save(conta.getTransacoes()); 
        System.out.println("Depósito realizado com sucesso!");
    }

    public void realizarTransferencia(Conta contaOrigem, String contaDestino, double valor) {
        Transferencia transferencia = new Transferencia(valor, contaDestino);
        contaOrigem.adicionarTransacao(transferencia);
        transacaoPersist.save(contaOrigem.getTransacoes()); 
        System.out.println("Transferência realizada com sucesso!");
    }

    public void consultarExtrato(Conta conta) {
        System.out.println("Extrato da conta " + conta.getId() + ":");
        for (Transacao transacao : conta.getTransacoes()) {
            System.out.println(transacao);
        }
    }
}