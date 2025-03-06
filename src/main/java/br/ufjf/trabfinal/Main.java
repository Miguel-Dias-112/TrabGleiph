package br.ufjf.trabfinal;

import javax.swing.JFrame;

import Controller.DataAcessObjects.ClienteDao;
import Models.Cliente;
import Models.Conta;
import Models.Login;
import Models.Usuario;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Utils.Exception.LoginException;
import View.LoginScreen;
import View.Screen;

public class Main {
    public static void main(String[] args) throws CPFException {
        ClienteDao usuarioDAO = new ClienteDao();
        Usuario novoUsuario;
        try {
            novoUsuario = new Cliente(
                "miguel", "123", "José Miguel", "16976385703"
            );
            usuarioDAO.adicionarNovoUsuario(novoUsuario);
        } catch (CPFException | CadastroException e) {
            e.printStackTrace();
        }
        try {
            novoUsuario = new Cliente(
                "wdgast", "123", "José Miguel", "20687284783"
            );
            usuarioDAO.adicionarNovoUsuario(novoUsuario);
        } catch (CPFException | CadastroException e) {
            e.printStackTrace();
        }
    
        
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
        loginScreen.show();
    }
}