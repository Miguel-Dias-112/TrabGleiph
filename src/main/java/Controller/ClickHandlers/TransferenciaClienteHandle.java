package Controller.ClickHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Caixa;
import Models.Cliente;
import View.Screen;
import View.HomeScreen.HomeCaixa;
import View.HomeScreen.HomeCliente;

public class TransferenciaClienteHandle implements ActionListener {
    private JTextField destinoCpField, valorField;
    private JPasswordField senhaField;
    private Screen tela;
    private Cliente cliente;
    public TransferenciaClienteHandle(Screen telaAtual,JTextField cpf, JTextField cpfDestino, JTextField valor, JPasswordField senha, Cliente cliente) {
        
        this.destinoCpField = cpfDestino;
        this.valorField = valor;
        this.senhaField = senha;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Transferir
        ClienteDAO clienteDAO = new ClienteDAO();
        String cpfOrigem = cliente.getCpf();
        String cpfDestino = destinoCpField.getText();
        Double valorTrans = Double.parseDouble(valorField.getText());
        String senha = senhaField.getText();
        boolean sucess = clienteDAO.realizarTransferencia(cpfOrigem,cpfDestino, valorTrans,senha );
        
        if (sucess) {
            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        tela.close();
        HomeCliente home = new HomeCliente(cliente);
        home.show();

    }
}
