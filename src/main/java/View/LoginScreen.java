package View;

import javax.swing.*;


import java.awt.*;


public class LoginScreen {
    private JFrame Tela ;
    private JTextField userField ;
    private JPasswordField passField ;
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
        JLabel titutlo = new JLabel("Tela Login", SwingConstants.CENTER);
        titutlo.setFont(new Font("Arial", Font.BOLD, 36));
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
            JLabel userLabel = new JLabel("Usuário:");
            userField = new JTextField(15);
            panelUser.add(userLabel);
            panelUser.add(userField);
        JPanel panelSenha = new JPanel();
            JLabel passLabel = new JLabel("Senha:  ");
            passField = new JPasswordField(15);
            panelSenha.add(passLabel);
            panelSenha.add(passField);
        posicionaElemento(panelUser, 0, 1, new Insets(0, 0, 0, 0));
        posicionaElemento(panelSenha, 0, 2, new Insets(0, 0, 15, 0));
    }
    private void desenhaBotoes(){
        JPanel panelBotoes = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton registrarButton = new JButton("Registrar");
     
   
        //loginButton.addActionListener(new ActionListener() {});
        //registrarButton.addActionListener(new CadastrarUser(userField.getText(), passField.getText(), userField.getText(),"16976385703"));
        posicionaElemento(loginButton, 0, 3, new Insets(0, 50, 8, 50));
        posicionaElemento(registrarButton, 0, 4, new Insets(0, 50, 0, 50));
    }
    public LoginScreen() {
      
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
