
package big.gun.window;


import big.gun.window.tank.allPlayer.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{
    private Timer start;
    private Player player;
    private Import importImg;
    
    public Game(){
        importImg = new Import();
        start = new Timer(10, this);
        start.start();
        player = new Player();
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
        player.draw(g2d);
        
        
    }
    
   
    public void actionPerformed(ActionEvent ae) {
        updateTank();
        updateBullet();
        repaint();
    }
    
}
