package View.HomeScreen;

import javax.swing.*;

import Controller.ClickHandlers.trocarScreen;
import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;
import View.LoginScreen;
import View.Screen;
import View.TransferenciaCliente;
import Models.Bank.Transacao;
import Models.Usuarios.Cliente;
import View.DeletarContaScreen;
import View.EditarScreen;
import View.InvestimentosScreen;
import View.RegisterScreen;
import java.util.List;
import java.awt.*;
public class HomeCliente extends Screen {

    private JPanel menuSuperior;
    private JPanel conteudoCentral;
    private JLabel saldoLabel;
    private JTextArea historico ;
    private Cliente Cliente;

    public HomeCliente(String cpf) {
        ClienteDAO clientes = new ClienteDAO();
        this.Cliente = clientes.findByCpf(cpf);

        double saldo = Cliente.getSaldo();
        saldoLabel = new JLabel("R$ "+ saldo);
        historico = new JTextArea(10, 30);
        historico.setText(Cliente.getConta().consultarExtrato());
    }
    private void configuraTela() {
        tela = new JFrame("BANCO JAVA - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(800, 600);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);
    }
    private void desenhaMenuSuperior() {
        menuSuperior = new JPanel();
        menuSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuSuperior.setBackground(Color.LIGHT_GRAY);
        
        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new trocarScreen(this, new LoginScreen()));
        menuSuperior.add(botaoSair);
        
        JButton botaoEditar = new JButton("Editar Usuário");
        botaoEditar.addActionListener(new trocarScreen(this,new EditarScreen(Cliente)));
        menuSuperior.add(botaoEditar);
        
        JButton botaoTransferir = new JButton("Transferir");
        botaoTransferir.addActionListener(new trocarScreen(this, new TransferenciaCliente(Cliente)));
        menuSuperior.add(botaoTransferir);

        JButton botaoInvestimentos = new JButton("Ver Investimentos");
        botaoInvestimentos.addActionListener(new trocarScreen(this, new InvestimentosScreen(Cliente)));
        menuSuperior.add(botaoInvestimentos);
        
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(new trocarScreen(this,new DeletarContaScreen(Cliente)));
        menuSuperior.add(botaoDeletar);

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
        historicoPanel.add(historico);
        conteudoCentral.add(saldoPanel);
        conteudoCentral.add(historicoPanel);
        tela.add(conteudoCentral, BorderLayout.CENTER);
    }

    @Override
    public void show() {
        configuraTela();
        desenhaMenuSuperior();
        desenhaConteudoCentral();
        tela.setVisible(true);
    }
}
