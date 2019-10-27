/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2dtest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

/**
 *
 * @author pooh
 */
public class Player {
    private double x, y;
    private double countx=0, county=0;
    private int rotate=0, countr=0;
    private Rectangle2D rect;
    private Color color;
    private String position;
    private final int speed;
    private LinkedList<Builds> b;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        b = Game.b;
        position = ""+rotate;
        speed = 2;
    }
    
    public void update(){
        b = Game.b;
        this.reView();
        if (this.rotate >= 360)
            this.rotate = 0;
        if (this.rotate < 0){
            this.rotate = 360 + this.rotate;
        }
        this.colisonBuilds();
        this.rotate += countr;
    }
    
    public void draw(Graphics2D g2d){
        AffineTransform old = g2d.getTransform();
        Font myFont = new Font("Helvetica", Font.PLAIN, 20);
        g2d.setFont(myFont);
        g2d.setColor(Color.RED);
        g2d.rotate(Math.toRadians(rotate), x+(35), y+(35));
        rect = new Rectangle2D.Double(x, y, 70, 70);
        g2d.fill(rect);
        g2d.setTransform(old);
        g2d.setColor(Color.BLACK);
        g2d.drawString(position, (int)x+35, (int)y-35);
        g2d.setTransform(old);
    }
    
    public Rectangle2D getBounds(){
        return new Rectangle2D.Double(x, y, 70, 70);
    }
    
    public void colisonBuilds(){
        //Rectangle2D b;
        this.x += countx*(-Math.sin(Math.toRadians(rotate)));
        this.y += county*Math.cos(Math.toRadians(rotate));
        for (int i=0; i < b.size(); i++){
            if (this.getBounds().intersects(b.get(i).getBounds())){
                this.x += (-countx*5)*(-Math.sin(Math.toRadians(rotate)));
                this.y += (-county*5)*Math.cos(Math.toRadians(rotate));;
            }
        
            if (!(this.getBounds().intersects(b.get(i).getBounds()))){
                position = Game.x+", "+Game.y;
            }
        }
    }
    
    public void reView(){
        int check = 0;
        if ((this.y+140) >= G2dTest.HEIGHT){
            check = 1;
            this.y -= 3;
        }
        if ((this.x+100) >= G2dTest.WIDTH){
            check = 1;
            this.x -= 3;
        }
        if (this.x <= 10){
            check = 1;
            this.x += 3;
        }
        if (this.y <= 10){
            check = 1;
            this.y += 3;
        }
            
        if (check == 1){
            Game.x -= countx*(-Math.sin(Math.toRadians(rotate)));
            Game.y -= county*Math.cos(Math.toRadians(rotate));
        }
    }
    
    public void keyPress(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W){
            county = -speed;
            countx = -speed;
        }
        if (key == KeyEvent.VK_A){
            countr = -1;
        }
        if (key == KeyEvent.VK_S){
            county = speed;
            countx = speed;
        }
        if (key == KeyEvent.VK_D){
            countr = 1;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W){
            county = 0;
            countx = 0;
        }
        if (key == KeyEvent.VK_A){
            countr = 0;
        }
        if (key == KeyEvent.VK_S){
            county = 0;
            countx = 0;
        }
        if (key == KeyEvent.VK_D){
            countr = 0;
        }
    }
    
}
