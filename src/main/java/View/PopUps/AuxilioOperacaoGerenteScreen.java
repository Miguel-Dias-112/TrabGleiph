package View.PopUps;

import Controller.ClickHandlers.AuxilioOperacaoGerenteHandle;
import Models.Usuarios.Gerente;
import View.Screen;
import View.HomeScreen.HomeGerente;

import javax.swing.*;
import java.awt.*;

public class AuxilioOperacaoGerenteScreen extends Screen {
    private JComboBox<String> tipoOperacaoCombo;
    private JTextField origemCpfField, destinoCpfField, valorField;
    private JPasswordField senhaField;
    private Gerente gerente;

    public AuxilioOperacaoGerenteScreen(Gerente gerente) {
        this.gerente = gerente;
        initialize();
    }

    private void initialize() {
        tela = new JFrame("Auxílio de Operações");
        tela.setSize(400, 300);
        tela.setLayout(new GridLayout(6, 2, 10, 10));
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Componentes
        tipoOperacaoCombo = new JComboBox<>(new String[]{"Saque", "Transferência"});
        origemCpfField = new JTextField();
        destinoCpfField = new JTextField();
        valorField = new JTextField();
        senhaField = new JPasswordField();
        JButton confirmarButton = new JButton("Confirmar");
        JButton cancelarButton = new JButton("Cancelar");

        // Configuração inicial
        destinoCpfField.setVisible(false);

        // Listeners
        tipoOperacaoCombo.addActionListener(e -> toggleDestinoVisibility());
        confirmarButton.addActionListener(new AuxilioOperacaoGerenteHandle(
            this, 
            tipoOperacaoCombo,
            origemCpfField,
            destinoCpfField,
            valorField,
            senhaField
        ));
        cancelarButton.addActionListener(e -> tela.dispose());

        // Layout
        tela.add(new JLabel("Tipo de Operação:"));
        tela.add(tipoOperacaoCombo);
        tela.add(new JLabel("CPF Origem:"));
        tela.add(origemCpfField);
        tela.add(new JLabel("CPF Destino:"));
        tela.add(destinoCpfField);
        tela.add(new JLabel("Valor:"));
        tela.add(valorField);
        tela.add(new JLabel("Senha do Cliente:"));
        tela.add(senhaField);
        tela.add(confirmarButton);
        tela.add(cancelarButton);
    }

    private void toggleDestinoVisibility() {
        boolean isTransferencia = "Transferência".equals(tipoOperacaoCombo.getSelectedItem());
        destinoCpfField.setVisible(isTransferencia);
        tela.revalidate();
        tela.repaint();
    }

    @Override
    public void show() {
        tela.setVisible(true);
    }
}