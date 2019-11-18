/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank;

/**
 *
 * @author USER
 */
import static big.gun.window.tank.Calculate.*;

public class Armour extends GameObject{
    public Armour(double posX, double posY, double size){
        super();
        setPosX(posX);
        setPosY(posY);
        setWidth(size);
        setHeight(size);
    }

    public void update(double posX, double posY, double cx, double cy, double rotate){
        setPosX(calculateRotateX(posX, posY, cx, cy, rotate));
        setPosY(calculateRotateY(posX, posY, cx, cy, rotate));
    }
}
