/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank;

import big.gun.window.Import;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author USER
 */
public class Boom extends GameObject{
    private BufferedImage img;
    private float alpha;
    
    public Boom(double posX, double posY){
        setWidth(300);
        setHeight(300);
        setPosX(posX-getWidth()/2);
        setPosY(posY-getHeight()/2);
        img = Import.boom;
        alpha = 1;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    
    
    public void update(double posX, double posY){
        setPosX(posX-getWidth()/2);
        setPosY(posY-getHeight()/2);
        if(alpha > 0.01){
            alpha -= 0.01;
        }
        else{
            alpha = 0;
        }
    }
    
    public void draw(Graphics2D g2d){
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
        g2d.setComposite(ac);
        g2d.drawImage(img, (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0);
        g2d.setComposite(ac);
    }
}
