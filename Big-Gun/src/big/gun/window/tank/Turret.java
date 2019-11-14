
package big.gun.window.tank;

import big.gun.window.Import;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Turret extends GameObject{
    private double rotateSpeed;
    private double PosHeadX, PosHeadY;
    private String nameTank;
    
    public Turret(double px, double py, double cx, double cy, double w, double h, String nameTank){
        super();
        setPosX(px);
        setPosY(py);
        setCenterX(cx);
        setCenterY(cy);
        setWidth(w);
        setHeight(h);
        this.nameTank = nameTank;
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
        g2d.fill(new Rectangle2D.Double(getPosX()+getWidth()/2-10, getPosY()-50, 20, 50));
        g2d.drawImage(Import.tankImg.get(nameTank)[1], (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
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
