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
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;
import Utils.Exception.TransacaoException;
import View.Screen;
import View.HomeScreen.HomeCliente;

public class TransferenciaClienteHandle implements ActionListener {
    private JTextField destinoCpField, valorField;
    private JPasswordField senhaField;
    private Screen tela;
    private Cliente cliente;

    public TransferenciaClienteHandle(Screen telaAtual, JTextField cpfDestino, JTextField valor, JPasswordField senha,
            Cliente cliente) {

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
        String senha = String.valueOf(senhaField.getPassword());

        try {
            double valorTrans = Double.parseDouble(valorField.getText().trim());

            // Bloqueia transações acima de 1 milhão para a operação do cliente
            if (valorTrans > 1000000) {
                JOptionPane.showMessageDialog(
                        null,
                        "Transações acima de 1 milhão não são permitidas nesta operação. Veja com seu gerente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            clienteDAO.realizarTransferencia(cpfOrigem, cpfDestino, valorTrans, senha);
            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            tela.close();
            HomeCliente home = new HomeCliente(cpfOrigem);
            home.show();
        } catch (CPFException | TransacaoException | LoginException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Insira um valor válido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
