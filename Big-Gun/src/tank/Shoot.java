
package tank;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Shoot {
    private double aX;
    private double aY;
    private double rotate;
    private Rectangle2D rect;
    private Player player;
    
    
    public Shoot(Player player){
        this.player = player;
        aX = player.getTag().getHead()[0];
        aY = player.getTag().getHead()[1];
        rotate = player.getRotateHead();
    }
    public void draw(Graphics2D g2d){
        aX += Math.sin(Math.toRadians(rotate))*30;
        aY += Math.cos(Math.toRadians(rotate))*-30;
        g2d.setColor(Color.black);
        rect = new Rectangle2D.Double(aX, aY, 10, 10);
        g2d.fill(rect);
        
    }

    
}
