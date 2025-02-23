package Controller;



import Models.Usuario;
import Persistence.UsuarioDAO;
import View.LoginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class CadastrarUser implements ActionListener {

    private JTextField nomeField;
    private JTextField loginField;
    private JTextField senhaField;
    private JTextField cpfField;

    public CadastrarUser(JTextField nome, JTextField login, JTextField senha, JTextField cpf) {
        this.nomeField = nome;
        this.loginField = login;
        this.senhaField = senha;
        this.cpfField = cpf;
     //   System.out.println(cpf);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = nomeField.getText();
        String login = loginField.getText();
        String senha = senhaField.getText();
        String cpf = cpfField.getText();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            Usuario novoUsuario = new Usuario(login, senha, nome, cpf);
            usuarioDAO.adicionarNovoUsuario(novoUsuario);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}