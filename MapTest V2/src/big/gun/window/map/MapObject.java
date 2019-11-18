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
import big.gun.window.tank.GameObject;
import big.gun.window.tank.Moveable;

public abstract class MapObject extends GameObject implements Moveable{
    public static double mapX=0;
    public static double mapY=0;
    private double myPosX;
    private double myPosY;
    
    public MapObject(double posX, double posY, double width, double height){
        myPosX = posX;
        myPosY = posY;
        this.setPosX(posX);
        this.setPosY(posY);
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public static void updatePos(double posX, double posY){
        mapX = posX;
        mapY = posY;
    }
    
    public abstract void update();
    
    public double[] getPosition(){
        double[] xy = {this.getPosX(), this.getPosY()};
        return xy;
    }
    
    public double[] getSize(){
        double[] size = {this.getWidth(), this.getWidth()};
        return size;
    }

    /**
     * @return the myPosX
     */
    public double getMyPosX() {
        return myPosX;
    }

    /**
     * @param myPosX the myPosX to set
     */
    public void setMyPosX(double myPosX) {
        this.myPosX = myPosX;
    }

    /**
     * @return the myPosY
     */
    public double getMyPosY() {
        return myPosY;
    }

    /**
     * @param myPosY the myPosY to set
     */
    public void setMyPosY(double myPosY) {
        this.myPosY = myPosY;
    }
    
}
