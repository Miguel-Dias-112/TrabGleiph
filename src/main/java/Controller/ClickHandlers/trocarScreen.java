/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.ClickHandlers;
import View.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class trocarScreen implements ActionListener {

    private Screen oldScreen;
    private Screen newScreen;
    public trocarScreen(Screen oldScreen, Screen newScreen) { 
        this.oldScreen = oldScreen;
        this.newScreen = newScreen;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        oldScreen.close();
        newScreen.show();
    }
}
