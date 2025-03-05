package View;

import javax.swing.*;

public class Screen {
    private JFrame Tela;
    public void show() {
        Tela.setVisible(true);
    }
    public void close() {
        Tela.dispose();
    }
}
