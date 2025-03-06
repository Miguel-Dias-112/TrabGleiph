package Controller.ClickHandlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Caixa;
import Models.Cliente;
import View.Screen;
import View.HomeScreen.HomeCaixa;
import View.HomeScreen.HomeCliente;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Transferir
        ClienteDAO clienteDAO = new ClienteDAO();
        String cpfOrigem = origemCpfField.getText();
        String cpfDestino = destinoCpField.getText();
        Double valorTrans = Double.parseDouble(valorField.getText());
        String senha = senhaField.getText();

        boolean sucess = clienteDAO.realizarTransferencia(cpfOrigem,cpfDestino, valorTrans,senha );
        
        if (sucess) {
            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Transferência não realizada, verifique os dados e tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tela.close();
        HomeCaixa home = new HomeCaixa(caixa);
        home.show();

    }
     
}
