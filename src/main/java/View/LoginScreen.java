package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends Screen {
    private JFrame Tela;
    private JTextField userField;
    private JPasswordField passField;

    private void configuraTela() {
        Tela = new JFrame("BANCO JAVA");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(700, 550);
        Tela.setLayout(new GridBagLayout());
        Tela.setLocationRelativeTo(null);
    }

    private void posicionaElemento(JComponent elemento, int x, int y, Insets margens) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = margens;
        Tela.add(elemento, gbc);
    }

    private void desenhaTitulo() {
        JLabel titulo = new JLabel("Acesse o sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Courier", Font.BOLD, 36));
        posicionaElemento(titulo, 0, 0, new Insets(40, 0, 40, 0));
    }

    private void desenhaMensagem() {
        JLabel messageLabel = new JLabel("Insira suas credenciais", SwingConstants.CENTER);
        posicionaElemento(messageLabel, 0, 1, new Insets(0, 0, 20, 0));
    }

    private void desenhaInputs() {
        JPanel panelUser = new JPanel();
        JLabel userLabel = new JLabel("Login:       ");
        userField = new JTextField(20);
        panelUser.add(userLabel);
        panelUser.add(userField);

        JPanel panelSenha = new JPanel();
        JLabel passLabel = new JLabel("Senha:  ");
        passField = new JPasswordField(20);
        panelSenha.add(passLabel);
        panelSenha.add(passField);

        posicionaElemento(panelUser, 0, 2, new Insets(0, 0, 10, 0));
        posicionaElemento(panelSenha, 0, 3, new Insets(0, 0, 20, 0));
    }

    private void desenhaBotoes() {
        JButton loginButton = new JButton("Acessar");
        JButton registrarButton = new JButton("Registrar");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tela.dispose();
                new HomeScreen("Cliente");
            }
        });

        registrarButton.addActionListener((ActionEvent e) -> {
            Tela.dispose();
            new RegisterScreen();
        });

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(registrarButton);
        panelBotoes.add(loginButton);

        posicionaElemento(panelBotoes, 0, 4, new Insets(20, 0, 0, 0));
    }

    public LoginScreen() {
        configuraTela();
        desenhaTitulo();
        desenhaMensagem();
        desenhaInputs();
        desenhaBotoes();
    }
 
}
