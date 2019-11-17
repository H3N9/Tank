/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import big.gun.window.Import;
import static big.gun.window.tank.CollectionTanks.tanks;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

        
public class Tank extends GameObject implements Moveable{
    private double hp;
    private double[][] armour;
    private double[] thickness;
    private double reload, rotateSpeed, isBack, speed, speedReload;
    private Shell shell;
    private Turret turret;
    private String nameTank;

    public Tank(String name, double posX, double posY){
        super();
        nameTank = name;
        setPosX(posX);
        setPosY(posY);
        setWidth(Import.tankImg.get(nameTank)[0].getWidth()*CollectionTanks.tanks.get(name)[9]);
        setHeight(Import.tankImg.get(nameTank)[0].getHeight()*CollectionTanks.tanks.get(name)[9]);
        setCenterX(getPosX()+getHeight()/2);
        setCenterY(getPosY()+getHeight()/2);
        // front left right back
        thickness = new double[] {CollectionTanks.tanks.get(name)[0], CollectionTanks.tanks.get(name)[1], CollectionTanks.tanks.get(name)[2], CollectionTanks.tanks.get(name)[3]};
        speedReload = CollectionTanks.tanks.get(name)[7];
        hp = CollectionTanks.tanks.get(name)[4];
        rotateSpeed = 0; isBack = 1; reload = speedReload;
        speed = CollectionTanks.tanks.get(name)[8];
        armour = new double[4][2];
        armour[0][0] = getPosX();
        armour[0][1] = getPosY();
        armour[1][0] = getPosX()+getWidth();
        armour[1][1] = getPosY();
        armour[2][0] = getPosX()+getWidth();
        armour[2][1] = getPosY()+getHeight();
        armour[3][0] = getPosX();
        armour[3][1] = getPosY()+getHeight();
        turret = new Turret(this);
        shell = new Shell(this);
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(getBounds());
        g2d.drawImage(Import.tankImg.get(nameTank)[0], (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
        shell.draw(g2d);
        turret.draw(g2d);
    }

    
    @Override
    public void move() {
        setRotate(getRotate()+getRotateSpeed()*isBack);
        turret.setRotate(turret.getRotate()+getRotateSpeed()*isBack);
        setPosX(getPosX()+Calculate.calculateMoveX(getRotate(), getSpeedX()));
        setPosY(getPosY()+Calculate.calculateMoveY(getRotate(), getSpeedY()));
        armour[0][0] += Calculate.calculateMoveX(getRotate(), getSpeedX());
        armour[0][1] += Calculate.calculateMoveY(getRotate(), getSpeedY());
        armour[1][0] += Calculate.calculateMoveX(getRotate(), getSpeedX());
        armour[1][1] += Calculate.calculateMoveY(getRotate(), getSpeedY());
        armour[2][0] += Calculate.calculateMoveX(getRotate(), getSpeedX());
        armour[2][1] += Calculate.calculateMoveY(getRotate(), getSpeedY());
        armour[3][0] += Calculate.calculateMoveX(getRotate(), getSpeedX());
        armour[3][1] += Calculate.calculateMoveY(getRotate(), getSpeedY());
        setCenterX(getPosX()+getWidth()/2); 
        setCenterY(getPosY()+getHeight()/2);
        turret.update(getPosX(), getPosY(), getCenterX(), getCenterY(), getWidth(), getHeight(), getRotate());
        setReload(getReload());
    }
    
    public void moveStop(){
        setPosX(getPosX()-Calculate.calculateMoveX(getRotate(), getSpeedX()));
        setPosY(getPosY()-Calculate.calculateMoveY(getRotate(), getSpeedY()));
        setCenterX(getPosX()+getWidth()/2); 
        setCenterY(getPosY()+getHeight()/2);
    }
    public void shoot(){
        shell = new Shell(this);
    }
    
    
    
    public double getIsBack() {
        return isBack;
    }

    public double getSpeed() {
        return speed;
    }
    
    
    public double getHp() {
        return hp;
    }

    public double getReload() {
        return reload;
    }

    public Shell getShell() {
        return shell;
    }

    public double getRotateSpeed() {
        return rotateSpeed;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setReload(double reload) {
        if(reload<speedReload)
            this.reload += 0.001;
        else
            this.reload = reload;
        System.out.println(this.reload);
    }
    public Turret getTurret() {
        return turret;
    }

    public String getNameTank() {
        return nameTank;
    }
    
    public void setRotateSpeed(double rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }
    public void setIsBack(double isBack) {
        this.isBack = isBack;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSpeedReload(double speedReload) {
        this.speedReload = speedReload;
    }

    public void setNameTank(String nameTank) {
        this.nameTank = nameTank;
    }

    public void setShell(Shell shell) {
        this.shell = shell;
    }
    
}
