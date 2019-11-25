
package big.gun.window.tank;

import big.gun.window.Import;
import big.gun.window.map.Map;
import big.gun.window.map.Person;
import big.gun.window.tank.allPlayer.Ai;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Shell extends GameObject implements Moveable{
    private double damage;
    private double penetration;
    boolean isShot;
    
    public Shell(Tank tank, boolean isShot){
        setWidth(5); setHeight(5);
        setPosX(tank.getTurret().getGunPosX()-getWidth()*0.5);
        setPosY(tank.getTurret().getGunPosY());
        setCenterX(getPosX()+getWidth()/2); setCenterY(getPosY()+getHeight()/2);
        setRotate(tank.getTurret().getRotate()+tank.getTurret().getRotateHead());
        this.damage = CollectionTanks.tanks.get(tank.getNameTank())[5];
        this.penetration = CollectionTanks.tanks.get(tank.getNameTank())[6];
        this.isShot = isShot;
    }

    
    public void draw(Graphics2D g2d){
        if(isShot){
            g2d.setColor(Color.white);
            g2d.fill(getBounds());
            g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
            g2d.drawImage(Import.shell, (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
            g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
        }
    }
    
    public void move() {
        if(isShot){
            setPosX(getPosX()+Calculate.calculateMoveX(this.getRotate(), 15)); 
            setPosY(getPosY()+Calculate.calculateMoveY(this.getRotate(), -15));
            setCenterX(getPosX()+getWidth()/2); 
            setCenterY(getPosY()+getHeight()/2);
        }
    }
    
    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setPenetration(double penetration) {
        this.penetration = penetration;
    }


    public double getDamage() {
        return damage;
    }

    public double getPenetration() {
        return penetration;
    }


    public boolean isIsShot() {
        return isShot;
    }

    public void setIsShot(boolean isShot) {
        this.isShot = isShot;
    }
    
    
}
