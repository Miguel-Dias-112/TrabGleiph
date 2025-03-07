/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.ClickHandlers;

import Controller.DataAcessObjects.CaixaDAO;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Models.*;
import Models.Usuarios.Caixa;
import Models.Usuarios.Cliente;
import Models.Usuarios.Gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.GerenteDAO;

public class CadastrarUser implements ActionListener {

    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField loginField;
    private JPasswordField senhaField;
    private JComboBox<String> cargoBox;

    public CadastrarUser(JTextField nome, JTextField cpf, JTextField login, JPasswordField senha, JComboBox<String> cargo) {
        this.nomeField = nome;
        this.cpfField = cpf;
        this.loginField = login;
        this.senhaField = senha;
        this.cargoBox = cargo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());
        String cargo = (String) cargoBox.getSelectedItem();

        
        try {
            switch (cargo) {
                case "Cliente" ->{
                     Cliente novoUsuario = new Cliente(login, senha, nome, cpf);
                     ClienteDAO clienteDAO = new ClienteDAO();
                     clienteDAO.adicionarNovoCliente(novoUsuario);
                    }
                case "Gerente" ->{
                     Gerente novoGerente = new Gerente(login, senha, nome, cpf);
                     GerenteDAO gerenteDAO = new GerenteDAO();
                     gerenteDAO.adicionarNovoGerente(novoGerente);
                    }
                case "Caixa" ->{
                     Caixa novoCaixa = new Caixa(login, senha, nome, cpf);
                     CaixaDAO caixaDAO = new CaixaDAO();
                     caixaDAO.adicionarNovoCaixa(novoCaixa);
                    }
               
                default -> {
                    JOptionPane.showMessageDialog(null, "Cargo inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (CPFException | CadastroException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
