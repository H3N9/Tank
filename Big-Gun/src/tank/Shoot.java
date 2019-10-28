
package tank;

import java.awt.Graphics2D;
import java.util.*;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Shoot {
    private double aX;
    private double aY;
    private double rotate;
    private Rectangle2D rect;
    private Player player;
    private double cenX;
    private double cenY;
    private double x;
    
    
    public Shoot(Player player){
        this.player = player;
        aX = player.getCenterX();
        aY = player.getCenterY();
        rotate = player.getRotateHead();
        cenX = player.getCenterX();
        cenY = player.getCenterY();
    }
    public void draw(Graphics2D g2d){
        aX += Math.sin(Math.toRadians(rotate))*20;
        aY += Math.cos(Math.toRadians(rotate))*-20;
        g2d.setColor(Color.black);
        g2d.rotate(Math.toRadians(rotate), cenX, cenY);
        rect = new Rectangle2D.Double(aX, aY, 10, 10);
        g2d.fill(rect);
        g2d.rotate(Math.toRadians(-rotate), cenX, cenY);
        
    }

    
}
