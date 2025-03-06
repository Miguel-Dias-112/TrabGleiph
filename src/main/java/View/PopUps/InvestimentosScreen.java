package View.PopUps;

import Controller.ClickHandlers.trocarScreen;
import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.InvestimentoDAO;
import Models.Bank.Investimento;
import Models.Usuarios.Cliente;
import Utils.Persistence.InvestimentoPersist;
import View.Screen;
import View.HomeScreen.HomeCliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InvestimentosScreen extends Screen {

    private JList<String> listaInvestimentos;
    private DefaultListModel<String> modeloInvestimentos;
    private InvestimentoPersist investimentoDAO;
    private Cliente cliente;

    public InvestimentosScreen(Cliente cliente) {
        investimentoDAO = new InvestimentoDAO();
        this.cliente = cliente;
    }

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
        modeloInvestimentos = new DefaultListModel<>();

        List<Investimento> investimentos = investimentoDAO.findAll();
        for (Investimento investimento : investimentos) {
            modeloInvestimentos.addElement(investimento.toString());
        }

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
                        "Selecione um investimento para continuar.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
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
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ClienteDAO clienteDAO = new ClienteDAO();
                    boolean sucesso = clienteDAO.realizarInvestimento(
                            cliente.getCpf(),
                            valorNumerico,
                            selecionado 
                    );

                    if (sucesso) {
                        this.cliente = clienteDAO.findByCpf(cliente.getCpf());

                        JOptionPane.showMessageDialog(
                                null,
                                "Investimento em \"" + selecionado + "\" de R$ " + valorNumerico + " realizado com sucesso.",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        new trocarScreen(this, new HomeCliente(cliente.getCpf())).actionPerformed(e);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Erro: Saldo insuficiente ou cliente não encontrado",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: apenas números ou formato inválido",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelarButton = new JButton("Voltar");
        cancelarButton.addActionListener(
                new trocarScreen(this, new HomeCliente(cliente.getCpf())));

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