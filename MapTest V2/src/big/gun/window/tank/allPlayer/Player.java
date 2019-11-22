
package big.gun.window.tank.allPlayer;

import big.gun.window.tank.Tank;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;



public class Player {
    private Tank myTank;


    
    public Player(String name, double posX, double posY){
        myTank = new Tank(name, posX, posY);
    }
    
    //
//    public Player(double posX, double posY){
//        own
//    }
    
    public void draw(Graphics2D g2d){
        myTank.draw(g2d);
        g2d.drawString(myTank.getNameTank(), 
                (float)myTank.getPosX()+50, (float)myTank.getPosY()-10);
    }
    
    public void keyPressed(KeyEvent e){
        if(myTank.getHp() > 0){
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_W){
                myTank.setSpeedX(myTank.getSpeed());  
                myTank.setSpeedY(-myTank.getSpeed());
                myTank.setIsBack(1);
            }
            if(key==KeyEvent.VK_A){
                myTank.setRotateSpeed(-myTank.getSpeed()/2);
            }
            if(key==KeyEvent.VK_D){
                myTank.setRotateSpeed(myTank.getSpeed()/2);
            }
            if(key==KeyEvent.VK_S){
                myTank.setSpeedX(-myTank.getSpeed());  
                myTank.setSpeedY(myTank.getSpeed());
                myTank.setIsBack(-1);
            }
            if(key==KeyEvent.VK_Q){
                myTank.getTurret().setRotateSpeed(-myTank.getSpeed()/1.5);
            }
            if(key==KeyEvent.VK_E){
                myTank.getTurret().setRotateSpeed(myTank.getSpeed()/1.5);
            }
            if(key==KeyEvent.VK_SPACE){
                myTank.shoot();
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_W){
            myTank.setSpeedX(0);  
            myTank.setSpeedY(0);
        }
        if(key==KeyEvent.VK_A){
            myTank.setRotateSpeed(0);
        }
        if(key==KeyEvent.VK_D){
            myTank.setRotateSpeed(0);
        }
        if(key==KeyEvent.VK_S){
            myTank.setSpeedX(0);  
            myTank.setSpeedY(0);
            myTank.setIsBack(1);
        }
        if(key==KeyEvent.VK_Q){
            myTank.getTurret().setRotateSpeed(0);
        }
        if(key==KeyEvent.VK_E){
            myTank.getTurret().setRotateSpeed(0);
        }
    }
    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public Tank getMyTank() {
        return myTank;
    }
}
