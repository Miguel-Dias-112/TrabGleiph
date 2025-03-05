package View;

import javax.swing.*;

public class Screen {
    public JFrame tela;
    public Screen() {
        tela = new JFrame();
    }
    public void show() {
        tela.setVisible(true);
    }
    public void close() {
        tela.dispose();
    }
}
