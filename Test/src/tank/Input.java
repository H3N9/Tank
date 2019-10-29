/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter{
    private Player player;

    public Input(Player player) {
        this.player = player;
    }
    
    public void keyPressed(KeyEvent e){
        player.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e){
        player.keyReleased(e);
    }
}
