
package big.gun.window.tank;

import Calculate.Calculate;
import java.awt.Color;
import java.awt.Graphics2D;

public class Shell extends GameObject implements Moveable{
    private double damage;
    private double penetration;
    
    public Shell(Tank tank){
        setPosX(tank.getPosX()+tank.getWidth()/4+getWidth()/2); setPosY(tank.getPosY());
        setWidth(tank.getWidth()/4); setHeight(tank.getHeight()/4);
        setCenterX(getPosX()+getWidth()/2); setCenterY(getPosY()+getHeight()/2);
        setRotate(tank.getTurret().getRotate());
    }

    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.CYAN);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(getBounds());
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
    }
    
    public void move() {
        setPosX(getPosX()-Calculate.calculateMoveX(this.getRotate(), 1)); 
        setPosY(getPosY()-Calculate.calculateMoveY(this.getRotate(), 1));
    }
    
}
