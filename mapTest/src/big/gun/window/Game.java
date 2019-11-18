
package big.gun.window;


import big.gun.window.tank.allPlayer.Player;
import big.gun.window.map.Map;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{
    private Timer start;
    private Player player;
    private Map map;
    
    public Game(){
        start = new Timer(10, this);
        player = new Player("m4", 510, 300);
        map = new Map(0, 0, player);
        start.start();
        addKeyListener(new Input(player));
        setFocusable(true);
    }
    
    public void updateTank(){
        player.getMyTank().move();
    }
    public void updateBullet(){
        player.getMyTank().getShell().move();
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //Draw below this
        map.draw(g2d);
        player.draw(g2d);
        g2d.setColor(Color.black);
        
        //ขอบเขตที่จะเคลื่อน map
        g2d.drawRect(170, 170, 700, 340);
    }
    

    public void actionPerformed(ActionEvent ae) {
        updateTank();
        updateBullet();
        map.update();
        map.playerColison();
        repaint();
    }
    
}
