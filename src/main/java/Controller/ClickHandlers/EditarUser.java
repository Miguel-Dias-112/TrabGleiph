package Controller.ClickHandlers;

import Controller.DataAcessObjects.ClienteDAO;
import Utils.Exception.EditarException;
import Models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class EditarUser implements ActionListener {

    private JTextField nomeField;
    private JTextField loginField;
    private JPasswordField senhaField;

    public EditarUser(JTextField nome, JTextField login, JPasswordField senha) {
        this.nomeField = nome;
        this.loginField = login;
        this.senhaField = senha;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = nomeField.getText();
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());
        String cpf = Login.user.getCpf();
        
        try {
            editarUserPersist(cpf, nome, login, senha);
        } catch (EditarException ex) {
            Logger.getLogger(EditarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void editarUserPersist(String cpf, String nome, String login, String senha) throws EditarException{
        switch(Login.user.getCargo()){
            case "Cliente":
                ClienteDAO clienteDAO =  new ClienteDAO();
                clienteDAO.editarCliente(cpf, nome, login, senha);
                break;
            case "Gerente":
                GerenteDAO gerenteDAO =  new gerenteDAO();
                gerenteDAO.editarGerente(cpf, nome, login, senha);
                break;
            case "Caixa":
                CaixaDAO caixaDAO =  new CaixaDAO();
                caixaDAO.editarCliente(cpf, nome, login, senha);
                break;
        }
    }

}
