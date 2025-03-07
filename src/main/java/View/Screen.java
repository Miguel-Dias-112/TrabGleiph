/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

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
