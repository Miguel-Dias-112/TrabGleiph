/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.Auth;

import Controller.ClickHandlers.CadastrarUser;
import View.Screen;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends Screen {
    private JTextField userField;
    private JTextField loginField;
    private JPasswordField senhaField;
    private JTextField cpfField;
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
        JLabel titulo = new JLabel("Cadastre-se", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); 
        posicionaElemento(titulo, 0, 0, new Insets(40, 0, 40, 0));
    }

    private void desenhaMensagem() {
        JLabel messageLabel = new JLabel("Preencha os dados abaixo para registrar", SwingConstants.CENTER);
        posicionaElemento(messageLabel, 0, 1, new Insets(0, 0, 20, 0));
    }

    private void desenhaInputs() {
        JPanel panelUser = new JPanel();
        JLabel userLabel = new JLabel("Nome:  ");
        userField = new JTextField(20);
        panelUser.add(userLabel);
        panelUser.add(userField);

        JPanel panelLogin = new JPanel();
        JLabel loginLabel = new JLabel("Login:  ");
        loginField = new JTextField(20);
        panelLogin.add(loginLabel);
        panelLogin.add(loginField);

        JPanel panelSenha = new JPanel();
        JLabel senhaLabel = new JLabel("Senha: ");
        senhaField = new JPasswordField(20);
        panelSenha.add(senhaLabel);
        panelSenha.add(senhaField);

        JPanel panelCpf = new JPanel();
        JLabel cpfLabel = new JLabel("CPF:     ");
        cpfField = new JTextField(20);
        panelCpf.add(cpfLabel);
        panelCpf.add(cpfField);

        JPanel panelCargo = new JPanel();
        JLabel cargoLabel = new JLabel("Cargo: ");
        String[] cargos = {"Cliente", "Caixa", "Gerente"};
        cargoComboBox = new JComboBox<>(cargos);
        panelCargo.add(cargoLabel);
        panelCargo.add(cargoComboBox);

        posicionaElemento(panelUser, 0, 2, new Insets(0, 0, 10, 0));
        posicionaElemento(panelLogin, 0, 3, new Insets(0, 0, 10, 0));
        posicionaElemento(panelSenha, 0, 4, new Insets(0, 0, 10, 0));
        posicionaElemento(panelCpf, 0, 5, new Insets(0, 0, 10, 0));
        posicionaElemento(panelCargo, 0, 6, new Insets(0, 0, 20, 0));
    }

    private void desenhaBotoes() {
        JButton registrarButton = new JButton("Registrar");
        CadastrarUser cadastrarUser = new CadastrarUser(userField, cpfField, loginField, senhaField, cargoComboBox);
        registrarButton.addActionListener(cadastrarUser);
    
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new LoginScreen().show();
            tela.dispose();
        });
    
        JPanel panelBotoes = new JPanel();
        panelBotoes.add(registrarButton);
        panelBotoes.add(voltarButton);
    
        posicionaElemento(panelBotoes, 0, 7, new Insets(20, 0, 0, 0));
    }
    
    public RegisterScreen() {
        
    }
    @Override
    public void show() {
        configuraTela();
        desenhaTitulo();
        desenhaMensagem();
        desenhaInputs();
        desenhaBotoes();
        tela.setVisible(true);
    }
}
