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
import big.gun.window.Window;
import big.gun.window.tank.allPlayer.Player;
import big.gun.window.tank.Tank;

import java.util.LinkedList;
import java.awt.Graphics2D;

public class Map {
    private LinkedList<Builds> builds;
    private double posX;
    private double posY;
    private Player player;
    
    public Map(Player player){
        builds = new LinkedList<Builds>();
        builds.add(new Builds(200, 200));
        builds.add(new Builds(400, 200));
        builds.add(new Builds(700, 200));
        builds.add(new Builds(200, 20));
        this.player = player;
        posX = 0;
        posY = 0;
    }
    
    public void draw(Graphics2D g2d){
        Builds build;
        for (int i=0; i < getBuilds().size(); i++){
            build = getBuilds().get(i);
            build.draw(g2d);
        }
    }
    
    public void update(){
        this.moveMap();
        Builds build;
        for (int i=0; i < getBuilds().size(); i++){
            build = getBuilds().get(i);
            build.update();
        }
    }
    
    public void playerColison(){
        Builds build;
        Tank pTank = player.getMyTank();
        for (int i=0; i < builds.size(); i++){
            build = builds.get(i);
            if (player.getMyTank().getBounds().intersects(build.getBounds())){
                pTank.moveStop();
            }
        }
    }
    
    public void moveMap(){
        int check = 0;
        Tank pTank = player.getMyTank();
        if (pTank.getPosX()+pTank.getWidth()+140 >= Window.width){
            check = 1;
        }
        
        if (pTank.getPosY()+pTank.getHeight()+140 >= Window.height){
            check = 1;
        }
        
        if (pTank.getPosX()<= 100){
            check = 1;;
        }
        
        if (pTank.getPosY() <= 100){
            check = 1;
        }
        
        if (check == 1){
            pTank.moveStop();
            posX -= Calculate.calculateMoveX(pTank.getRotate(), pTank.getSpeedX());
            posY -= Calculate.calculateMoveY(pTank.getRotate(), pTank.getSpeedY());
            Builds.updatePos(posX, posY);
        }
    }
    
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public LinkedList<Builds> getBuilds() {
        return builds;
    }

    public void setBuilds(LinkedList<Builds> builds) {
        this.builds = builds;
    }
}
