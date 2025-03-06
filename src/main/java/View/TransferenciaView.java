package View;


import javax.swing.*;
import Controller.DataAcessObjects.ClienteDAO;
import Models.Cliente;
import View.HomeScreen.HomeCliente;

import java.awt.*;

public class TransferenciaView extends Screen {
    private JTextField destinoCpfField, valorField;
    private JPasswordField senhaField;
    private JButton transferButton, cancelButton;
    private Cliente cliente;
  
    public TransferenciaView(Cliente cliente) {
        this.cliente = cliente;
        initialize();
    }
    
    private void initialize() {
        tela.setTitle("Transferência");
        tela.setSize(400, 250);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.add(new JLabel("CPF da conta destino:"));
        destinoCpfField = new JTextField();
        panel.add(destinoCpfField);
        
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
            String cpfOrigem = cliente.getCpf();
            String cpfDestino = destinoCpfField.getText();
            Double valorTrans = Double.parseDouble(valorField.getText());
            String senha = senhaField.getText();

            boolean sucess = clienteDAO.realizarTransferencia(cpfOrigem,cpfDestino, valorTrans,senha );
            
            if (sucess) {
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            tela.dispose();
            HomeCliente home = new HomeCliente(cliente);
            home.show();
        });
        cancelButton = new JButton("Cancelar");
        panel.add(transferButton);
        panel.add(cancelButton);
        tela.add(panel);
        cancelButton.addActionListener(e -> tela.dispose());
    }
    
    
}