package Controller.ClickHandlers;

import Utils.Exception.LoginException;
import Models.Login;
import Models.Usuario;
import View.HomeScreen.*;
import View.LoginScreen;
import View.Screen;
import View.HomeScreen.HomeCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            String cpf = novoLogin.validarlogin(login, senha);
         
            if(cpf.equals("")){
                return;
            }
            Usuario user = novoLogin.user;
            Screen newScreen = new HomeCliente(user);
            newScreen.show();
            
        } catch (LoginException error) {
            error.printStackTrace(); 
        }

    }
}
