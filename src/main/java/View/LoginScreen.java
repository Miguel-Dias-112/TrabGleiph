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
        Tela.setSize(500, 300);
        Tela.setLayout(new GridBagLayout());
        Tela.setLocationRelativeTo(null);

    }
    private static void posicionaElemento(JComponent elemento, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        Tela.add(elemento, gbc);

    }
    public static void main(String[] args) {
      
        configuraTela();
        // Criando os componentes
        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField(15);
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

        // Configurando a janela
        posicionaElemento(userLabel, 0, 0);
        posicionaElemento(userField, 1, 0);
        posicionaElemento(passLabel, 0, 1);
        posicionaElemento(passField, 1, 1);
        posicionaElemento(loginButton, 1, 2);
        posicionaElemento(loginButton, 1, 3);
        posicionaElemento(messageLabel, 0, 5);


        // Exibir a janela no centro da tela
        Tela.setVisible(true);


        

       
    }
}
