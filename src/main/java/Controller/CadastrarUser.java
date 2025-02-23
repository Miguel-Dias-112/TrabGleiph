package Controller;



import Models.Usuario;
import Persistence.UsuarioDAO;
import View.LoginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarUser implements ActionListener {

    private String nome;
    private String login;
    private String senha;
    private String cpf;

    public CadastrarUser(String nome, String login, String senha, String cpf) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        System.out.println(cpf);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            Usuario novoUsuario = new Usuario(login, senha, nome, cpf);
            usuarioDAO.adicionarNovoUsuario(novoUsuario);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}