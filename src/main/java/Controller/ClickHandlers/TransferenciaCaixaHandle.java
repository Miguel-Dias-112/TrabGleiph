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
import Utils.Exception.TransacaoException;
import View.Screen;
import View.HomeScreen.HomeCaixa;

public class TransferenciaCaixaHandle implements ActionListener {
    private JTextField destinoCpField,
    origemCpfField, valorField;
    private JPasswordField senhaField;
    private Screen tela;
    private Caixa caixa;
    public TransferenciaCaixaHandle(Screen telaAtual,JTextField cpf, JTextField cpfDestino, JTextField valor, JPasswordField senha, Caixa caixa) {
        this.origemCpfField = cpf;
        this.destinoCpField = cpfDestino;
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
        String cpfDestino = destinoCpField.getText();
        String senha = String.valueOf(senhaField.getPassword());

        try {
            double valorTrans = Double.parseDouble(valorField.getText().trim());
            clienteDAO.realizarTransferencia(cpfOrigem, cpfDestino, valorTrans, senha);
            JOptionPane.showMessageDialog(null, "Transferencia realizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            tela.close();
            HomeCaixa home = new HomeCaixa(caixa.getCpf());
            home.show();
        } catch (CPFException | TransacaoException | LoginException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Insira um valor valido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
     
}
