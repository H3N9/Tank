
package big.gun.window.tank;

import java.awt.Color;
import java.awt.Graphics2D;


public class Turret extends GameObject{
    private double rotateSpeed;
    private double PosHeadX, PosHeadY;
    
    public Turret(double px, double py, double cx, double cy, double w, double h){
        super();
        setPosX(px);
        setPosY(py);
        setCenterX(cx);
        setCenterY(cy);
        setWidth(w);
        setHeight(h);
        rotateSpeed = 0;
    }
    
    public void update(double px, double py, double cx, double cy){
        setPosX(px);
        setPosY(py);
        setCenterX(cx);
        setCenterY(cy);
    }
    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.yellow);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(getBounds());
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
    }
    
    public double getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(double rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    public double getPosHeadX() {
        return PosHeadX;
    }

    public void setPosHeadX(double PosHeadX) {
        this.PosHeadX = PosHeadX;
    }

    public void setPosHeadY(double PosHeadY) {
        this.PosHeadY = PosHeadY;
    }

    public double getPosHeadY() {
        return PosHeadY;
    }
    
}
