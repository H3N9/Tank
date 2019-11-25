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
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Builds extends MapObject{
    private Color color;
    private BufferedImage img;
    private Rectangle2D rect;
    private boolean alwaysDraw; 
    
    public Builds(double posX, double posY, String name){
        this(posX, posY, 200, 200, null, name);
    }
    
//    public Builds(double posX, double posY, String path){
//        this(posX, posY, 50.0, 50.0, null, path);
//    }
    
    public Builds(double posX, double posY, double width, double height, Color color, String path){
        super(posX, posY, width, height);
        this.color = color;
        if (!"".equals(path)){
            loadImage(path);
        }
        alwaysDraw = false;
    }
    public Builds(double posX, double posY, double width, double height, Color color, String path, boolean b){
        super(posX, posY, width, height);
        this.color = color;
        if (!"".equals(path)){
            loadImage(path);
        }
        alwaysDraw = b;
    }
    
    public static void updatePos(double posX, double posY){
        MapObject.updatePos(posX, posY);
    }
    
    @Override
    public void update(){
        this.move();
    }
    
    @Override
    public void move() {
        this.setPosX(getMyPosX()+mapX);
        this.setPosY(getMyPosY()+mapY);
    }
    
    private void loadImage(String path){
        //ImageIcon imgCon = null;
        try{
            img = ImageIO.read(getClass().getResource("/res/building/"+path+".png"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2d){
        if(this.getBounds().intersects(-10,-10,big.gun.window.Window.width+10,big.gun.window.Window.height+10) || alwaysDraw){
            AffineTransform old = g2d.getTransform();
            g2d.setColor(color);
            if (img != null){
                g2d.drawImage(img, (int)this.getPosX(), (int)this.getPosY(), (int)this.getWidth(), (int)this.getHeight(), null);
            }
            else{
                rect = new Rectangle2D.Double(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
                g2d.fill(rect);
            }
            g2d.setTransform(old);
        }
    }
}
