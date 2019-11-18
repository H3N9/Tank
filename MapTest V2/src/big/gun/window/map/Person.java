/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.map;

/**
 *
 * @author pooh
 */
import big.gun.window.tank.*;
import java.awt.Graphics2D;
import big.gun.window.tank.enemies.*;

public class Person extends MapObject{
    private Tank myTank;
    private Ai ai;
    //private double count;
    
    public Person(double posX, double posY, String name) {
        super(posX, posY, 0, 0);
        myTank = new Tank(name, posX, posY);
        this.setWidth(myTank.getWidth());
        this.setHeight(myTank.getHeight());
        //count = 5;
    }
    
    public static void updatePos(double posX, double posY){
        MapObject.updatePos(posX, posY);
    }
    
    public void draw(Graphics2D g2d){
        myTank.draw(g2d);
        g2d.drawString(myTank.getPosX()+", "+myTank.getPosY(), 
                (float)myTank.getPosX()+50, (float)myTank.getPosY()-10);
    }
    
    @Override
    public void update(){
        //พฤติกรรม
        behavior();

        //จุดเกิดเคลื่อนที่
        setMyPosX(getMyPosX()+Calculate.calculateMoveX(myTank.getRotate(), myTank.getSpeedX()));
        setMyPosY(getMyPosY()+Calculate.calculateMoveY(myTank.getRotate(), myTank.getSpeedY()));
        
        //อัพเดทตำแหน่งจาก viewpoint
        myTank.setPosX(getMyPosX()+mapX);
        myTank.setPosY(getMyPosY()+mapY);
        
        //รถเคลื่อนที่
        this.move();
    }
    
    @Override
    public void move(){
        myTank.move();
        myTank.getShell().move();
    }
    
    public void moveStop(){
        setMyPosX(getMyPosX()-Calculate.calculateMoveX(myTank.getRotate(), myTank.getSpeedX()));
        setMyPosY(getMyPosY()-Calculate.calculateMoveY(myTank.getRotate(), myTank.getSpeedY()));
    }
    
    //กำหนดพฤติกรรม
    private void behavior(){
//        เดินหน้า W
        myTank.setSpeedX(myTank.getSpeed());  
        myTank.setSpeedY(-myTank.getSpeed());
        myTank.setIsBack(1);
        
        //ถอยหลัง
//        myTank.setSpeedX(-myTank.getSpeed());  
//        myTank.setSpeedY(myTank.getSpeed());
        //myTank.setIsBack(0);
        

        //เลี้ยวขวา A
        myTank.setRotateSpeed(myTank.getSpeed()/2);
    }


    public Tank getMyTank() {
        return myTank;
    }


    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    
}
