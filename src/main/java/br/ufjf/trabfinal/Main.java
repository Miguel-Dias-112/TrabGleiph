package br.ufjf.trabfinal;

import javax.swing.JFrame;

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

public class Main {
    public static void main(String[] args) throws CPFException {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.show();
    }
}