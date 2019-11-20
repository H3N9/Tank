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
import big.gun.window.tank.allPlayer.Ai;
import java.awt.Color;

import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Map {
    private double posX;
    private double posY;
    private LinkedList<Builds> builds;
    private LinkedList<Person> persons;
    private boolean isMoveMap;
    
    public Map(double posX, double posY, LinkedList<Person> persons){
        builds = new LinkedList<Builds>();
        this.addBuilds();
        this.posX = -posX;
        this.posY = -posY;
        Builds.updatePos(this.posX, this.posY);
        this.persons = persons;
        isMoveMap = true;
        Person.updatePos(getPosX(), getPosY());
//        for (Person person: getPersons()){
//            person.update();
//        }
    }
    
    public void draw(Graphics2D g2d){
        for (Builds build: getBuilds()){
            build.draw(g2d);
        }
        for (Person person: getPersons()){
            person.draw(g2d);
        }
        g2d.setColor(Color.BLACK);
    }
    
    public void update(){
        
        getBuilds().forEach((build) ->{
            build.update();
        });
        
//        for (int i=0; i < builds.size(); i++){
//            build = getBuilds().get(i);
//            build.update();
//        }
        for (Person person: getPersons()){
            person.update();
        }
        Builds.updatePos(getPosX(), getPosY());
        Person.updatePos(getPosX(), getPosY());
    }
    
    
    public void addBuilds(){
        getBuilds().add(new Builds(0, 0, 5000, 5000, Color.decode("#9b7653"), ""){
            @Override
            public Rectangle2D getBounds(){
                return new Rectangle2D.Double(getPosX(), getPosY(),0,0);
            }
        });
        getBuilds().add(new Builds(0, 0, 5000, 50, Color.BLACK, ""));
        getBuilds().add(new Builds(0, 50, 50, 4900, Color.BLACK, ""));
        getBuilds().add(new Builds(4950, 50, 50, 4900, Color.BLACK, ""));
        getBuilds().add(new Builds(0, 4950, 5000, 50, Color.BLACK, ""));
        
        getBuilds().add(new Builds(800, 400, "tree1"));
        getBuilds().add(new Builds(400, 1000, "tree1"));
        getBuilds().add(new Builds(1200, 1600, "tree1"));
        getBuilds().add(new Builds(100, 1800, "tree2"));
        getBuilds().add(new Builds(800, 2400, "tree1"));
        getBuilds().add(new Builds(1000, 2600, "tree2"));
        getBuilds().add(new Builds(200, 3000, "tree1"));
        getBuilds().add(new Builds(800, 3600, "tree1"));
        getBuilds().add(new Builds(400, 4000, "tree2"));
        getBuilds().add(new Builds(4000, 4400, "tree1"));
        getBuilds().add(new Builds(4400, 3800, "tree2"));
        getBuilds().add(new Builds(3800, 2200, "tree1"));
        getBuilds().add(new Builds(4000, 2400, "tree1"));
        getBuilds().add(new Builds(4600, 1800, "tree2"));
        getBuilds().add(new Builds(4000, 1200, "tree1"));
        getBuilds().add(new Builds(4400, 800, "tree1"));
        
        getBuilds().add(new Builds(1400, 600, 1000, 200, Color.GRAY, "horizontal1"));
        getBuilds().add(new Builds(2600, 600, 1000, 200, Color.GRAY, "horizontal1"));
        getBuilds().add(new Builds(1400, 800, 200, 3400, Color.GRAY, "vertical1"));
        getBuilds().add(new Builds(1400, 4200, 1000, 200, Color.GRAY, "horizontal2"));
        getBuilds().add(new Builds(2600, 4200, 1000, 200, Color.GRAY, "horizontal2"));
        getBuilds().add(new Builds(3400, 800, 200, 3400, Color.GRAY, "vertical2"));
        
        getBuilds().add(new Builds(2000, 1000, 300, 600, Color.GRAY, "square1"));
        getBuilds().add(new Builds(2700, 1000, 300, 600, Color.GRAY, "square2"));
        
        getBuilds().add(new Builds(2000, 2000, 300, 300, Color.GRAY, "square2"));
        getBuilds().add(new Builds(2700, 2000, 300, 300, Color.GRAY, "square1"));
        
        getBuilds().add(new Builds(1800, 2600, 600, 200, Color.GRAY, "horizontal2"));
        getBuilds().add(new Builds(2600, 2600, 600, 200, Color.GRAY, "horizontal1"));
        
        getBuilds().add(new Builds(2000, 2900, 300, 300, Color.GRAY, "square2"));
        getBuilds().add(new Builds(2700, 2900, 300, 300, Color.GRAY, "square1"));
        
        getBuilds().add(new Builds(2000, 3400, 300, 600, Color.GRAY, "square1"));
        getBuilds().add(new Builds(2700, 3400, 300, 600, Color.GRAY, "square2"));
    }
    
    public void moveMap(Player player){
        Tank pTank = player.getMyTank();
//        if (pTank.getPosX()+pTank.getWidth()+(Window.width*0.3) >= Window.width){
//            check = 1;
//        }
//        
//        else if (pTank.getPosY()+pTank.getHeight()+(Window.width*0.3) >= Window.height){
//            check = 1;
//        }
//        
//        else if (pTank.getPosX()<= (Window.width*0.3)){
//            check = 1;
//        }
//        
//        else if (pTank.getPosY() <= (Window.height*0.3)){
//            check = 1;
//        }
        
        if (isMoveMap){
            setPosX(getPosX()-Calculate.calculateMoveX(pTank.getRotate(), pTank.getSpeedX()));
            setPosY(getPosY()-Calculate.calculateMoveY(pTank.getRotate(), pTank.getSpeedY()));
            pTank.moveStop();
        }
    }
    
    public void playerCollision(Player player, Ai bot){
        Builds build;
        Tank pTank = player.getMyTank();
        int breakall = 0;
        
        isMoveMap = true;
        //เช็คชนสิ่งของ
        for (int i=0; i < getBuilds().size(); i++){
            build = getBuilds().get(i);
            //player ชนสิ่งของ
            for(int n=0; n < pTank.getArmours().length; n++){
                for(int m=0; m < pTank.getArmours()[n].length; m++){
                    if( pTank.getArmours()[n][m].getBounds().intersects(build.getBounds())){
                        pTank.moveRotateStop();
                        isMoveMap = false;
                        breakall = 1;
                        break;
                    }
                }
                if(breakall == 1){break;}
            }
            //bot ชนสิ่งของ
            breakall = 0;
            for (Person ebot: bot.getPersons()){
                for(int n=0; n < ebot.getMyTank().getArmours().length; n++){
                    for(int m=0; m < ebot.getMyTank().getArmours()[n].length; m++){
                        if( ebot.getMyTank().getArmours()[n][m].getBounds().intersects(build.getBounds())){
                            ebot.moveStop();
                            breakall = 1;
                            break;
                        }
                    }
                    if(breakall == 1){break;}
                }
            }
        }
        
        
        //player ชนคนอื่น
        breakall = 0;
        for(int n=0; n < pTank.getArmours().length; n++){
                for(int m=0; m < pTank.getArmours()[n].length; m++){
                    for(Person ebot: bot.getPersons()){
                        for(int p=0; p < ebot.getMyTank().getArmours().length; p++){
                            for(int q=0; q < ebot.getMyTank().getArmours()[p].length; q++){
                                if(pTank.getArmours()[n][m].getBounds().intersects(ebot.getMyTank().getArmours()[p][q].getBounds())){
                                    pTank.moveStop();
                                    ebot.moveStop();
                                    isMoveMap = false;
                                    breakall = 1;
                                    break;
                                }
                            }
                            if(breakall == 1){break;}
                        }
                        if(breakall == 1){break;}
                    }
                    if(breakall == 1){break;}
                }
                if(breakall == 1){break;}
            }
        
        //bot ชน bot
        breakall = 0;
        for(Person ebot: bot.getPersons()){
            for(int m=0; m < ebot.getMyTank().getArmours().length; m++){
                for(int n=0; n < ebot.getMyTank().getArmours()[m].length; n++){
                    for(Person ebot2: bot.getPersons()){
                        for(int p=0; p < ebot2.getMyTank().getArmours().length; p++){
                            for(int q=0; q < ebot2.getMyTank().getArmours()[p].length; q++){
                                if(ebot.getMyTank().getArmours()[m][n].getBounds().intersects(ebot2.getMyTank().getArmours()[p][q].getBounds()) && ebot != ebot2){
                                    ebot.moveStop();
                                    ebot2.moveStop();
                                    breakall = 1;
                                    break;
                                }
                            }
                            if(breakall == 1){break;}
                        }
                        if(breakall == 1){break;}
                    }
                    if(breakall == 1){break;}
                }
                if(breakall == 1){break;}
            }
            if(breakall == 1){break;}
        }
    }
    
    public void bulletCollision(Ai bot, Player player){
        int breakall = 0;
            //hit map's objects
            for (int i=0; i < getBuilds().size(); i++){
                //player's bullet
                try{
                    if(player.getMyTank().getShell().getBounds().intersects(getBuilds().get(i).getBounds())){
                        player.getMyTank().setShell(null);
                    }
                }catch(NullPointerException e){
                    
                }
                
                //bot's bullets
                for(Person ebot: bot.getPersons()){
                    try{
                        if(ebot.getMyTank().getShell().getBounds().intersects(getBuilds().get(i).getBounds())){
                            ebot.getMyTank().setShell(null);
                        }
                    }catch(NullPointerException e){
            
                    }
                }
            }
            
        //player shoots bottank's armours
        breakall = 0;
        for(Person ebot: bot.getPersons()){
            for(int n=0; n < ebot.getMyTank().getArmours().length; n++){
                    for(int m=0; m < ebot.getMyTank().getArmours()[n].length; m++){
                        try{
                            if( player.getMyTank().getShell().getBounds().intersects(ebot.getMyTank().getArmours()[n][m].getBounds())){
                                double thickness;
                                if(n==0){
                                    thickness = ebot.getMyTank().getThickness()[0];
                                }
                                else if(n==ebot.getMyTank().getSizeOfArmoursArray()-1){
                                    thickness = ebot.getMyTank().getThickness()[3];
                                }
                                else{
                                    thickness = ebot.getMyTank().getThickness()[1];
                                }
                                
                                if(player.getMyTank().getShell().getPenetration() >= thickness){
                                    ebot.getMyTank().setHp(ebot.getMyTank().getHp()-player.getMyTank().getShell().getDamage());
                                    System.out.println(ebot.getMyTank().getHp());
                                }
                                else{
                                    System.out.println("not penetrate");
                                }
                                

                                player.getMyTank().setShell(null);
                                breakall = 1;
                                break;
                            }
                        }catch(NullPointerException e){
                            
                        }
                        
                    }
                    if(breakall == 1){break;}
                }
            if(breakall == 1){break;}
        }
        
        //bot shoots armours
        for(Person ebot: bot.getPersons()){
            //bot shoot player
            breakall = 0;
            for(int i=0; i < player.getMyTank().getArmours().length; i++){
                for(int j=0; j < player.getMyTank().getArmours()[i].length; j++){
                    try{
                        if(ebot.getMyTank().getShell().getBounds().intersects(player.getMyTank().getArmours()[i][j].getBounds())){
                            double thickness;
                            if(i==0){
                                thickness = player.getMyTank().getThickness()[0];
                            }
                            else if(i==player.getMyTank().getSizeOfArmoursArray()-1){
                                thickness = player.getMyTank().getThickness()[3];
                            }
                            else{
                                thickness = player.getMyTank().getThickness()[1];
                            }
                            
                            if(ebot.getMyTank().getShell().getPenetration() >= thickness){
                                player.getMyTank().setHp(player.getMyTank().getHp()-ebot.getMyTank().getShell().getDamage());
                            }
                            
                            ebot.getMyTank().setShell(null);
                            breakall = 1;
                            break;
                        }
                    }catch(NullPointerException e){
                        
                    }
                }
                if(breakall == 1){break;}
            }
            if(breakall == 1){continue;}
            
            //bot shoot bot
            for(Person ebot2: bot.getPersons()){
                for(int p=0; p < ebot2.getMyTank().getArmours().length; p++){
                    for(int q=0; q < ebot2.getMyTank().getArmours()[p].length; q++){
                        try{
                            if(ebot.getMyTank().getShell().getBounds().intersects(ebot2.getMyTank().getArmours()[p][q].getBounds()) && ebot != ebot2){
                                double thickness;
                                if(p==0){
                                    thickness = ebot2.getMyTank().getThickness()[0];
                                }
                                else if(p==player.getMyTank().getSizeOfArmoursArray()-1){
                                    thickness = ebot2.getMyTank().getThickness()[3];
                                }
                                else{
                                    thickness = ebot2.getMyTank().getThickness()[1];
                                }
                                
                                if(ebot.getMyTank().getShell().getPenetration() >= thickness){
                                    ebot2.getMyTank().setHp(ebot2.getMyTank().getHp()-ebot.getMyTank().getShell().getDamage());
                                }
                                ebot.getMyTank().setShell(null);
                                breakall = 1;
                                break;
                            }
                        }catch(NullPointerException e){
                            
                        }
                    }
                    if(breakall == 1){break;}
                }
            }
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

    public LinkedList<Person> getPersons() {
        return persons;
    }

    public void setPersons(LinkedList<Person> persons) {
        this.persons = persons;
    }

    
}
