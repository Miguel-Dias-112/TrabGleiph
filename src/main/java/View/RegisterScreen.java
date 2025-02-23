package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.CadastrarUser;

public class RegisterScreen {
    private JFrame Tela ;
    private JTextField userField ;
    private JTextField loginField ;
    private JPasswordField senhaField ;
    private JTextField cpfField ;

    private void configuraTela(){
        Tela = new JFrame("Tela de Login");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(600, 500);
        Tela.setLayout(new GridBagLayout());
        Tela.setLocationRelativeTo(null);

    }
    private 
    void posicionaElemento(JComponent elemento, int x, int y,Insets margens) {
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.gridx = x;
       gbc.gridy = y;
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.insets =margens;
       Tela.add(elemento, gbc);
   }
    private void desenhaTitulo(){
        JLabel titutlo = new JLabel("Tela Registro", SwingConstants.CENTER);
        titutlo.setFont(new Font("Arial", Font.BOLD, 36));
     //   titutlo.setFont(new Font("Arial", Font.BOLD, 36));
        posicionaElemento(titutlo, 0, 0, new Insets(0, 0, 60, 0));
    }
    private void desenhaMensagem(){
        JLabel messageLabel = new JLabel("Insira suas crêdenciais", SwingConstants.CENTER);

        posicionaElemento(messageLabel, 0, 10, new Insets(5, 0, 15, 0));
    }
   private void desenhaInputs(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel panelUser = new JPanel();
            JLabel userLabel = new JLabel("Nome:   ");
            userField = new JTextField(15);
            panelUser.add(userLabel);
            panelUser.add(userField);
        JPanel panelLogin = new JPanel();
            JLabel loginLabel = new JLabel("Login:  ");
            loginField = new JTextField(15);
            panelLogin.add(loginLabel);
            panelLogin.add(loginField);
        JPanel panelSenha = new JPanel();
            JLabel senhaLabel = new JLabel("Senha:  ");
            senhaField = new JPasswordField(15);
            panelSenha.add(senhaLabel);
            panelSenha.add(senhaField);
        JPanel panelCpf = new JPanel();
            JLabel cpfLabel = new JLabel("CPF:      ");
            cpfField = new JTextField(10);
            panelCpf.add(cpfLabel);
            panelCpf.add(cpfField);
        
        posicionaElemento(panelUser, 0, 1, new Insets(0, 0, 0, 0));
        posicionaElemento(panelSenha, 0, 2, new Insets(0, 0, 0, 0));
        posicionaElemento(panelCpf, 0, 3, new Insets(0, 0, 0, 0));
        posicionaElemento(panelLogin, 0, 4, new Insets(0, 0, 15, 0));

    }
    private void desenhaBotoes(){
        JButton loginButton = new JButton("Login");
        JButton registrarButton = new JButton("Registrar");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tela.setVisible(false);
                LoginScreen loginScreen = new LoginScreen();
            }
        });
        registrarButton.addActionListener(new CadastrarUser(userField, loginField, senhaField,cpfField));
        posicionaElemento(registrarButton, 0, 5, new Insets(0, 50, 8, 50));
        posicionaElemento(loginButton, 0, 6, new Insets(0, 50, 0, 50));
    }
    public RegisterScreen() {
      
        configuraTela();
        desenhaTitulo();
        desenhaMensagem();

        // Criando os componentes
        JLabel titutlo = new JLabel("Login Screen", SwingConstants.CENTER);
        titutlo.setFont(new Font("Arial", Font.BOLD, 36));

        desenhaInputs();
        desenhaBotoes();
        Tela.setVisible(true);
        
    }
}
