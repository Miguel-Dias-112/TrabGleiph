package View.PopUps;

import Controller.ClickHandlers.EditarUser;
import Controller.ClickHandlers.trocarScreen;
import Models.Usuarios.Caixa;
import Models.Usuarios.Cliente;
import Models.Usuarios.Gerente;
import Models.Usuarios.Usuario;
import View.Screen;
import View.HomeScreen.HomeCaixa;
import javax.swing.*;
import java.awt.*;
import View.HomeScreen.HomeCliente;
import View.HomeScreen.HomeGerente;

public class EditarScreen extends Screen {
    private JTextField nomeField;
    private JTextField loginField;
    private JPasswordField senhaField;
    private Usuario user;
    private void configuraTela() {
        tela = new JFrame("BANCO JAVA");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(700, 550);
        tela.setLayout(new GridBagLayout());
        tela.setLocationRelativeTo(null);
    }

    private void posicionaElemento(JComponent elemento, int x, int y, Insets margens) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = margens;
        tela.add(elemento, gbc);
    }

    private void desenhaTitulo() {
        JLabel titulo = new JLabel("Editar Usuário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); 
        posicionaElemento(titulo, 0, 0, new Insets(40, 0, 40, 0));
    }

    private void desenhaMensagem() {
        JLabel messageLabel = new JLabel("Preencha os dados que deseja editar", SwingConstants.CENTER);
        posicionaElemento(messageLabel, 0, 1, new Insets(0, 0, 20, 0));
    }

    private void desenhaInputs() {
        JPanel panelUser = new JPanel();
        JLabel userLabel = new JLabel("Nome:  ");
        nomeField = new JTextField(20);
        panelUser.add(userLabel);
        panelUser.add(nomeField);

        JPanel panelLogin = new JPanel();
        JLabel loginLabel = new JLabel("Login:  ");
        loginField = new JTextField(20);
        panelLogin.add(loginLabel);
        panelLogin.add(loginField);

        JPanel panelSenha = new JPanel();
        JLabel senhaLabel = new JLabel("Senha: ");
        senhaField = new JPasswordField(20);
        panelSenha.add(senhaLabel);
        panelSenha.add(senhaField);

        posicionaElemento(panelUser, 0, 2, new Insets(0, 0, 10, 0));
        posicionaElemento(panelLogin, 0, 3, new Insets(0, 0, 10, 0));
        posicionaElemento(panelSenha, 0, 4, new Insets(0, 0, 10, 0));
    }

    private void desenhaBotoes() {
        JButton editarButton = new JButton("Editar");
        EditarUser editarUser = new EditarUser(nomeField, loginField, senhaField, user);
        editarButton.addActionListener(editarUser);
        
        JButton cancelarButton = new JButton("Cancelar");
        switch(user.getCargo()){
            case("Cliente") -> cancelarButton.addActionListener(new trocarScreen(this, new HomeCliente(user.getCpf())));
            case("Caixa") -> cancelarButton.addActionListener(new trocarScreen(this, new HomeCaixa(user.getCpf())));
            case("Gerente") -> cancelarButton.addActionListener(new trocarScreen(this, new HomeGerente(user.getCpf()))); 
        }

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(editarButton);
        panelBotoes.add(cancelarButton);
        posicionaElemento(panelBotoes, 0, 7, new Insets(20, 0, 0, 0));
    }
    public EditarScreen(Usuario user) {
        this.user = user;
    }
    @Override
    public void show() {
        configuraTela();
        desenhaTitulo();
        desenhaMensagem();
        desenhaInputs();
        desenhaBotoes();
        tela.setVisible(true);
    }
}
