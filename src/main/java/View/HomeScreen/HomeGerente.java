package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import Models.Usuario;
import View.DepositoCaixa;
import View.DepositoCaixa;
import View.LoginScreen;
import View.SaqueCaixa;
import View.Screen;
import View.TransferenciaCaixa;
import Models.Caixa;
import Models.Gerente;
import Models.Transacao;
import View.DeletarContaScreen;
import View.EditarScreen;
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
        botaoEditar.addActionListener(new trocarScreen(this,new EditarScreen()));
        menuSuperior.add(botaoEditar);
        
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(new trocarScreen(this,new DeletarContaScreen()));
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
