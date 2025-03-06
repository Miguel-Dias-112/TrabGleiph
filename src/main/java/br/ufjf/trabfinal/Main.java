package br.ufjf.trabfinal;

import Utils.Exception.CPFException;
import View.LoginScreen;

public class Main {
    public static void main(String[] args) throws CPFException {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.show();
    }
}