package View;

import javax.swing.*;

import Controller.ClickHandlers.LogarUser;
import Controller.ClickHandlers.trocarScreen;

import java.awt.*;


public class LoginScreen extends Screen {
    private JTextField userField;
    private JPasswordField passField;
    private JComboBox<String> cargoComboBox;

    private void configuraTela() {
        
        tela = new JFrame("BANCO JAVA");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(700, 550);
        tela.setLayout(new GridBagLayout());
        tela.setLocationRelativeTo(null);
    }

    private void posicionaElemento(JComponent elemento, int x, int y, Insets margens) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = margens;
        tela.add(elemento, gbc);
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
        JLabel userLabel = new JLabel("Login:   ");
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
        loginButton.addActionListener(new LogarUser(userField, passField,cargoComboBox));
        registrarButton.addActionListener(new trocarScreen(this,new RegisterScreen()));
        JPanel panelBotoes = new JPanel();
        panelBotoes.add(registrarButton);
        panelBotoes.add(loginButton);
        posicionaElemento(panelBotoes, 0, 4, new Insets(20, 0, 0, 0));
    }
    public void desenhaCargo(){
        
        JPanel panelCargo = new JPanel();
        JLabel cargoLabel = new JLabel("Cargo: ");
        String[] cargos = {"Cliente", "Caixa", "Gerente"};
        this.cargoComboBox = new JComboBox<String>(cargos);
        panelCargo.add(cargoLabel);
        panelCargo.add(cargoComboBox);
        posicionaElemento(panelCargo, 0, 6, new Insets(0, 0, 20, 0));

    }
    public LoginScreen() {
       
    }
    @Override
    public void show() {
        configuraTela();
        desenhaTitulo();
        desenhaMensagem();
        desenhaInputs();
        desenhaCargo();
        desenhaBotoes();
        tela.setVisible(true);
    }
}
