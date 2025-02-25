package Controller;

import Exception.LoginException;
import Models.Login;
import Models.Usuario;
import Persistence.UsuarioDAO;
import View.LoginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

public class LogarUser implements ActionListener {

    private JTextField loginField;
    private JTextField senhaField;

    public LogarUser(JTextField login, JTextField senha) {
        this.loginField = login;
        this.senhaField = senha;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String login = loginField.getText();
        String senha = senhaField.getText();
        
        Login novoLogin = new Login();
        
        try {
            novoLogin.validarlogin(login, senha);
        } catch (LoginException error) {
            error.printStackTrace(); 
        }

    }
}
