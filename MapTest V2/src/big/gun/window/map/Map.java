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
import big.gun.window.Window;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Color;

import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Map {
    private double posX;
    private double posY;
    private LinkedList<Builds> builds;
    private LinkedList<Person> enemys;
    private Player player;
    private Person person1, person2;
    
    public Map(double posX, double posY, Player player){
        builds = new LinkedList<Builds>();
        enemys = new LinkedList<Person>();
        this.addBuilds();
        this.player = player;
        enemys.add(new Person(200, 200, "m4"));
        enemys.add(new Person(299, 1800, "tiger"));
        
        this.posX = -posX;
        this.posY = -posY;
        Person.updatePos(this.posX, this.posY);
        Builds.updatePos(this.posX, this.posY);
    }
    
    public void draw(Graphics2D g2d){
        for (Builds build: builds){
            build.draw(g2d);
        }
        for (Person enemy: enemys){
            enemy.draw(g2d);
        }
        g2d.setColor(Color.BLACK);
    }
    
    public void update(){
        this.moveMap();
        
        builds.forEach((build) ->{
            build.update();
        });
        
//        for (int i=0; i < builds.size(); i++){
//            build = getBuilds().get(i);
//            build.update();
//        }
        enemys.forEach((enemy) -> {
            enemy.update();
        });
    }
    
    public void playerColison(){
        Builds build;
        Tank pTank = player.getMyTank();
        for (int i=0; i < builds.size(); i++){
            build = builds.get(i);
            if (player.getMyTank().getBounds().intersects(build.getBounds())){
                pTank.moveStop();
            }
            for (Person enemy: enemys){
                if (enemy.getMyTank().getBounds().intersects(build.getBounds())){
                    enemy.moveStop();
                }
                
//                  บัคไรก็ไม่รู้
//                if (enemy.getMyTank().getBounds().intersects(pTank.getBounds())){
//                    enemy.moveStop();
//                }
            }
        }
    }
    
    private void moveMap(){
        int check = 0;
        Tank pTank = player.getMyTank();
        if (pTank.getPosX()+pTank.getWidth()+210 >= Window.width){
            check = 1;
        }
        
        if (pTank.getPosY()+pTank.getHeight()+210 >= Window.height){
            check = 1;
        }
        
        if (pTank.getPosX()<= 170){
            check = 1;;
        }
        
        if (pTank.getPosY() <= 170){
            check = 1;
        }
        
        if (check == 1){
            posX -= Calculate.calculateMoveX(pTank.getRotate(), pTank.getSpeedX());
            posY -= Calculate.calculateMoveY(pTank.getRotate(), pTank.getSpeedY());
            pTank.moveStop();
            Builds.updatePos(posX, posY);
            Person.updatePos(posX, posY);
        }
    }
    
    private void addBuilds(){
        builds.add(new Builds(0, 0, 5000, 5000, Color.decode("#9b7653"), ""){
            @Override
            public Rectangle2D getBounds(){
                return new Rectangle2D.Double(posX,posY,0,0);
            }
        });
        builds.add(new Builds(0, 0, 5000, 50, Color.BLACK, ""));
        builds.add(new Builds(0, 50, 50, 4900, Color.BLACK, ""));
        builds.add(new Builds(4950, 50, 50, 4900, Color.BLACK, ""));
        builds.add(new Builds(0, 4950, 5000, 50, Color.BLACK, ""));
        
        builds.add(new Builds(800, 400));
        builds.add(new Builds(400, 1000));
        builds.add(new Builds(1200, 1600));
        builds.add(new Builds(100, 1800));
        builds.add(new Builds(800, 2400));
        builds.add(new Builds(1000, 2600));
        builds.add(new Builds(200, 3000));
        builds.add(new Builds(800, 3600));
        builds.add(new Builds(400, 4000));
        builds.add(new Builds(4000, 4400));
        builds.add(new Builds(4400, 3800));
        builds.add(new Builds(3800, 2200));
        builds.add(new Builds(4000, 2400));
        builds.add(new Builds(4600, 1800));
        builds.add(new Builds(4000, 1200));
        builds.add(new Builds(4400, 800));
        
        builds.add(new Builds(1400, 600, 1000, 200, Color.GRAY, ""));
        builds.add(new Builds(2600, 600, 1000, 200, Color.GRAY, ""));
        builds.add(new Builds(1400, 800, 200, 3400, Color.GRAY, ""));
        builds.add(new Builds(1400, 4200, 1000, 200, Color.GRAY, ""));
        builds.add(new Builds(2600, 4200, 1000, 200, Color.GRAY, ""));
        builds.add(new Builds(3400, 800, 200, 3400, Color.GRAY, ""));
        
        builds.add(new Builds(2000, 1000, 300, 600, Color.GRAY, ""));
        builds.add(new Builds(2700, 1000, 300, 600, Color.GRAY, ""));
        
        builds.add(new Builds(2000, 2000, 300, 300, Color.GRAY, ""));
        builds.add(new Builds(2700, 2000, 300, 300, Color.GRAY, ""));
        
        builds.add(new Builds(1800, 2600, 600, 200, Color.GRAY, ""));
        builds.add(new Builds(2600, 2600, 600, 200, Color.GRAY, ""));
        
        builds.add(new Builds(2000, 2900, 300, 300, Color.GRAY, ""));
        builds.add(new Builds(2700, 2900, 300, 300, Color.GRAY, ""));
        
        builds.add(new Builds(2000, 3400, 300, 600, Color.GRAY, ""));
        builds.add(new Builds(2700, 3400, 300, 600, Color.GRAY, ""));
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
