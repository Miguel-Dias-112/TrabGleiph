/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import Controller.DataAcessObjects.CaixaDAO;
import Models.Usuarios.Caixa;
import Utils.Exception.CPFException;
import View.Screen;
import View.Auth.LoginScreen;
import View.PopUps.DeletarContaScreen;
import View.PopUps.DepositoCaixa;
import View.PopUps.EditarScreen;
import View.PopUps.SaqueCaixa;
import View.PopUps.TransferenciaCaixa;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeCaixa extends Screen {

    private JPanel menuSuperior;
    private JPanel conteudoCentral;
    private Caixa caixa;

    public HomeCaixa(String cpf) {
        CaixaDAO caixas = new CaixaDAO();
        this.caixa = caixas.findByCpf(cpf);
    }

    private void configuraTela() {
        tela = new JFrame("BANCO JAVA - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1200, 600);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);
    }

    private void desenhaMenuSuperior() throws CPFException {
        menuSuperior = new JPanel();
        menuSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuSuperior.setBackground(Color.LIGHT_GRAY);

        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new trocarScreen(this, new LoginScreen()));
        menuSuperior.add(botaoSair);
        
        JButton botaoSaque = new JButton("Saque");
        botaoSaque.addActionListener(new trocarScreen(this, new SaqueCaixa(caixa)));
        menuSuperior.add(botaoSaque);
        
        JButton botaoTransferir = new JButton("Transferir");
        botaoTransferir.addActionListener(new trocarScreen(this, new TransferenciaCaixa(caixa)));
        menuSuperior.add(botaoTransferir);
        
        JButton botaoDeposito = new JButton("Deposito");
        botaoDeposito.addActionListener(new trocarScreen(this, new DepositoCaixa(caixa)));
        menuSuperior.add(botaoDeposito);
        
        JButton botaoEditar = new JButton("Editar Usuário");
        botaoEditar.addActionListener(new trocarScreen(this,new EditarScreen(caixa)));
        menuSuperior.add(botaoEditar);
        
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(new trocarScreen(this,new DeletarContaScreen(caixa)));
        menuSuperior.add(botaoDeletar);
       
        tela.add(menuSuperior, BorderLayout.NORTH);
    }

    private void desenhaConteudoCentral() {
        conteudoCentral = new JPanel();
        conteudoCentral.setLayout(new GridLayout(1, 2, 20, 20));
        conteudoCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel saldoPanel = new JPanel();
        saldoPanel.add(new JLabel("Você é um caixa"));

        conteudoCentral.add(saldoPanel);

        // telas especificas para caixa e gerente aqui
        tela.add(conteudoCentral, BorderLayout.CENTER);
    }

    @Override
    public void show() {
        configuraTela();
        try {
            desenhaMenuSuperior();
        } catch (CPFException ex) {
            Logger.getLogger(HomeCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
        desenhaConteudoCentral();
        tela.setVisible(true);
    }
}
