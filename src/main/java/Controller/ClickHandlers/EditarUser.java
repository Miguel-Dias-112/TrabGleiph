package Controller.ClickHandlers;

import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.GerenteDAO;
import Utils.Exception.EditarException;
import Models.*;
import Models.Usuarios.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class EditarUser implements ActionListener {

    private JTextField nomeField;
    private JTextField loginField;
    private JPasswordField senhaField;
    private Usuario user;
    public EditarUser(JTextField nome, JTextField login, JPasswordField senha, Usuario user) {
        this.nomeField = nome;
        this.loginField = login;
        this.senhaField = senha;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = nomeField.getText();
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());
        String cpf = user.getCpf();
        
        try {
            editarUserPersist(cpf, nome, login, senha);
        } catch (EditarException ex) {
            Logger.getLogger(EditarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void editarUserPersist(String cpf, String nome, String login, String senha) throws EditarException{
        switch(user.getCargo()){
            case "Cliente" -> {
                ClienteDAO clienteDAO =  new ClienteDAO();
                clienteDAO.editarCliente(cpf, nome, login, senha);
            }
            case "Gerente" -> {
                GerenteDAO gerenteDAO =  new GerenteDAO();
                gerenteDAO.editarGerente(cpf, nome, login, senha);
            }
            case "Caixa" -> {
                CaixaDAO caixaDAO =  new CaixaDAO();
                caixaDAO.editarCaixa(cpf, nome, login, senha);
            }
        }
    }

}
