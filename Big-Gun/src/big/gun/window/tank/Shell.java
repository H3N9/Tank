
package big.gun.window.tank;

import big.gun.window.Import;
import java.awt.Color;
import java.awt.Graphics2D;

public class Shell extends GameObject implements Moveable{
    private double damage;
    private double penetration;
    
    public Shell(Tank tank){
        setWidth(Import.tankImg.get(tank.getNameTank())[2].getWidth()/4); setHeight(getWidth());
        setPosX(tank.getTurret().getGunPosX()-getWidth()*0.5);
        setPosY(tank.getTurret().getGunPosY());
        setCenterX(getPosX()+getWidth()/2); setCenterY(getPosY()+getHeight()/2);
        setRotate(tank.getTurret().getRotate()+tank.getTurret().getRotateHead());
    }

    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.fill(getBounds());
    }
    
    public void move() {
        setPosX(getPosX()+Calculate.calculateMoveX(this.getRotate(), 20)); 
        setPosY(getPosY()+Calculate.calculateMoveY(this.getRotate(), -20));
    }
    
}
