package Controller.ClickHandlers;

import javax.swing.JFrame;
import javax.swing.JTextField;

import View.LoginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseWindow implements ActionListener {

    private JFrame tela;
    public CloseWindow(JFrame tela) {
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.tela.dispose();
        LoginScreen login = new LoginScreen();
    }
}
