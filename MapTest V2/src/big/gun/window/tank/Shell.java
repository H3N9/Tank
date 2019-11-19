
package big.gun.window.tank;

import big.gun.window.Import;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Shell extends GameObject implements Moveable,ActionListener{
    private double damage;
    private double penetration;
    private Timer time;
    
    public Shell(Tank tank){
        time = new Timer(1, this);
        setWidth(Import.tankImg.get(tank.getNameTank())[2].getWidth()/4); setHeight(getWidth());
        setPosX(tank.getTurret().getGunPosX()-getWidth()*0.5);
        setPosY(tank.getTurret().getGunPosY());
        setCenterX(getPosX()+getWidth()/2); setCenterY(getPosY()+getHeight()/2);
        setRotate(tank.getTurret().getRotate()+tank.getTurret().getRotateHead());
    }

    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.white);
        g2d.fill(getBounds());
    }
    
    public void move() {
        setPosX(getPosX()+Calculate.calculateMoveX(this.getRotate(), 15)); 
        setPosY(getPosY()+Calculate.calculateMoveY(this.getRotate(), -15));
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setPenetration(double penetration) {
        this.penetration = penetration;
    }

    public void setTime(Timer time) {
        this.time = time;
    }

    public double getDamage() {
        return damage;
    }

    public double getPenetration() {
        return penetration;
    }

    public Timer getTime() {
        return time;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        move();
    }
    
}
