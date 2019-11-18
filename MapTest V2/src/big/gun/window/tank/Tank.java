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
    private int sizeOfArmoursArray;
    private Armour[][] armours;
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
        sizeOfArmoursArray = (int)(getHeight()/(getWidth()/3));
        armours = new Armour[sizeOfArmoursArray][];
        createArmours();
        turret = new Turret(this);
        shell = new Shell(this);
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.rotate(Math.toRadians(getRotate()), getCenterX(), getCenterY());
        g2d.fill(getBounds());
        g2d.drawImage(Import.tankImg.get(nameTank)[0], (int)getPosX(), (int)getPosY(), (int)getWidth(), (int)getHeight(), null);
        g2d.rotate(Math.toRadians(-getRotate()), getCenterX(), getCenterY());
        for(int i=0; i < armours.length; i++){
            if(i==0 || i==armours.length-1){
                for(int j=0; j<3; j++){
                    g2d.setColor(Color.red);
                    g2d.fill(armours[i][j].getBounds());
                }
            }
            else{
                for(int j=0; j<2; j++){
                    g2d.setColor(Color.cyan);
                    g2d.fill(armours[i][j].getBounds());
                }
            }
        }
        shell.draw(g2d);
        turret.draw(g2d);
    }

    
    @Override
    public void move() {
        setRotate(getRotate()+getRotateSpeed()*isBack);
        turret.setRotate(turret.getRotate()+getRotateSpeed()*isBack);
        setPosX(getPosX()+Calculate.calculateMoveX(getRotate(), getSpeedX()));
        setPosY(getPosY()+Calculate.calculateMoveY(getRotate(), getSpeedY()));
        setCenterX(getPosX()+getWidth()/2); 
        setCenterY(getPosY()+getHeight()/2);
        for(int i=0; i < armours.length; i++){
            if(i==0 || i==armours.length-1){
                for(int j=0; j<3; j++){
                    armours[i][j].update(getPosX()+(getWidth()/3*j), getPosY()+(getWidth()/3*i), getCenterX()-getWidth()/6, getCenterY()-getHeight()/sizeOfArmoursArray/2, getRotate());
                }
            }
            else{
                for(int j=0; j<2; j++){
                    if(j == 0){
                        armours[i][j].update(getPosX()+(getWidth()/3*j), getPosY()+(getWidth()/3*i), getCenterX()-getWidth()/6, getCenterY()-getHeight()/sizeOfArmoursArray/2, getRotate());
                    }
                    else{
                        armours[i][j].update(getPosX()+(getWidth()/3*2), getPosY()+(getWidth()/3*i), getCenterX()-getWidth()/6, getCenterY()-getHeight()/sizeOfArmoursArray/2, getRotate());
                    }
                }
            }
        }
        turret.update(getPosX(), getPosY(), getCenterX(), getCenterY(), getWidth(), getHeight(), getRotate());
        setReload(getReload());
    }
    
    public void moveStop(){
        setPosX(getPosX()-Calculate.calculateMoveX(getRotate(), getSpeedX()));
        setPosY(getPosY()-Calculate.calculateMoveY(getRotate(), getSpeedY()));
        setCenterX(getPosX()+getWidth()/2); 
        setCenterY(getPosY()+getHeight()/2);
    }
    
    public void moveStop(String check){
        if (getRotateSpeed() != 0){
            setRotate(getRotate()-getRotateSpeed()*isBack);
            turret.setRotate(turret.getRotate()-getRotateSpeed()*isBack);
            
        }
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

    public void setSizeOfArmoursArray(int sizeOfArmoursArray) {
        this.sizeOfArmoursArray = sizeOfArmoursArray;
    }

    public void setArmours(Armour[][] armours) {
        this.armours = armours;
    }

    public void setThickness(double[] thickness) {
        this.thickness = thickness;
    }

    public void setTurret(Turret turret) {
        this.turret = turret;
    }

    public int getSizeOfArmoursArray() {
        return sizeOfArmoursArray;
    }

    public Armour[][] getArmours() {
        return armours;
    }

    public double[] getThickness() {
        return thickness;
    }

    public double getSpeedReload() {
        return speedReload;
    }
    
    
    
    private void createArmours(){
        for(int i=0; i < armours.length; i++){
            if(i==0 || i==armours.length-1){
                armours[i] = new Armour[3];
                for(int j=0; j<3; j++){
                    armours[i][j] = new Armour(getPosX()+(getWidth()/3*j), getPosY()+(getWidth()/3*i), getWidth()/3);
                }
            }
            else{
                armours[i] = new Armour[2];
                for(int j=0; j<2; j++){
                    if(j == 0){
                        armours[i][j] = new Armour(getPosX()+(getWidth()/3*j), getPosY()+(getWidth()/3*i), getWidth()/3);
                    }
                    else{
                        armours[i][j] = new Armour(getPosX()+(getWidth()/3*2), getPosY()+(getWidth()/3*i), getWidth()/3);
                    }
                }
            }
        }
    }
}
