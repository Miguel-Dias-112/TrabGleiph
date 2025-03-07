/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.PopUps;

import Controller.DataAcessObjects.CreditoDAO;
import Models.Bank.Credito;
import Models.Usuarios.Cliente;
import View.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreditoClienteScreen extends Screen {
    private JTextField valorField;
    private CreditoDAO creditoDAO;
    private Cliente cliente;

    public CreditoClienteScreen(Cliente cliente) {
        this.cliente = cliente;
        creditoDAO = new CreditoDAO();
        initialize();
    }

    private void initialize() {
        tela = new JFrame("Solicitar Crédito");
        tela.setSize(400, 200);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        valorField = new JTextField();

        formPanel.add(new JLabel("Valor do Crédito:"));
        formPanel.add(valorField);

        JButton solicitarButton = new JButton("Solicitar");
        solicitarButton.addActionListener(this::solicitarCredito);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(solicitarButton, BorderLayout.SOUTH);

        tela.add(mainPanel);
    }

    private void solicitarCredito(ActionEvent e) {
        try {
            double valor = Double.parseDouble(valorField.getText().trim());

            Credito novoCredito = new Credito(cliente.getId(), valor);
            creditoDAO.adicionarCredito(novoCredito);
            
            JOptionPane.showMessageDialog(tela, "Crédito solicitado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            tela.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(tela, "Valor deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void show() {
        tela.setVisible(true);
    }
}
