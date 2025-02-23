package br.ufjf.trabfinal;

import javax.swing.JFrame;

/**
 * @authors 
 * Eduarda Nunes
 * Gabriel Giácomo
 * Miguel Dias
 */

import Controller.TransacaoController;
import Exception.CPFException;
import Models.Conta;
import Models.Usuario;
import Persistence.UsuarioDAO;
import View.LoginScreen;

public class Main {
    public static void main(String[] args) throws CPFException {
        // UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Usuario user2 = new Usuario("joao.silva", "senha123", "João Silva", "12345678909");
        // usuarioDAO.adicionarNovoUsuario(user2);
        
        // Conta conta = new Conta("12345");

        // TransacaoController transacaoController = new TransacaoController();

        // transacaoController.realizarSaque(conta, 100.0);
        // transacaoController.realizarDeposito(conta, 200.0);
        // transacaoController.realizarTransferencia(conta, "7777", 50.0);
        // transacaoController.realizarSaque(conta, -100);
        // transacaoController.realizarDeposito(conta, 21);
        // transacaoController.realizarTransferencia(conta, "123", 8000);
        // transacaoController.realizarDeposito(conta, 22.444);
        
        // transacaoController.consultarExtrato(conta);

        LoginScreen loginScreen = new LoginScreen();
     
    }
}