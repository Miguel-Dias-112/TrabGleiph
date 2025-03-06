package Controller.ClickHandlers;

import Utils.Exception.LoginException;
import Models.Caixa;
import Models.Login;
import Models.Usuarios.Cliente;
import Models.Usuarios.Usuario;
import View.HomeScreen.*;
import View.LoginScreen;
import View.Screen;
import View.HomeScreen.HomeCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import javax.swing.SwingUtilities;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
        
        switch (cargo) {
            case "Cliente":
                try {
                    Cliente cliente = (Cliente) novoLogin.validarLoginCliente(login, senha);
                    Screen newScreen = new HomeCliente(cliente);
                    Window oldWindow = SwingUtilities.getWindowAncestor(loginField);
                    if (oldWindow != null) {
                        oldWindow.dispose();
                    }
                    newScreen.show();
                } catch (LoginException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Caixa":
                try {
                    Caixa cliente = novoLogin.validarLoginCaixa(login, senha);
                    Screen newScreen = new HomeCaixa(cliente);
                    Window oldWindow = SwingUtilities.getWindowAncestor(loginField);
                    if (oldWindow != null) {
                        oldWindow.dispose();
                    }
                    newScreen.show();
                } catch (LoginException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            default:
                break;
        }
        
    }
}
