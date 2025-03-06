package View;

import Controller.DataAcessObjects.CaixaDAO;
import javax.swing.*;
import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.GerenteDAO;
import Models.Caixa;
import Models.Cliente;
import Models.Gerente;
import Models.Login;
import Models.Usuario;
import View.HomeScreen.HomeCliente;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DeletarContaScreen extends Screen {
    private JPasswordField senhaField;
    private JButton confirmarButton, cancelarButton;
    private final Usuario usuario;
  
    public DeletarContaScreen() {
        this.usuario = Login.user;
        initialize();
    }
    
    private void initialize() {
        tela.setTitle("Deletar Conta");
        tela.setSize(400, 250);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        JLabel mensagemLabel = new JLabel("Tem certeza que deseja deletar sua conta?");
        mensagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(mensagemLabel);

        senhaField = new JPasswordField();
        senhaField.setBorder(BorderFactory.createTitledBorder("Digite sua senha para continuar"));
        panel.add(senhaField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        confirmarButton = new JButton("Confirmar");
        cancelarButton = new JButton("Cancelar");
        buttonPanel.add(confirmarButton);
        buttonPanel.add(cancelarButton);
        panel.add(buttonPanel);

        confirmarButton.addActionListener((ActionEvent e) -> {
            String senha = new String(senhaField.getPassword());
            String cpf = usuario.getCpf();
            boolean encontrou = false;
            
            switch(usuario.getCargo()){
                case "Cliente" -> {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    List<Cliente> clientes = clienteDAO.findAll();
                    for(Cliente cliente : clientes){
                        if(cpf.equals(cliente.getCpf()) && senha.equals(cliente.getSenha())){
                            encontrou = true;
                            clienteDAO.deletarCliente(cpf);
                        }
                    }
                    break;
                }
                case "Caixa" -> {
                    CaixaDAO caixaDAO = new CaixaDAO();
                    List<Caixa> caixas = caixaDAO.findAll();
                    for(Caixa caixa : caixas){
                        if(cpf.equals(caixa.getCpf()) && senha.equals(caixa.getSenha())){
                            encontrou = true;
                            caixaDAO.deletarCaixa(cpf);
                        }
                    }
                    break;
                }
                case "Gerente" -> {
                    GerenteDAO gerenteDAO = new GerenteDAO();
                    List<Gerente> gerentes = gerenteDAO.findAll();
                    for(Gerente gerente : gerentes){
                        if(usuario.getCpf().equals(gerente.getCpf()) && senha.equals(gerente.getSenha())){
                            encontrou = true;
                            gerenteDAO.deletarGerente(cpf);
                        }
                    }
                    break;
                }      
            }
            
            if(encontrou){
                JOptionPane.showMessageDialog(null, "Conta deletada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                tela.dispose();
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.show();
                
            }else{
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> tela.dispose());

        tela.add(panel);
    }
}