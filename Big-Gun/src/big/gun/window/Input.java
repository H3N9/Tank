
package big.gun.window;

import big.gun.window.tank.allPlayer.Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Input extends KeyAdapter{
    private Player player;
    
    public Input(Player p){
        player = p;
    }
    
    public void keyPressed(KeyEvent e){
        player.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e){
        player.keyReleased(e);
    }
}
