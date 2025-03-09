/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.PopUps;

import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.CreditoDAO;
import Models.Bank.Credito;
import Models.Usuarios.Cliente;
import Models.Usuarios.Gerente;
import Utils.Exception.CPFException;
import View.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditoGerenteScreen extends Screen {
    private JList<Credito> listaCreditos;
    private DefaultListModel<Credito> modeloLista;
    private CreditoDAO creditoDAO;
    private ClienteDAO clienteDAO;

    public CreditoGerenteScreen(Gerente gerente) {
        creditoDAO = new CreditoDAO();
        clienteDAO = new ClienteDAO();
        initialize();
    }

    private void initialize() {
        tela = new JFrame("Gerenciar Créditos");
        tela.setSize(600, 400);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeloLista = new DefaultListModel<>();
        atualizarListaCreditos();
        
        listaCreditos = new JList<>(modeloLista);
        listaCreditos.setCellRenderer(new CreditoListRenderer());
        JScrollPane scrollPane = new JScrollPane(listaCreditos);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton aprovarButton = new JButton("Aprovar");
        JButton recusarButton = new JButton("Recusar");
        
        aprovarButton.addActionListener(e -> {
            try {
                this.aprovarCredito(e);
            } catch (CPFException ex) {
                Logger.getLogger(CreditoGerenteScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        recusarButton.addActionListener(this::recusarCredito);
        
        buttonPanel.add(aprovarButton);
        buttonPanel.add(recusarButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        tela.add(mainPanel);
    }

    private void aprovarCredito(ActionEvent e) throws CPFException {
        Credito selecionado = listaCreditos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um crédito para aprovar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = clienteDAO.findById(selecionado.getClienteId());
        String senha = JOptionPane.showInputDialog(tela, "Digite a senha do cliente para confirmação:");
        if (senha != null && senha.equals(cliente.getSenha())) {
            selecionado.setStatus("Aprovado");
            creditoDAO.atualizarCredito(selecionado);
            clienteDAO.aprovarCredito(cliente.getIdConta(), selecionado.getValor());
            atualizarListaCreditos();
            JOptionPane.showMessageDialog(tela, "Crédito aprovado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(tela, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recusarCredito(ActionEvent e) {
        Credito selecionado = listaCreditos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um crédito para recusar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        selecionado.setStatus("Recusado");
        creditoDAO.atualizarCredito(selecionado);
        atualizarListaCreditos();
        JOptionPane.showMessageDialog(tela, "Crédito recusado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarListaCreditos() {
        modeloLista.clear();
        creditoDAO.findAll().forEach(modeloLista::addElement);
    }

    private static class CreditoListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Credito) {
                Credito credito = (Credito) value;
                setText(String.format("ID: %s - Valor: %.2f - Status: %s", 
                    credito.getId(), credito.getValor(), credito.getStatus()));
            }
            return this;
        }
    }

    @Override
    public void show() {
        tela.setVisible(true);
    }
}
