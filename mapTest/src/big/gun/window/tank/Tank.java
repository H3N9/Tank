/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import Calculate.Calculate;
import big.gun.window.map.*;
import java.util.LinkedList;

        
public class Tank extends GameObject implements Moveable{
    private double hp;
    private Armour[] armour;
    private double[] thickness;
    private double reload, rotateSpeed, isBack, speed, speedReload;
    private Shell shell;
    private Turret turret;
    private CollectionTanks tanks;
    private String nameTank;
    
    
    public Tank(String name){
        super();
        tanks = new CollectionTanks();
        setPosX(350);
        setPosY(400);
        setWidth(tanks.getTanks(name)[9]);
        setHeight(tanks.getTanks(name)[10]);
        setCenterX(getPosX()+getHeight()/2);
        setCenterY(getPosY()+getHeight()/2);
        // front left right back
        thickness = new double[] {tanks.getTanks(name)[0], tanks.getTanks(name)[1], tanks.getTanks(name)[2], tanks.getTanks(name)[3]};
        speedReload = tanks.getTanks(name)[7];
        hp = tanks.getTanks(name)[4];
        rotateSpeed = 0; isBack = 1; reload = speedReload;
        speed = tanks.getTanks(name)[8];
        turret = new Turret(getPosX()+getWidth()/4, getPosY()+getHeight()/4, getCenterX(), getCenterY(), getWidth()/2, getHeight()/2);
        armour = new Armour[4];
        shell = new Shell(this);
        nameTank = name;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(getBounds());
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
        turret.draw(g2d);
        shell.draw(g2d);
    }

    
    @Override
    public void move() {
        setRotate(getRotate()+getRotateSpeed()*isBack);
        turret.setRotate(turret.getRotate()+(turret.getRotateSpeed()+getRotateSpeed())*isBack);
        setPosX(getPosX()+Calculate.calculateMoveX(getRotate(), getSpeedX()));
        setPosY(getPosY()+Calculate.calculateMoveY(getRotate(), getSpeedY()));
        setCenterX(getPosX()+getWidth()/2); 
        setCenterY(getPosY()+getHeight()/2);
        turret.update(getPosX()+getWidth()/4, getPosY()+getHeight()/4, getCenterX(), getCenterY());
        setReload(getReload());
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

    public CollectionTanks getTanks() {
        return tanks;
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

    public void setTanks(CollectionTanks tanks) {
        this.tanks = tanks;
    }

    public void setReload(double reload) {
        if(reload<speedReload)
            this.reload += 1;
        else
            this.reload = reload;
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
