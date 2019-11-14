
package big.gun.window.tank;

import big.gun.window.Import;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Turret extends GameObject{
    private double rotateSpeed;
    private double PosHeadX, PosHeadY;
    private double gunWidth, gunHeight;
    private String nameTank;
    
    public Turret(double px, double py, double cx, double cy, String nameTank){
        super();
        setPosX(px);
        setPosY(py);
        setCenterX(cx);
        setCenterY(cy);
        setWidth(Import.tankImg.get(nameTank)[1].getWidth());
        setHeight(Import.tankImg.get(nameTank)[1].getHeight());
        gunWidth = (Import.tankImg.get(nameTank)[2].getWidth());
        gunHeight = (Import.tankImg.get(nameTank)[2].getHeight());
        this.nameTank = nameTank;
        rotateSpeed = 0;
    }
    
    public void update(double px, double py, double cx, double cy){
        setPosX(px+Import.tankImg.get(nameTank)[0].getWidth()/2-getWidth()/2);
        setPosY(py+Import.tankImg.get(nameTank)[0].getHeight()/2-getHeight()/2);
        setCenterX(cx);
        setCenterY(cy);
    }
    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.yellow);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(new Rectangle2D.Double(getPosX()+getWidth()/2-10, getPosY()-50, 20, 50));
        g2d.drawImage(Import.tankImg.get(nameTank)[1], (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
        g2d.drawImage(Import.tankImg.get(nameTank)[2], (int)(getPosX()+getWidth()/2-gunWidth/2), (int)(getPosY()-gunHeight), (int)gunWidth, (int)gunHeight, null);
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

    public void setGunWidth(double gunWidth) {
        this.gunWidth = gunWidth;
    }

    public void setGunHeight(double gunHeight) {
        this.gunHeight = gunHeight;
    }

    public double getGunWidth() {
        return gunWidth;
    }

    public double getGunHeight() {
        return gunHeight;
    }
    
}
