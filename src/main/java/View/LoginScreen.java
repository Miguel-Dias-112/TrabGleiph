package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    public static void main(String[] args) {
        // Criando o frame principal
        JFrame frame = new JFrame("Tela de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new GridBagLayout());

        // Criando os componentes
        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);

        // Configurando layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(userLabel, gbc);

        gbc.gridx = 1;
        frame.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(passLabel, gbc);

        gbc.gridx = 1;
        frame.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(loginButton, gbc);

        gbc.gridy = 3;
        frame.add(messageLabel, gbc);

        // Definindo ação do botão
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

        // Exibir a janela no centro da tela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
