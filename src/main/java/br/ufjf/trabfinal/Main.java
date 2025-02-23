package br.ufjf.trabfinal;

/**
 * @authors 
 * Eduarda Nunes
 * Gabriel Giácomo
 * Miguel Dias
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
        transacaoController.realizarSaque(conta, -100);
        transacaoController.realizarDeposito(conta, 21);
        transacaoController.realizarTransferencia(conta, "123", 8000);
        transacaoController.realizarDeposito(conta, 22.444);
        
        transacaoController.consultarExtrato(conta);
    }
}