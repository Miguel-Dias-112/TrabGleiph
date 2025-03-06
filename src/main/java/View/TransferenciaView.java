package View;


import javax.swing.*;

import Models.Cliente;
import java.awt.*;

public class TransferenciaView extends Screen {
    private JTextField destinoCpfField, valorField;
    private JPasswordField senhaField;
    private JButton transferButton, cancelButton;
    private Cliente cliente;
    public TransferenciaView() {
        initialize();
    }
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
        cancelButton = new JButton("Cancelar");
        panel.add(transferButton);
        panel.add(cancelButton);
        tela.add(panel);
        cancelButton.addActionListener(e -> tela.dispose());
    }
    
    // private void performTransfer() {
    //     String destinoCpf = destinoCpfField.getText();
    //     String valorText = valorField.getText();
    //     String senha = new String(senhaField.getPassword());
    //     try {
    //         double valor = Double.parseDouble(valorText);
    //         User destUser = users.get(destinoCpf);
    //         if (destUser == null) {
    //             JOptionPane.showMessageDialog(this, "Conta destino não encontrada.");
    //             return;
    //         }
    //         if (!(destUser instanceof Cliente)) {
    //             JOptionPane.showMessageDialog(this, "Conta destino inválida.");
    //             return;
    //         }
    //         Cliente destCliente = (Cliente) destUser;
    //         bankingController.transferir(cliente, destCliente.getConta(), valor, senha);
    //         JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso.");
    //         dispose();
    //     } catch (NumberFormatException ex) {
    //         JOptionPane.showMessageDialog(this, "Valor inválido.");
    //     } catch (Exception ex) {
    //         JOptionPane.showMessageDialog(this, ex.getMessage());
    //     }
    // }
}