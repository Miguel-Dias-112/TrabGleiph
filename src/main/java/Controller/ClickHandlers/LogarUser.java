/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.ClickHandlers;

import Utils.Checkers.LoginChecker;
import Utils.Exception.LoginException;
import Models.Usuarios.Caixa;
import Models.Usuarios.Cliente;
import View.HomeScreen.*;
import View.Screen;
import View.HomeScreen.HomeCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import javax.swing.SwingUtilities;

import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.GerenteDAO;
import Models.Usuarios.Gerente;
import Utils.Exception.CPFException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String cpf;
        
        try{
            switch (cargo) {
                case "Cliente":
                    ClienteDAO clientes = new ClienteDAO();
                    LoginChecker.checkLoginCliente(login, senha);
                    Cliente cliente = clientes.findByLogin(login);
                    cpf = cliente.getCpf();
                    newScreen = new HomeCliente(cpf);

                    break;
                case "Caixa":
                    CaixaDAO caixas = new CaixaDAO();
                    LoginChecker.checkLoginCaixa(login, senha);
                    Caixa caixa = caixas.findByLogin(login);
                    cpf = caixa.getCpf();
                    newScreen = new HomeCaixa(cpf);
                    break;
                case "Gerente":
                    GerenteDAO gerentes = new GerenteDAO();
                    LoginChecker.checkLoginGerente(login, senha);
                    Gerente gerente = gerentes.findByLogin(login);
                    cpf = gerente.getCpf();
                    newScreen = new HomeGerente(cpf);
                    break;
                default:
                    break;
            }
            
            Window oldWindow = SwingUtilities.getWindowAncestor(loginField);
            if (oldWindow != null) {
                oldWindow.dispose();
            }
            newScreen.show();
            
        }catch(LoginException error){
            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);   
        } catch (CPFException ex) {
            Logger.getLogger(LogarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
