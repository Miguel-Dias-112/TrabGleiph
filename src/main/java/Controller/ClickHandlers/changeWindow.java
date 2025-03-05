package Controller.ClickHandlers;

import javax.swing.JFrame;
import javax.swing.JTextField;

import View.LoginScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changeWindow implements ActionListener {

    private JFrame oldScreen;
    private JFrame newScreen;
    public changeWindow(JFrame oldScreen, JFrame newScreen) { 
        this.oldScreen = oldScreen;
        this.newScreen = newScreen;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.oldScreen.dispose();
        newScreen.setVisible(true);
    }
}
