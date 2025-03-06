package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Usuarios.Caixa;
import View.HomeScreen.HomeCaixa;

public class DepositoCaixa  extends Screen {
    private JTextField destinoCpfField,origemCpfField, valorField;
    private JPasswordField senhaField;
    private JButton transferButton, cancelButton;
    private Caixa   caixa;

    public DepositoCaixa(Caixa caixa) {
        this.caixa = caixa;
        initialize();
    }
    
    private void initialize() {
        tela.setTitle("Transferência");
        tela.setSize(400, 250);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.add(new JLabel("CPF da conta origem:"));
        origemCpfField = new JTextField();
        panel.add(origemCpfField);
        

        panel.add(new JLabel("Valor:"));
        valorField = new JTextField();
        panel.add(valorField);
        
        panel.add(new JLabel("Confirme sua senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);
        
        transferButton = new JButton("Transferir");
        transferButton.addActionListener(e -> {
            // Transferir
            ClienteDAO clienteDAO = new ClienteDAO();
            String cpfOrigem = origemCpfField.getText();
            Double valorTrans = Double.parseDouble(valorField.getText());
            String senha = senhaField.getText();
            boolean sucess = clienteDAO.realizarDeposito(cpfOrigem, valorTrans,senha );
            
            if (sucess) {
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Transferência não realizada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            tela.dispose();
            Screen home = new HomeCaixa(caixa.getCpf());
            home.show();
        });
        cancelButton = new JButton("Cancelar");
        panel.add(transferButton);
        panel.add(cancelButton);
        tela.add(panel);
        cancelButton.addActionListener(e -> tela.dispose());
    }
}
