package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import Controller.DataAcessObjects.ClienteDAO;
import View.Screen;
import View.Auth.LoginScreen;
import View.PopUps.DeletarContaScreen;
import View.PopUps.EditarScreen;
import View.PopUps.InvestimentosScreen;
import View.PopUps.TransferenciaCliente;
import View.PopUps.CreditoClienteScreen;
import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;

import java.awt.*;

import java.util.logging.Level;
import java.util.logging.Logger;
public class HomeCliente extends Screen {

    private JPanel menuSuperior;
    private JPanel conteudoCentral;
    private JLabel saldoLabel;
    private JTextArea historico;
    private Cliente Cliente;

    public HomeCliente(String cpf) throws CPFException {
        ClienteDAO clientes = new ClienteDAO();
        this.Cliente = clientes.findByCpf(cpf);

        double saldo = Cliente.getSaldo();
        saldoLabel = new JLabel(String.format("R$ %.2f", saldo));
        historico = new JTextArea(10, 30);
        historico.setText(Cliente.getConta().consultarExtrato());
        historico.setLineWrap(true);
        historico.setWrapStyleWord(true);
        historico.setCaretPosition(0);
        historico.setEditable(false); 
    }

    private void configuraTela() {
        tela = new JFrame("BANCO JAVA - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(800, 600);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);
    }
    private void desenhaMenuSuperior() throws CPFException {
        menuSuperior = new JPanel();
        menuSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuSuperior.setBackground(Color.LIGHT_GRAY);

        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new trocarScreen(this, new LoginScreen()));
        menuSuperior.add(botaoSair);

        JButton botaoEditar = new JButton("Editar Usuário");
        botaoEditar.addActionListener(new trocarScreen(this, new EditarScreen(Cliente)));
        menuSuperior.add(botaoEditar);

        JButton botaoTransferir = new JButton("Transferir");
        botaoTransferir.addActionListener(new trocarScreen(this, new TransferenciaCliente(Cliente)));
        menuSuperior.add(botaoTransferir);

        JButton botaoInvestimentos = new JButton("Ver Investimentos");
        botaoInvestimentos.addActionListener(new trocarScreen(this, new InvestimentosScreen(Cliente)));
        menuSuperior.add(botaoInvestimentos);

        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(new trocarScreen(this, new DeletarContaScreen(Cliente)));
        menuSuperior.add(botaoDeletar);

        JButton solicitarCreditoButton = new JButton("Solicitar Crédito");
        solicitarCreditoButton.addActionListener(e -> {
            new CreditoClienteScreen(Cliente).show();
        });
        menuSuperior.add(solicitarCreditoButton);

        tela.add(menuSuperior, BorderLayout.NORTH);
    }

    private void desenhaConteudoCentral() {
        conteudoCentral = new JPanel();
        conteudoCentral.setLayout(new GridLayout(1, 2, 20, 20));
        conteudoCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel saldoPanel = new JPanel();
        saldoPanel.setBorder(BorderFactory.createTitledBorder("Saldo"));
        saldoPanel.add(saldoLabel);

        JPanel historicoPanel = new JPanel();
        historicoPanel.setBorder(BorderFactory.createTitledBorder("Histórico de Transações"));
        historicoPanel.setLayout(new BorderLayout()); 

        JScrollPane scrollPane = new JScrollPane(historico);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        historicoPanel.add(scrollPane, BorderLayout.CENTER);

        conteudoCentral.add(saldoPanel);
        conteudoCentral.add(historicoPanel);

        tela.add(conteudoCentral, BorderLayout.CENTER);
    }

    @Override
    public void show() {
        configuraTela();
        try {
            desenhaMenuSuperior();
        } catch (CPFException ex) {
            Logger.getLogger(HomeCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        desenhaConteudoCentral();
        tela.setVisible(true);
    }
}
