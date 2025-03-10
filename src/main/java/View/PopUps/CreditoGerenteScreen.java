package View.PopUps;

import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.CreditoDAO;
import Models.Bank.Credito;
import Models.Usuarios.Cliente;
import Models.Usuarios.Gerente;
import Utils.Exception.CPFException;
import View.Screen;
import java.util.List;

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
    private JTextArea extratoArea;

    public CreditoGerenteScreen(Gerente gerente) {
        creditoDAO = new CreditoDAO();
        clienteDAO = new ClienteDAO();
        initialize();
    }

    private void initialize() {
        tela = new JFrame("Gerenciar Créditos");
        tela.setSize(1000, 600);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);

        // Painel Esquerdo - Lista de Créditos
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeloLista = new DefaultListModel<>();
        atualizarListaCreditos();

        listaCreditos = new JList<>(modeloLista);
        listaCreditos.setCellRenderer(new CreditoListRenderer());
        listaCreditos.addListSelectionListener(e -> atualizarExtrato());

        JScrollPane scrollPane = new JScrollPane(listaCreditos);
        scrollPane.setPreferredSize(new Dimension(380, 500));

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

        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Painel Direito - Extrato
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        extratoArea = new JTextArea();
        extratoArea.setEditable(false);
        extratoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane extratoScroll = new JScrollPane(extratoArea);

        rightPanel.add(new JLabel("Histórico Financeiro do Cliente:"), BorderLayout.NORTH);
        rightPanel.add(extratoScroll, BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        tela.add(splitPane);
    }

    private void atualizarListaCreditos() {
        modeloLista.clear();
        List<Credito> listaAtualizada = creditoDAO.findAll();

        for (Credito credito : listaAtualizada) {
            modeloLista.addElement(credito);
        }
    }

    private void atualizarExtrato() {
        Credito selecionado = listaCreditos.getSelectedValue();
        if (selecionado != null) {
            Cliente cliente = clienteDAO.findById(selecionado.getClienteId());
            if (cliente != null) {
                extratoArea.setText(cliente.getConta().consultarExtrato());
            } else {
                extratoArea.setText("Cliente não encontrado!");
            }
        } else {
            extratoArea.setText("");
        }
    }

    private void aprovarCredito(ActionEvent e) throws CPFException {
        Credito selecionado = listaCreditos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um crédito para aprovar!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = clienteDAO.findById(selecionado.getClienteId());
        if (cliente == null) {
            JOptionPane.showMessageDialog(tela, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String senha = JOptionPane.showInputDialog(tela, "Digite a senha do cliente para confirmação:");
        if (senha != null && senha.equals(cliente.getSenha())) {
            try {
                selecionado.setStatus("Aprovado");
                creditoDAO.atualizarCredito(selecionado);
                clienteDAO.aprovarCredito(cliente.getIdConta(), selecionado.getValor());
                atualizarListaCreditos();
                atualizarExtrato();

                JOptionPane.showMessageDialog(tela, "Crédito aprovado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tela, "Erro ao processar: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tela, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recusarCredito(ActionEvent e) {
        Credito selecionado = listaCreditos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um crédito para recusar!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                tela,
                "Confirmar recusa do crédito: " + selecionado.getId() + "?",
                "Confirmar Recusa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            selecionado.setStatus("Recusado");
            creditoDAO.atualizarCredito(selecionado);
            atualizarListaCreditos();
            JOptionPane.showMessageDialog(tela, "Crédito recusado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static class CreditoListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Credito) {
                Credito credito = (Credito) value;
                setText(String.format("[%s] %s - R$%.2f",
                        credito.getStatus(),
                        credito.getId(),
                        credito.getValor()));

                switch (credito.getStatus()) {
                    case "Aprovado":
                        setBackground(new Color(220, 255, 220));
                        break;
                    case "Recusado":
                        setBackground(new Color(255, 220, 220));
                        break;
                    default:
                        setBackground(new Color(240, 240, 240));
                }
            }
            return this;
        }
    }

    @Override
    public void show() {
        tela.setVisible(true);
    }
}