
package big.gun.window.tank;

import big.gun.window.Import;
import static big.gun.window.tank.Calculate.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Turret extends GameObject{
    private double rotateSpeed, rotateHead;
    private double posHeadX, posHeadY, centerHeadX, centerHeadY;
    private double gunPosX, gunPosY, gunWidth, gunHeight;
    private String nameTank;
    private double save;
    
    public Turret(Tank tank){
        super();
        this.nameTank = tank.getNameTank();
        setWidth(Import.tankImg.get(nameTank)[1].getWidth()*CollectionTanks.tanks.get(nameTank)[9]);
        setHeight(Import.tankImg.get(nameTank)[1].getHeight()*CollectionTanks.tanks.get(nameTank)[9]);
        gunWidth = (Import.tankImg.get(nameTank)[2].getWidth()*CollectionTanks.tanks.get(nameTank)[9]);
        gunHeight = (Import.tankImg.get(nameTank)[2].getHeight()*CollectionTanks.tanks.get(nameTank)[9]);
        setPosX(tank.getPosX()+tank.getWidth()/2-getWidth()/2);
        setPosY(tank.getPosY()+tank.getHeight()*CollectionTanks.tanks.get(nameTank)[10]-getHeight()/2);
        setCenterX(tank.getCenterX());
        setCenterY(tank.getCenterY());
        centerHeadX = getPosX()+getWidth()/2;
        centerHeadY = getPosY()+getHeight()/2;
        gunPosX = centerHeadX;
        gunPosY = centerHeadY - getHeight()/2 - gunHeight;
        rotateSpeed = 0;
    }
    
    public void update(double px, double py, double cx, double cy, double tankWidth, double tankHeight, double tankRotate){
        setPosX(px+tankWidth/2-getWidth()/2);
        setPosY(py+tankHeight*CollectionTanks.tanks.get(nameTank)[10]-getHeight()/2);
        setCenterX(cx);
        setCenterY(cy);
        centerHeadX = getPosX()+getWidth()/2;
        centerHeadY = getPosY()+getHeight()/2;
        gunPosX = centerHeadX;
        gunPosY = centerHeadY - getHeight()/2 - gunHeight;
        save = centerHeadX;
        centerHeadX = calculateRotateX(centerHeadX, centerHeadY, cx, cy, tankRotate);
        centerHeadY = calculateRotateY(save, centerHeadY, cx, cy, tankRotate);
        rotateHead += rotateSpeed;
        save = gunPosX;
        gunPosX = calculateRotateX(gunPosX, gunPosY, cx, cy, tankRotate);
        gunPosY = calculateRotateY(save, gunPosY, cx, cy, tankRotate);
        save = gunPosX;
        gunPosX = calculateRotateX(gunPosX, gunPosY, centerHeadX, centerHeadY, rotateHead);
        gunPosY = calculateRotateY(save, gunPosY, centerHeadX, centerHeadY, rotateHead);
    }
    
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.yellow);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.rotate(Math.toRadians(rotateHead), getPosX()+getWidth()/2, getPosY()+getHeight()/2);
        g2d.fill(new Rectangle2D.Double(getPosX()+getWidth()/2-10, getPosY()-50, 20, 50));
        g2d.drawImage(Import.tankImg.get(nameTank)[1], (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
        g2d.drawImage(Import.tankImg.get(nameTank)[2], (int)(getPosX()+getWidth()/2-gunWidth/2), (int)(getPosY()-gunHeight), (int)gunWidth, (int)gunHeight, null);
        g2d.rotate(Math.toRadians(-rotateHead), getPosX()+getWidth()/2, getPosY()+getHeight()/2);
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
        g2d.fill(new Rectangle2D.Double(centerHeadX, centerHeadY, 10, 10));
        g2d.fill(new Rectangle2D.Double(gunPosX, gunPosY, 10, 10));
        
    }
    
    public double getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(double rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    public double getPosHeadX() {
        return posHeadX;
    }

    public void setPosHeadX(double posHeadX) {
        this.posHeadX = posHeadX;
    }

    public void setPosHeadY(double posHeadY) {
        this.posHeadY = posHeadY;
    }

    public double getPosHeadY() {
        return posHeadY;
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

    public double getRotateHead() {
        return rotateHead;
    }

    public double getCenterHeadX() {
        return centerHeadX;
    }

    public double getCenterHeadY() {
        return centerHeadY;
    }

    public double getGunPosX() {
        return gunPosX;
    }

    public double getGunPosY() {
        return gunPosY;
    }

    public String getNameTank() {
        return nameTank;
    }

    public void setRotateHead(double rotateHead) {
        this.rotateHead = rotateHead;
    }

    public void setCenterHeadX(double centerHeadX) {
        this.centerHeadX = centerHeadX;
    }

    public void setCenterHeadY(double centerHeadY) {
        this.centerHeadY = centerHeadY;
    }

    public void setGunPosX(double gunPosX) {
        this.gunPosX = gunPosX;
    }

    public void setGunPosY(double gunPosY) {
        this.gunPosY = gunPosY;
    }

    public void setNameTank(String nameTank) {
        this.nameTank = nameTank;
    }
    
}
