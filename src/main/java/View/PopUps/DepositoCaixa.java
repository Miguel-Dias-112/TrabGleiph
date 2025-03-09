/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package View.PopUps;

import Controller.ClickHandlers.DepositoCaixaHandle;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.ClickHandlers.trocarScreen;
import Models.Usuarios.Caixa;
import View.Screen;
import View.HomeScreen.HomeCaixa;

public class DepositoCaixa  extends Screen {
    private JTextField origemCpfField, valorField;
    private JPasswordField senhaField;
    private JButton transferButton, cancelButton;
    private Caixa   caixa;

    public DepositoCaixa(Caixa caixa) {
        this.caixa = caixa;
        initialize();
    }
    
    private void initialize() {
        tela.setTitle("Depósito");
        tela.setSize(400, 250);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.add(new JLabel("CPF da conta origem:"));
        origemCpfField = new JTextField();
        panel.add(origemCpfField);
        

        panel.add(new JLabel("Valor:"));
        valorField = new JTextField();
        panel.add(valorField);
        
        panel.add(new JLabel("Confirme sua senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);
        
        transferButton = new JButton("Depositar");
        transferButton.addActionListener(
            new DepositoCaixaHandle(this, origemCpfField, valorField, senhaField, caixa)
        );
        cancelButton = new JButton("Cancelar");
        panel.add(cancelButton);

        panel.add(transferButton);
        tela.add(panel);
        cancelButton.addActionListener(new trocarScreen(this, new HomeCaixa(caixa.getCpf())));
    }
}
