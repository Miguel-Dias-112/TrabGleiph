package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import View.LoginScreen;
import View.Screen;
import View.TransferenciaView;
import Models.Cliente;
import Models.Transacao;
import java.util.List;
import java.awt.*;

public class HomeCliente extends Screen {

    private JPanel menuSuperior;
    private JPanel conteudoCentral;
    private JLabel saldoLabel;
    private JTextArea historico ;


    public HomeCliente(Cliente user) {
        double saldo = user.getSaldo();
        saldoLabel = new JLabel("R$ "+ saldo);
        historico = new JTextArea(10, 30);
        historico.setText(user.getConta().consultarExtrato());
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
        menuSuperior.add(new JButton("Editar Usuário"));
        JButton botaoTransferir = new JButton("Transferir");
        botaoTransferir.addActionListener(new trocarScreen(this, new TransferenciaView()));

        menuSuperior.add(botaoTransferir);
        menuSuperior.add(new JButton("Ver Investimentos"));
        tela.add(menuSuperior, BorderLayout.NORTH);
    }
    private void desenhaConteudoCentral() {
        conteudoCentral = new JPanel();
        conteudoCentral.setLayout(new GridLayout(1, 2, 20, 20));
        conteudoCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel saldoPanel = new JPanel();
        saldoPanel.setBorder(BorderFactory.createTitledBorder("Saldo"));
        saldoPanel.add(saldoLabel);
        JPanel historicoPanel = new JPanel();
        historicoPanel.setBorder(BorderFactory.createTitledBorder("Histórico de Transações"));
        historicoPanel.add(historico);
        conteudoCentral.add(saldoPanel);
        conteudoCentral.add(historicoPanel);
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
