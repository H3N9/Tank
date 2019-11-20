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
import big.gun.window.Window;
import big.gun.window.tank.*;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

import java.util.LinkedList;

public class Person extends MapObject {

    private Tank myTank;
    private int tag;
    private Rectangle2D veiw;
    private double cfx, cfy;
    private Rectangle2D checkFront;
    //private double count;

    public Person(double posX, double posY, String name, int tag) {
        super(posX, posY, 0, 0);
        myTank = new Tank(name, posX, posY);
        this.setWidth(myTank.getWidth());
        this.setHeight(myTank.getHeight());
        this.tag = tag;
        veiw = new Rectangle2D.Double(myTank.getCenterX()-Window.width/2, myTank.getCenterY()-Window.height/2, Window.width, Window.height);
        cfx = Calculate.calculateRotateX(myTank.getPosX(), myTank.getPosY()-myTank.getWidth(), myTank.getCenterX(), myTank.getCenterY(), myTank.getRotate());
        cfy = Calculate.calculateRotateY(myTank.getPosX(), myTank.getPosY()-myTank.getWidth(), myTank.getCenterX(), myTank.getCenterY(), myTank.getRotate());
        checkFront = new Rectangle2D.Double(cfx, cfy, myTank.getWidth(), myTank.getWidth());
        //count = 5;
    }

    public static void updatePos(double posX, double posY) {
        MapObject.updatePos(posX, posY);
    }

    public void draw(Graphics2D g2d) {
        myTank.draw(g2d);
        g2d.setColor(Color.PINK);
        g2d.fill(checkFront);
        g2d.drawString(myTank.getNameTank(),
                (float) myTank.getPosX() + 50, (float) myTank.getPosY() - 10);
    }

    @Override
    public void update() {
        //พฤติกรรม
        //behavior();

        //จุดเกิดเคลื่อนที่
        setMyPosX(getMyPosX() + Calculate.calculateMoveX(myTank.getRotate(), myTank.getSpeedX()));
        setMyPosY(getMyPosY() + Calculate.calculateMoveY(myTank.getRotate(), myTank.getSpeedY()));

        //อัพเดทตำแหน่งจาก viewpoint
        myTank.setPosX(getMyPosX() + mapX);
        myTank.setPosY(getMyPosY() + mapY);
        cfx = Calculate.calculateRotateX(myTank.getPosX(), myTank.getPosY()-myTank.getWidth(), myTank.getCenterX(), myTank.getCenterY(), myTank.getRotate());
        cfy = Calculate.calculateRotateY(myTank.getPosX(), myTank.getPosY()-myTank.getWidth(), myTank.getCenterX(), myTank.getCenterY(), myTank.getRotate());
        checkFront.setRect(cfx, cfy, myTank.getWidth(), myTank.getWidth()); 
        //รถเคลื่อนที่
        this.move();
    }

    @Override
    public void move() {
        myTank.move();
        try {
            myTank.getShell().move();
        } catch (Exception e) {

        }
    }

    public void moveStop() {
        setMyPosX(getMyPosX() - Calculate.calculateMoveX(myTank.getRotate(), myTank.getSpeedX()));
        setMyPosY(getMyPosY() - Calculate.calculateMoveY(myTank.getRotate(), myTank.getSpeedY()));
//        if (myTank.getRotateSpeed() != 0){
//            myTank.setRotate(myTank.getRotate()-myTank.getRotateSpeed()*myTank.getIsBack());
//            myTank.getTurret().setRotate(myTank.getTurret().getRotate()-myTank.getRotateSpeed()*myTank.getIsBack());
//        }
//        if (myTank.getRotateSpeed() != 0){
//            myTank.setRotate(myTank.getRotate()-myTank.getRotateSpeed()*myTank.getIsBack());
//            myTank.getTurret().setRotate(myTank.getTurret().getRotate()-myTank.getRotateSpeed()*myTank.getIsBack());
//        }
//        myTank.moveRotateStop();
    }

    //กำหนดพฤติกรรม
    public void behavior(HashSet<String> event) {
        if(this.getMyTank().getHp() > 0){
            if (event.contains("W")) {
                myTank.setSpeedX(myTank.getSpeed());
                myTank.setSpeedY(-myTank.getSpeed());
                myTank.setIsBack(1);
            }
            else if (event.contains("S")) {
                myTank.setSpeedX(-myTank.getSpeed());  
                myTank.setSpeedY(myTank.getSpeed());
                myTank.setIsBack(-1);
            }
            else{
                myTank.setSpeedX(0);  
                myTank.setSpeedY(0);
                myTank.setIsBack(1);
            }

            if (event.contains("A")) {
                myTank.setRotateSpeed(-myTank.getSpeed() / 2);
            }

            else if (event.contains("D")) {
                myTank.setRotateSpeed(myTank.getSpeed() / 2);
            }
            else{
                myTank.setRotateSpeed(0);
            }

            

            if(event.contains("Q")){
                myTank.getTurret().setRotateSpeed(-myTank.getSpeed()/1.5);
            }
            else if(event.contains("E")){
                myTank.getTurret().setRotateSpeed(myTank.getSpeed()/1.5);
            }
            else{
                myTank.getTurret().setRotateSpeed(0);
            }

            if(event.contains("shoot")){
                myTank.shoot();
            }
        }

    }
    
    public int veiwOfBot(Player player, LinkedList<Person> bot, int origin){
        veiw.setRect(myTank.getCenterX()-Window.width/2, myTank.getCenterY()-Window.height/2, Window.width, Window.height);
        for(int i=0;i<bot.size();i++){
            if(i!=origin){
                if(bot.get(i).getTag()!=tag&& veiw.getBounds().intersects(bot.get(i).getMyTank().getBounds())&&bot.get(i).getMyTank().getHp()>0){
                    return i; //bot
                }
                else if(tag!=1&&veiw.getBounds().intersects(player.getMyTank().getBounds())&&player.getMyTank().getHp()>0){
                    return -1; //Player
                }
                    
            }
        }
        return -2; //notfound
    }
    
    public String shootOnTarGet(Player player, LinkedList<Person> bot, int target){
        double myx = getMyTank().getCenterX();
        double myy = getMyTank().getCenterY();
        double tx = player.getMyTank().getCenterX();
        double ty = player.getMyTank().getCenterY();
        double degree = 90-Math.toDegrees(Calculate.calculateArcTan(myx, myy, tx, ty));
        double realDegree = 0;
        if(target!=-1){
            tx = bot.get(target).getMyTank().getCenterX();
            ty = bot.get(target).getMyTank().getCenterY();
            degree = 90-Math.toDegrees(Calculate.calculateArcTan(myx, myy, tx, ty));
        }
        
        if(tx < myx && ty < myy){
            realDegree = 360-degree;
        }
        else if(tx < myx && ty > myy){
            realDegree = 180+degree;
        }
        else if(tx > myx && ty > myy){
            realDegree = 180-degree;
        }
        else if(tx > myx && ty < myy){
            realDegree = degree;
        }
        if(getMyTank().getTurret().getRotateHead()+getMyTank().getRotate() < realDegree-5){
            return "right";
        }
        else if(getMyTank().getTurret().getRotateHead()+getMyTank().getRotate() > realDegree+5){
            return "left";
        }
        else{
            return "shoot";
        }
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }
    
    public void setTag(int i){
        tag = i;
    }
    public int getTag(){
        return tag;
    }

    public Rectangle2D getVeiw() {
        return veiw;
    }

    public void setVeiw(Rectangle2D veiw) {
        this.veiw = veiw;
    }


    public double getCfx() {
        return cfx;
    }

    public void setCfx(double cfx) {
        this.cfx = cfx;
    }

    public double getCfy() {
        return cfy;
    }

    public void setCfy(double cfy) {
        this.cfy = cfy;
    }

    public Rectangle2D getCheckFront() {
        return checkFront;
    }

    public void setCheckFront(Rectangle2D checkFront) {
        this.checkFront = checkFront;
    }

    
}
