/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.PopUps;

import Controller.DataAcessObjects.InvestimentoDAO;
import Models.Bank.Investimento;
import Models.Usuarios.Gerente;
import View.Screen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InvestimentosGerenteScreen extends Screen {
    private JTextField nomeField, anoField, retornoField;
    private JList<Investimento> listaInvestimentos;
    private DefaultListModel<Investimento> modeloLista;
    private InvestimentoDAO investimentoDAO;

    public InvestimentosGerenteScreen(Gerente gerente) {
        investimentoDAO = new InvestimentoDAO();
        initialize();
    }

    private void initialize() {
        tela = new JFrame("Gerenciar Investimentos");
        tela.setSize(600, 400);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        nomeField = new JTextField();
        anoField = new JTextField();
        retornoField = new JTextField();

        formPanel.add(new JLabel("Nome do Investimento:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Ano de Vencimento:"));
        formPanel.add(anoField);
        formPanel.add(new JLabel("Retorno (Exemplo: 10.5):"));
        formPanel.add(retornoField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton addButton = new JButton("Adicionar");
        JButton removeButton = new JButton("Remover");
        
        addButton.addActionListener(this::adicionarInvestimento);
        removeButton.addActionListener(this::removerInvestimento);
        
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        modeloLista = new DefaultListModel<>();
        atualizarListaInvestimentos();
        
        listaInvestimentos = new JList<>(modeloLista);
        listaInvestimentos.setCellRenderer(new InvestimentoListRenderer());
        JScrollPane scrollPane = new JScrollPane(listaInvestimentos);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        tela.add(mainPanel);
    }

    private void adicionarInvestimento(ActionEvent e) {
        try {
            String nome = nomeField.getText().trim();
            int ano = Integer.parseInt(anoField.getText().trim());
            double retorno = Double.parseDouble(retornoField.getText().trim());

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(tela, "Nome do investimento é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Investimento novoInvestimento = new Investimento(nome, ano, retorno);
            investimentoDAO.adicionarInvestimento(novoInvestimento);
            
            atualizarListaInvestimentos();
            limparCampos();
            
            JOptionPane.showMessageDialog(tela, "Investimento adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(tela, "Ano e retorno devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerInvestimento(ActionEvent e) {
        Investimento selecionado = listaInvestimentos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um investimento para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            tela, 
            "Tem certeza que deseja remover o investimento: " + selecionado.getNome() + "?",
            "Confirmar Remoção",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            investimentoDAO.removerInvestimento(selecionado.getNome());
            atualizarListaInvestimentos();
        }
    }

    private void atualizarListaInvestimentos() {
        modeloLista.clear();
        investimentoDAO.findAll().forEach(modeloLista::addElement);
    }

    private void limparCampos() {
        nomeField.setText("");
        anoField.setText("");
        retornoField.setText("");
    }

    private static class InvestimentoListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Investimento) {
                Investimento inv = (Investimento) value;
                setText(String.format("%s - Vencimento: %d - Retorno: %.2f%%", 
                    inv.getNome(), inv.getAno(), inv.getRetorno()));
            }
            return this;
        }
    }

    @Override
    public void show() {
        tela.setVisible(true);
    }
}