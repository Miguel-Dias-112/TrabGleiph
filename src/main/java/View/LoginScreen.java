package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private static JFrame Tela ;
    private static void configuraTela(){
        Tela = new JFrame("Tela de Login");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(700, 500);
        Tela.setLayout(new GridBagLayout());
        Tela.setLocationRelativeTo(null);

    }
    private static void posicionaElemento(JComponent elemento, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        Tela.add(elemento, gbc);

    }
    public static void main(String[] args) {
      
        configuraTela();
        // Criando os componentes
        JPanel panelUser = new JPanel();

            JLabel userLabel = new JLabel("Usuário:");
            JTextField userField = new JTextField(15);
            panelUser.add(userLabel);
            panelUser.add(userField);
        JPanel panelSenha = new JPanel();
            JLabel passLabel = new JLabel("Senha:  ");
            JPasswordField passField = new JPasswordField(15);
            panelSenha.add(passLabel);
            panelSenha.add(passField);
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);

        JButton loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userField.getText();
                    String password = new String(passField.getPassword());

                    if ("admin".equals(username) && "1234".equals(password)) {
                        messageLabel.setText("Login bem-sucedido!");
                        messageLabel.setForeground(Color.GREEN);
                    } else {
                        messageLabel.setText("Credenciais inválidas!");
                        messageLabel.setForeground(Color.RED);
                    }
                }
            });

        JButton registrarButton = new JButton("Registrar");
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageLabel.setText("usuário registrado com sucesso!");
                    messageLabel.setForeground(Color.BLACK);
                }
            });

        // Configurando a janela
        posicionaElemento(panelUser, 0, 0);
        posicionaElemento(panelSenha, 0, 1);
        posicionaElemento(loginButton, 0, 2);
        posicionaElemento(registrarButton, 0, 3);
        posicionaElemento(messageLabel, 0, 5);


        // Exibir a janela no centro da tela
        Tela.setVisible(true);


        

       
    }
}
