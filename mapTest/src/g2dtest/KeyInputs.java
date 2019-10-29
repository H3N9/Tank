/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2dtest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author pooh
 */
public class KeyInputs implements KeyListener{
    Player p;
    
    public KeyInputs(Player p){
        this.p = p;
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        p.keyPress(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        p.keyReleased(ke);
    }
}
