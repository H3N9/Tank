/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.map;

/**
 *
 * @author pooh
 */
import big.gun.window.tank.*;
import java.awt.*;
import java.awt.geom.*;

public class Builds extends GameObject implements Moveable{
    private static double mapX=0;
    private static double mapY=0;
    private double myPosX;
    private double myPosY;
    private Color color;
    private Rectangle2D rect;
    
    public Builds(double posX, double posY){
        super();
        this.setPosX(posX);
        this.setPosY(posY);
        myPosX = posX;
        myPosY = posY;
        this.color = Color.yellow;
        this.setWidth(50.0);
        this.setHeight(50.0);
    }
    
    public static void updatePos(double posX, double posY){
        mapX = posX;
        mapY = posY;
    }
    
    public void draw(Graphics2D g2d){
        AffineTransform old = g2d.getTransform();
        g2d.setColor(color);
        rect = new Rectangle2D.Double(this.getPosX(), this.getPosY(), this.getWidth(), this.getWidth());
        g2d.fill(rect);
        g2d.setTransform(old);
    }
    
    public void update(){
        this.move();
    }
    
    @Override
    public void move() {
        this.setPosX(myPosX+mapX);
        this.setPosY(myPosY+mapY);
    }
    
    public double[] getPosition(){
        double[] xy = {this.getPosX(), this.getPosY()};
        return xy;
    }
    
    public double[] getSize(){
        double[] size = {this.getWidth(), this.getWidth()};
        return size;
    }

}
