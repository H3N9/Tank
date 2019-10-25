
package tank;

import java.awt.Graphics2D;
import java.util.*;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.lang.Object;
import java.awt.geom.AffineTransform;

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
        aX = player.getTag().getHead()[0]-5;
        aY = player.getTag().getHead()[1]-5;
        rotate = player.getRotateHead();
        cenX = player.getCenterX()-10;
        cenY = player.getCenterY()-10;
    }
    public void draw(Graphics2D g2d){
        aX += Math.sin(Math.toRadians(rotate))*5;
        aY += Math.cos(Math.toRadians(rotate))*-5;
        g2d.setColor(Color.black);
        //เก็บสถานะปากกา
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotate), aX+5, aY+5);
        rect = new Rectangle2D.Double(aX, aY, 10, 10);
        g2d.fill(rect);
        //คืนสถานะปากกา
        g2d.setTransform(old);
    }
}
