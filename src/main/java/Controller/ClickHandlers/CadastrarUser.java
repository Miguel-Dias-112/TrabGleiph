package Controller.ClickHandlers;

import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.DataAcessObjects.ClienteDao;

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
            // Verifica se o CPF está cadastrado
            /*
            if (CPF.isCPFCadastrado(cpf)) {
                JOptionPane.showMessageDialog(null, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            */
            switch (cargo) {
                case "Cliente" ->{
                     Cliente novoUsuario = new Cliente(login, senha, nome, cpf);
                     ClienteDao clienteDao = new ClienteDao();
                     clienteDao.adicionarNovoUsuario(novoUsuario);
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
