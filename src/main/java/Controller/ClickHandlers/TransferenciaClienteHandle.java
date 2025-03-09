/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.ClickHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Usuarios.Cliente;
import Utils.Checkers.CpfChecker;
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;
import Utils.Exception.TransacaoException;
import View.Screen;
import View.HomeScreen.HomeCliente;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferenciaClienteHandle implements ActionListener {
    private JTextField destinoCpField, valorField;
    private JPasswordField senhaField;
    private Screen tela;
    private Cliente cliente;
    public TransferenciaClienteHandle(Screen telaAtual, JTextField cpfDestino, JTextField valor, JPasswordField senha, Cliente cliente) {
        
        this.destinoCpField = cpfDestino;
        this.valorField = valor;
        this.senhaField = senha;
        this.tela = telaAtual;  
        this.cliente = cliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Transferir
        ClienteDAO clienteDAO = new ClienteDAO();
        String cpfOrigem = cliente.getCpf();
        String cpfDestino = destinoCpField.getText();
        String senha = senhaField.getText();
        
       
        
       
        double valorTrans;
        
        try {
            valorTrans = Double.parseDouble(valorField.getText().trim());
            if (valorTrans <= 0) {
                JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Insira um valor válido para a transferência!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            clienteDAO.realizarTransferencia(cpfOrigem,cpfDestino, valorTrans,senha );
            JOptionPane.showMessageDialog(null, "Transferencia realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            tela.close();

            HomeCliente home = new HomeCliente(cpfOrigem);
            home.show();
        } catch (CPFException | TransacaoException | LoginException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
