package Controller.ClickHandlers;

import Utils.Exception.EditarException;
import Models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        try {
            Login.user.setNome(nome);
            Login.user.setLogin(login);
            Login.user.setSenha(senha);
            JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (EditarException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }

}
