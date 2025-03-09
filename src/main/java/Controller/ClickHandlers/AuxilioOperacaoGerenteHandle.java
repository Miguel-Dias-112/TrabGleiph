package Controller.ClickHandlers;

import Controller.DataAcessObjects.ClienteDAO;
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;
import Utils.Exception.TransacaoException;
import View.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuxilioOperacaoGerenteHandle implements ActionListener {
    private final JComboBox<String> tipoOperacaoCombo;
    private final JTextField origemCpfField;
    private final JTextField destinoCpfField;
    private final JTextField valorField;
    private final JPasswordField senhaField;
    private final Screen tela;

    public AuxilioOperacaoGerenteHandle(
        Screen telaAtual,
        JComboBox<String> tipoOperacaoCombo,
        JTextField origemCpfField,
        JTextField destinoCpfField,
        JTextField valorField,
        JPasswordField senhaField
    ) {
        this.tela = telaAtual;
        this.tipoOperacaoCombo = tipoOperacaoCombo;
        this.origemCpfField = origemCpfField;
        this.destinoCpfField = destinoCpfField;
        this.valorField = valorField;
        this.senhaField = senhaField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClienteDAO clienteDAO = new ClienteDAO();
        String operacao = tipoOperacaoCombo.getSelectedItem().toString();
        String cpfOrigem = origemCpfField.getText().trim();
        String cpfDestino = destinoCpfField.getText().trim();
        String senha = new String(senhaField.getPassword()).trim();

        try {
            double valor = Double.parseDouble(valorField.getText().trim());
            
            if (operacao.equals("Transferência")) {
                clienteDAO.realizarTransferencia(cpfOrigem, cpfDestino, valor, senha);
            } else {
                clienteDAO.realizarSaque(cpfOrigem, valor, senha);
            }
            
            JOptionPane.showMessageDialog(
                tela.tela,
                "Operação realizada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            tela.close();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                tela.tela,
                "Valor inválido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (CPFException | LoginException | TransacaoException ex) {
            JOptionPane.showMessageDialog(
                tela.tela,
                ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}