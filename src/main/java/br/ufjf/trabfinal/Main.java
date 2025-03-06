package br.ufjf.trabfinal;

import javax.swing.JFrame;

import Controller.ClickHandlers.TransacaoController;
import Controller.DataAcessObjects.TransacaoDAO;
import Controller.DataAcessObjects.UsuarioDAO;
import Models.Cliente;
import Models.Conta;
import Models.Login;
import Models.Usuario;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Utils.Exception.LoginException;
import View.LoginScreen;
import View.Screen;
import View.HomeScreen.HomeCliente;

public class Main {
    public static void main(String[] args) throws CPFException {
        TransacaoDAO trasDao = new TransacaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario novoUsuario;
        try {
            novoUsuario = new Cliente(
                "jose", "Miguel@123", "José Miguel", "12345678909"
            );
            usuarioDAO.adicionarNovoUsuario(novoUsuario);
        } catch (CPFException | CadastroException e) {
            e.printStackTrace();
        }

        Login novoLogin = new Login();
        try {
            Cliente cliente = (Cliente) novoLogin.validarlogin("jose", "Miguel@123");
            trasDao.realizarSaque(cliente, 100.0);

        } catch (LoginException error) {
            error.printStackTrace(); 
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
        //loginScreen.show();
    }
}