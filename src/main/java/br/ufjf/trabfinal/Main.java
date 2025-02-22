
package br.ufjf.trabfinal;

/**
 * @authors 
 * MeuPawDeAsa
 * ggiacz
 * Miguel-Dias-112
 */


import br.ufjf.trabfinal.controller.TransacaoController;
import br.ufjf.trabfinal.model.Conta;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta("12345");

        TransacaoController transacaoController = new TransacaoController();

        transacaoController.realizarSaque(conta, 100.0);
        transacaoController.realizarDeposito(conta, 200.0);
        transacaoController.realizarTransferencia(conta, "7777", 50.0);

        transacaoController.consultarExtrato(conta);
    }
}