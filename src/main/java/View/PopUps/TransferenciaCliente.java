/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.PopUps;


import javax.swing.*;

import Controller.ClickHandlers.TransferenciaClienteHandle;
import Controller.ClickHandlers.trocarScreen;
import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;
import View.Screen;
import View.HomeScreen.HomeCliente;

import java.awt.*;

public class TransferenciaCliente extends Screen {
    private JTextField destinoCpfField, valorField;
    private JPasswordField senhaField;
    private JButton transferButton, cancelButton;
    private Cliente cliente;
  
    public TransferenciaCliente(Cliente cliente) throws CPFException {
        this.cliente = cliente;
        initialize();
    }
    
    private void initialize() throws CPFException {
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
        transferButton.addActionListener(
           new TransferenciaClienteHandle(this,  destinoCpfField, valorField, senhaField, cliente)
        );
        cancelButton = new JButton("Cancelar");
        panel.add(transferButton);
        panel.add(cancelButton);
        tela.add(panel);
        cancelButton.addActionListener(
            new trocarScreen(this, new HomeCliente(cliente.getCpf()))
        );  
      }
    
    
}