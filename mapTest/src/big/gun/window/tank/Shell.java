
package big.gun.window.tank;

import Calculate.Calculate;
import java.awt.Color;
import java.awt.Graphics2D;

public class Shell extends GameObject implements Moveable{
    private double damage;
    private double penetration;
    private Tank tank;
    
    public Shell(Tank tank){
        this.tank = tank;
        setWidth(this.tank.getWidth()/4); setHeight(this.tank.getHeight()/4);
        setCenterX(getPosX()+getWidth()/2); setCenterY(getPosY()+getHeight()/2);
        setRotate(this.tank.getTurret().getRotate());
    }

    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.CYAN);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(getBounds());
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
    }
    
    public void move() {
        setPosX(tank.getPosX()+tank.getWidth()/4+getWidth()/2); setPosY(tank.getPosY());
        setRotate(tank.getTurret().getRotate());
        setCenterX(tank.getCenterX()); setCenterY(tank.getCenterY());
    }
    
}
