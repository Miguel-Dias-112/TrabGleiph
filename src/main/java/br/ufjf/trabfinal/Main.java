package br.ufjf.trabfinal;

import java.util.List;

import javax.swing.JFrame;

import Controller.DataAcessObjects.ClienteDao;
import Controller.DataAcessObjects.InvestimentoDAO;
import Models.Cliente;
import Models.Conta;
import Models.Investimento;
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
        Cliente novoUsuario;

                InvestimentoDAO investimentoDAO = new InvestimentoDAO();

        // Adicionar investimentos
        investimentoDAO.adicionarInvestimento(new Investimento("Investimento A", 2025, 10.0));
        investimentoDAO.adicionarInvestimento(new Investimento("Investimento B", 2026, 12.0));

        // Listar investimentos
        List<Investimento> investimentos = investimentoDAO.findAll();
        for (Investimento investimento : investimentos) {
            System.out.println(investimento);
        }
    
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.show();
    }
}