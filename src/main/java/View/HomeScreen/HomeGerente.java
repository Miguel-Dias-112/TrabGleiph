package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import Models.Bank.Transacao;
import Models.Usuarios.Caixa;
import Models.Usuarios.Gerente;
import Models.Usuarios.Usuario;
import View.Screen;
import View.Auth.LoginScreen;
import View.PopUps.DeletarContaScreen;
import View.PopUps.DepositoCaixa;
import View.PopUps.EditarScreen;
import View.PopUps.SaqueCaixa;
import View.PopUps.TransferenciaCaixa;

import java.util.List;

import java.awt.*;

public class HomeGerente extends Screen {

    private JPanel menuSuperior;
    private JPanel conteudoCentral;
    private Gerente gerente;

    public HomeGerente(Gerente user) {
        this.gerente = user;
    }

    private void configuraTela() {
        tela = new JFrame("BANCO JAVA - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(800, 600);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);
    }

    private void desenhaMenuSuperior() {
        menuSuperior = new JPanel();
        menuSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuSuperior.setBackground(Color.LIGHT_GRAY);

        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new trocarScreen(this, new LoginScreen()));
        menuSuperior.add(botaoSair);
        
        JButton botaoEditar = new JButton("Editar Usuário");
        botaoEditar.addActionListener(new trocarScreen(this,new EditarScreen(gerente)));
        menuSuperior.add(botaoEditar);
        
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(new trocarScreen(this,new DeletarContaScreen(gerente)));
        menuSuperior.add(botaoDeletar);
       
        tela.add(menuSuperior, BorderLayout.NORTH);
    }

    private void desenhaConteudoCentral() {
        conteudoCentral = new JPanel();
        conteudoCentral.setLayout(new GridLayout(1, 2, 20, 20));
        conteudoCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel saldoPanel = new JPanel();
        saldoPanel.add(new JLabel("Você é um gerente"));

        conteudoCentral.add(saldoPanel);

        // telas especificas para caixa e gerente aqui
        tela.add(conteudoCentral, BorderLayout.CENTER);
    }

    @Override
    public void show() {
        configuraTela();
        desenhaMenuSuperior();
        desenhaConteudoCentral();
        tela.setVisible(true);
    }
}
