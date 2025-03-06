package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import Models.Usuario;
import View.LoginScreen;
import View.SaqueCaixa;
import View.Screen;
import View.TransferenciaCaixa;
import Models.Caixa;
import Models.Transacao;
import java.util.List;

import java.awt.*;

public class HomeCaixa extends Screen {

    private JPanel menuSuperior;
    private JPanel conteudoCentral;
    private Caixa caixa;

    public HomeCaixa(Caixa user) {
        this.caixa = user;
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
        JButton botaoSaque = new JButton("Saque");
        botaoSaque.addActionListener(new trocarScreen(this, new SaqueCaixa(caixa)));
        JButton botaoTransferir = new JButton("Transferir");
        botaoTransferir.addActionListener(new trocarScreen(this, new TransferenciaCaixa(caixa)));
     
       
        menuSuperior.add(botaoTransferir);
        menuSuperior.add(new JButton("Editar Usuário"));
        menuSuperior.add(botaoSaque);
        menuSuperior.add(new JButton("Depósito"));
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
        desenhaMenuSuperior();
        desenhaConteudoCentral();
        tela.setVisible(true);
    }
}
