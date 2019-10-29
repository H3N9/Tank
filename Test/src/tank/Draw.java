
package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Draw extends JPanel implements ActionListener{
    private Timer start;
    private Player player;
    private Map map;
    
    public Draw(){
        start = new Timer(10, this);
        start.start();
        player = new Player();
        map = new Map();
        addKeyListener(new Input(player));
        setFocusable(true);
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        map.Draw(g2d);
        g2d.setColor(Color.BLACK);
        player.Draw(g2d);
        
        
    }

    public void actionPerformed(ActionEvent ae){
        repaint();
    }
}
