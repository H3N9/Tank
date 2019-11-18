/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank.enemies;

import big.gun.window.tank.Tank;
import java.awt.Graphics2D;

/**
 *
 * @author h3n9
 */
public class Ai {
    private Tank myTank;
    public Ai(double posX, double posY, String name){
        myTank = new Tank(name, posX, posY);
    }
    
    public void draw(Graphics2D g2d){  
    }
//    public Tank getTank() {
//        return tank;
//    }
//    
//    public void setTank(Tank tank) {
//        this.tank = tank;
//    }
}
