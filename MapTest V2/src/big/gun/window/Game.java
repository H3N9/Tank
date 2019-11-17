
package big.gun.window;


import big.gun.window.map.Map;
import big.gun.window.tank.CollectionTanks;
import big.gun.window.tank.TestDrawTank;
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
    private Map map;
    private Import importImg;
    private CollectionTanks collection;
    //private TestDrawTank tdt;
    
    public Game(){
        importImg = new Import();
        collection = new CollectionTanks();
        start = new Timer(10, this);
        player = new Player("m4a3e8", 510, 250);
        map = new Map(250, 1750, player);
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
        g2d.drawRect(170, 170, 700, 340);
        //tdt = new TestDrawTank(g2d);
        
    }
    
   
    public void actionPerformed(ActionEvent ae) {
        updateTank();
        updateBullet();
        map.update();
        map.playerColison();
        repaint();
    }
    
}
