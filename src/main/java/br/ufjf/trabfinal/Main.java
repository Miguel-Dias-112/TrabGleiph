package br.ufjf.trabfinal;

import javax.swing.JFrame;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Login;
import Models.Bank.Conta;
import Models.Usuarios.Cliente;
import Models.Usuarios.Usuario;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Utils.Exception.LoginException;
import View.LoginScreen;
import View.Screen;

public class Main {
    public static void main(String[] args) throws CPFException {
        //ClienteDAO usuarioDAO = new ClienteDAO();
        //Cliente novoUsuario;
     
    
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.show();
    }
}