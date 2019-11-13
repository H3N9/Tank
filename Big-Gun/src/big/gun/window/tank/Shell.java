
package big.gun.window.tank;

import Calculate.Calculate;
import java.awt.Color;
import java.awt.Graphics2D;

public class Shell extends GameObject implements Moveable{
    private double damage;
    private double penetration;
    
    public Shell(Tank tank){
        setWidth(tank.getWidth()/4); setHeight(tank.getHeight()/4);
        setPosX(tank.getPosX()+tank.getWidth()/2-getWidth()/2);
        setPosY(tank.getPosY()+tank.getHeight()/2-getHeight()/2);
        setCenterX(getPosX()+getWidth()/2); setCenterY(getPosY()+getHeight()/2);
        setRotate(tank.getTurret().getRotate());
    }

    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.CYAN);
        g2d.fill(getBounds());
    }
    
    public void move() {
        setPosX(getPosX()+Calculate.calculateMoveX(this.getRotate(), 50)); 
        setPosY(getPosY()+Calculate.calculateMoveY(this.getRotate(), -50));
    }
    
}
