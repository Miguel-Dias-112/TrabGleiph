package View;

import javax.swing.*;
import java.awt.*;

public class HomeScreen {
    private JFrame tela;
    private JPanel menuSuperior;
    private JPanel conteudoCentral;

    public HomeScreen(String tipoUsuario) {
        configuraTela();
        desenhaMenuSuperior(tipoUsuario);
        desenhaConteudoCentral(tipoUsuario);
        tela.setVisible(true);
    }

    private void configuraTela() {
        tela = new JFrame("BANCO JAVA - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(800, 600);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);
    }

    private void desenhaMenuSuperior(String tipoUsuario) {
        menuSuperior = new JPanel();
        menuSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuSuperior.setBackground(Color.LIGHT_GRAY);

        if ("Cliente".equals(tipoUsuario)) {
            menuSuperior.add(new JButton("Transferências"));
            menuSuperior.add(new JButton("Pedir Crédito"));
            menuSuperior.add(new JButton("Ver Investimentos"));
        }
        // menus diferentes para caixa e gerente aqui
        
        tela.add(menuSuperior, BorderLayout.NORTH);
    }

    private void desenhaConteudoCentral(String tipoUsuario) {
        conteudoCentral = new JPanel();
        conteudoCentral.setLayout(new GridLayout(1, 2, 20, 20));
        conteudoCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        if ("Cliente".equals(tipoUsuario)) {
            JPanel saldoPanel = new JPanel();
            saldoPanel.setBorder(BorderFactory.createTitledBorder("Saldo"));
            saldoPanel.add(new JLabel("R$ 0,00"));
            
            JPanel historicoPanel = new JPanel();
            historicoPanel.setBorder(BorderFactory.createTitledBorder("Histórico de Transações"));
            historicoPanel.add(new JTextArea(10, 30));
            
            conteudoCentral.add(saldoPanel);
            conteudoCentral.add(historicoPanel);
        }
        // telas especificas para caixa e gerente aqui        
        tela.add(conteudoCentral, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new HomeScreen("Cliente"); // mockup Cliente
    }
}
