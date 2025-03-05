package Controller.ClickHandlers;

import Utils.Exception.LoginException;
import Models.Cliente;
import Models.Login;
import Models.Usuario;
import View.HomeScreen.*;
import View.LoginScreen;
import View.Screen;
import View.HomeScreen.HomeCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
public class LogarUser implements ActionListener {

    private JTextField loginField;
    private JTextField senhaField;
    private JComboBox<String> cargoBox;

    public LogarUser(JTextField login, JTextField senha, JComboBox<String> cargo) {
        this.loginField = login;
        this.senhaField = senha;
        this.cargoBox = cargo;
    }   
    
    @Override
    public void actionPerformed(ActionEvent e){
        String login = loginField.getText();
        String senha = senhaField.getText();
        String cargo = (String) cargoBox.getSelectedItem();
        Login novoLogin = new Login();
        
        try {
            Cliente cpf = (Cliente) novoLogin.validarlogin(login, senha);
            if(cpf.equals("")){
                return;
            }
            if (novoLogin.user.getCargo().equals("Cliente")) {
                Cliente user = (Cliente) novoLogin.user;
                Screen newScreen = new HomeCliente(user);
                newScreen.show();
                
            }
      
            
        } catch (LoginException error) {
            error.printStackTrace(); 
        }

    }
}
