package Controller.ClickHandlers;

import Utils.Checkers.LoginChecker;
import Utils.Exception.LoginException;
import Models.Usuarios.Caixa;
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

import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;

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
        Screen newScreen = null;
        switch (cargo) {
            case "Cliente":
                ClienteDAO clientes = new ClienteDAO();
                LoginChecker.checkLoginCliente(login, senha);
                Cliente cliente = clientes.findByLogin(login);
                newScreen = new HomeCliente(cliente);
               
                break;
            case "Caixa":
                CaixaDAO caixas = new CaixaDAO();
                LoginChecker.checkLoginCaixa(login, senha);
                Caixa caixa = caixas.findByLogin(login);
                newScreen = new HomeCaixa(caixa);
            default:
                break;
        }
        Window oldWindow = SwingUtilities.getWindowAncestor(loginField);
        if (oldWindow != null) {
            oldWindow.dispose();
        }
        newScreen.show();
    }
}
