package View;

import Controller.ClickHandlers.trocarScreen;
import Models.Cliente;
import Models.Login;
import View.HomeScreen.HomeCliente;

import javax.swing.*;
import java.awt.*;

public class InvestimentosScreen extends Screen {

    private JList<String> listaInvestimentos;
    private DefaultListModel<String> modeloInvestimentos;

    private void configuraTela() {
        tela = new JFrame("BANCO JAVA - Visualizar Investimentos");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(600, 450);
        tela.setLayout(new GridBagLayout());
        tela.setLocationRelativeTo(null);
    }

    private void posicionaElemento(JComponent elemento, int x, int y, Insets margens) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = margens;
        tela.add(elemento, gbc);
    }

    private void desenhaTitulo() {
        JLabel titulo = new JLabel("Visualizar Investimentos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        posicionaElemento(titulo, 0, 0, new Insets(40, 0, 40, 0));
    }

    private void desenhaListaInvestimentos() {
        modeloInvestimentos = new DefaultListModel<>(); // recebe a lista do gson
        modeloInvestimentos.addElement("Investimento A - Ano: 2025 - Retorno: 10%");
        modeloInvestimentos.addElement("Investimento B - Ano: 2026 - Retorno: 12%");
        modeloInvestimentos.addElement("Investimento C - Ano: 2027 - Retorno: 15%");
        modeloInvestimentos.addElement("Investimento D - Ano: 2027 - Retorno: 15%");
        modeloInvestimentos.addElement("Investimento E - Ano: 2027 - Retorno: 15%");
        modeloInvestimentos.addElement("Investimento F - Ano: 2027 - Retorno: 15%");
        modeloInvestimentos.addElement("Investimento G - Ano: 2027 - Retorno: 15%");
        modeloInvestimentos.addElement("Investimento H - Ano: 2027 - Retorno: 15%");
        modeloInvestimentos.addElement("Investimento I - Ano: 2027 - Retorno: 15%");


        listaInvestimentos = new JList<>(modeloInvestimentos);
        listaInvestimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaInvestimentos.setVisibleRowCount(8);

        JScrollPane scrollPane = new JScrollPane(listaInvestimentos);
        scrollPane.setPreferredSize(new Dimension(240, 400));

        posicionaElemento(scrollPane, 0, 1, new Insets(0, 0, 20, 0));
    }

    private void desenhaBotoes() {
        JButton investirButton = new JButton("Investir");
        investirButton.addActionListener(e -> {
            String selecionado = listaInvestimentos.getSelectedValue();
            if (selecionado == null || selecionado.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "{erro de select}",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String valor = JOptionPane.showInputDialog("Quanto deseja investir?");
            if (valor != null && !valor.trim().isEmpty()) {
                try {
                    double valorNumerico = Double.parseDouble(valor);
                    if (valorNumerico <= 0) {
                        JOptionPane.showMessageDialog(
                            null,
                            "ERRO: valor inválido",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    JOptionPane.showMessageDialog(
                        null,
                        "Investimento em \"" + selecionado + "\" de R$ " + valor + " realizado com sucesso.",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        null,
                        "ERRO: apenas números ou formato inválido",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JButton cancelarButton = new JButton("Voltar");
        cancelarButton.addActionListener(e -> tela.dispose());

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(investirButton);
        panelBotoes.add(cancelarButton);

        posicionaElemento(panelBotoes, 0, 2, new Insets(20, 0, 0, 0));
    }

    @Override
    public void show() {
        configuraTela();
        desenhaTitulo();
        desenhaListaInvestimentos();
        desenhaBotoes();
        tela.setVisible(true);
    }
}