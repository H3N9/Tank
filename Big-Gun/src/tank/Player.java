
package tank;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player extends Tank{
    private double positionX;
    private double positionY;
    private double rotate;
    private double rotateHead;
    private Tag tag;
    private double centerX;
    private double centerY;
    private int speedX;
    private int speedY;
    private int positionH;
    private int speedR;
    private int speedH;
    Rectangle2D rect;
    private int count = super.getReload();
    private ArrayList<Shoot> shoot;
    private Shoot s;
    
    

    public double getPositionH() {
        return positionH;
    }
    

    public double getRotate() {
        return rotate;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setRotateHead(int rotateHead) {
        this.rotateHead = getRotate()+rotateHead;
    }

    public double getRotateHead() {
        return rotateHead;
    }

    public Player(){
        this("m26");
    }
    public Player(String name){
        super(name);
        this.speedX = 0;
        this.speedY = 0;
        this.speedH = 0;
        this.centerX = positionX+super.getWidthTank()/2;
        this.centerY = positionY+super.getLongTank()/2;
        tag = new Tag(this);
        shoot = new ArrayList<Shoot>();
        
        
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setCenterX(double x) {
        this.centerX = x+super.getWidthTank()/2;
    }

    public void setCenterY(double y) {
        this.centerY = y+super.getLongTank()/2;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    
    public void Draw(Graphics2D g2d){
        rotate += speedR;
        rotateHead += speedH+speedR;
        positionX += Math.sin(Math.toRadians(rotate))*speedX;
        positionY += Math.cos(Math.toRadians(rotate))*speedY;
        setCenterX(positionX);
        setCenterY(positionY);
        tag.setTagFont(this);
        tag.setHead(this);
        g2d.setColor(Color.yellow);
        g2d.rotate(Math.toRadians(rotate), centerX, centerY);
        rect = new Rectangle2D.Double(positionX, positionY, super.getWidthTank(), super.getLongTank());
        g2d.fill(rect);
        g2d.rotate(Math.toRadians(-rotate), centerX, centerY);
        g2d.rotate(Math.toRadians(rotateHead), centerX, centerY);
        g2d.setColor(Color.black);
        rect = new Rectangle2D.Double(positionX+super.getWidthTank()/4, positionY+super.getLongTank()/4, super.getWidthTank()/2, super.getLongTank()/2);
        g2d.fill(rect);
        rect = new Rectangle2D.Double(positionX+super.getWidthTank()/2.5, positionY+5-super.getLongTank()/4, super.getWidthTank()/5, super.getLongTank()/2);
        g2d.fill(rect);
        g2d.rotate(Math.toRadians(-(rotateHead)), centerX, centerY);
        g2d.drawString(""+rotateHead+" "+rotate+" "+speedH, 200, 220);
        g2d.drawString(""+tag.getHead()[0]+" "+tag.getHead()[1], 250, 300);
        for (int i = 0; i < shoot.size(); i++) {
            shoot.get(i).draw(g2d);
        }
        if(count!=super.getReload())
            count++;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_W){
            speedX = super.getSpeed();
            speedY = -super.getSpeed();
            
            tag.setTagFont(this);
            tag.setHead(this);
        }
        if(key==KeyEvent.VK_A){
            speedR = -1;
            tag.setTagFont(this);
            tag.setHead(this);
        }
        if(key==KeyEvent.VK_D){
            speedR = 1;
            tag.setTagFont(this);
            tag.setHead(this);
        }
        if(key==KeyEvent.VK_S){
            speedX = -super.getSpeed();
            speedY = super.getSpeed();
            tag.setTagFont(this);
            tag.setHead(this);

        }
        if(key==KeyEvent.VK_Q){
            speedH = -1;
            tag.setHead(this);
        }
        if(key==KeyEvent.VK_E){
            speedH = 1;
            tag.setHead(this);
        }
        if(key==KeyEvent.VK_SPACE&&count==super.getReload()){
            s = new Shoot(this);
            shoot.add(s);
            count = 0;
            
        }


        
    }

    public Tag getTag() {
        return tag;
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_W){
            speedX = 0;
            speedY = 0;

        }
        if(key==KeyEvent.VK_A){
            speedR = 0;

        }
        if(key==KeyEvent.VK_D){
            speedR = 0;

        }
        if(key==KeyEvent.VK_S){
            speedX = 0;
            speedY = 0;

        }
        if(key==KeyEvent.VK_Q){
            speedH = 0;
        }
        if(key==KeyEvent.VK_E){
            speedH = 0;
        }

    }
}
