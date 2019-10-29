/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2dtest;

/**
 *
 * @author pooh
 */
import java.util.LinkedList;
import java.awt.*;
import java.awt.geom.*;

public class Builds {
    private double x, y;
    private double px, py;
    private int width, height;
    private Color color;
    private Rectangle2D rect;
    
    public Builds(){
        this(520, 300, Color.YELLOW, 150, 150);
    }
    
    public Builds(double x, double y){
        this(x, y, Color.YELLOW, 150, 150);
    }
    
    public Builds(double x, double y, Color color, int width, int height){
        this.x = x;
        this.y = y;
        px = x+Game.x;
        py = x+Game.y;
        this.color = color;
        this.width = width;
        this.height = height;
    }
    
    public static void buildsPaint(LinkedList<Builds> b, Graphics2D g2d){
        for(int i=0; i < b.size(); i++){
            b.get(i).draw(g2d);
        }
    }
    
    public static void buildsUpdate(LinkedList<Builds> b){
        for (int i=0; i < b.size(); i++){
            b.get(i).update();
        }
    }
    
    public double[] getPosition(){
        double[] xy = {x, y};
        return xy;
    }
    
    public int[] getSize(){
        int[] size = {width, height};
        return size;
    }
    
    public void update(){
        px = x + Game.x;
        py = y + Game.y;
    }
    
    public void draw(Graphics2D g2d){
        AffineTransform old = g2d.getTransform();
        g2d.setColor(color);
        rect = new Rectangle2D.Double(px, py, width, height);
        g2d.fill(rect);
        g2d.setTransform(old);
    }
    
    public Rectangle2D getBounds(){
        return new Rectangle2D.Double(px, py, width, height);
    }
}
