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
import Calculate.Calculate;
import big.gun.window.tank.Tank;
import java.awt.Graphics2D;

public class Person extends MapObject{
    private Tank myTank;
    private double count;
    
    public Person(double posX, double posY, String name) {
        super(posX, posY, 0, 0);
        myTank = new Tank(name, posX, posY);
        this.setWidth(myTank.getWidth());
        this.setHeight(myTank.getHeight());
        count = 5;
    }
    
    public static void updatePos(double posX, double posY){
        MapObject.updatePos(posX, posY);
    }
    
    public void draw(Graphics2D g2d){
        myTank.draw(g2d);
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
    
    
    //กำหนดพฤติกรรม
    private void behavior(){
        //เดินหน้า W
        myTank.setSpeedX(myTank.getSpeed());  
        myTank.setSpeedY(-myTank.getSpeed());
        myTank.setIsBack(1);
        
        //ถอยหลัง
//        myTank.setSpeedX(-myTank.getSpeed());  
//        myTank.setSpeedY(myTank.getSpeed());
//        myTank.setIsBack(0);
        

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
