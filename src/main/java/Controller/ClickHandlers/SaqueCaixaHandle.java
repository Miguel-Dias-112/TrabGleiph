/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.ClickHandlers;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Usuarios.Caixa;
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;
import View.Screen;
import View.HomeScreen.HomeCaixa;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaqueCaixaHandle implements ActionListener {
    private JTextField origemCpfField, valorField;
    private JPasswordField senhaField;
    private Screen tela;
    private Caixa caixa;

    public SaqueCaixaHandle(Screen telaAtual, JTextField cpf, JTextField valor, JPasswordField senha, Caixa caixa) {
        this.origemCpfField = cpf;
        this.valorField = valor;
        this.senhaField = senha;
        this.tela = telaAtual;
        this.caixa = caixa;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Transferir
        ClienteDAO clienteDAO = new ClienteDAO();
        String cpfOrigem = origemCpfField.getText();
        String senha = String.valueOf(senhaField.getPassword());

        if (cpfOrigem.isEmpty() || valorField.getText().trim().isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valorSaque;

        try {
            valorSaque = Double.parseDouble(valorField.getText().trim());
            if (valorSaque <= 0) {
                JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Insira um valor válido para o saque!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            valorSaque = Double.parseDouble(valorField.getText().trim());
            if (valorSaque <= 0) {
                JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Bloqueia saques acima de 1 milhão para a operação de caixa
            if (valorSaque > 1000000) {
                JOptionPane.showMessageDialog(
                        null,
                        "Saques acima de 1 milhão não são permitidos nesta operação. Veja com seu gerente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Insira um valor válido para o saque!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

}
