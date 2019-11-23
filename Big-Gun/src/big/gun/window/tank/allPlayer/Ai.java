/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank.allPlayer;

import big.gun.window.map.Builds;
import big.gun.window.map.Map;
import big.gun.window.map.Person;
import big.gun.window.tank.*;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.Timer;

/**
 *
 * @author h3n9
 */
public class Ai implements ActionListener{
    private LinkedList<Person> persons;
    private LinkedList<HashSet<String>> move;
    private Player player;
    private final int max = 20;
    private Timer time;
    private int spawnAxis, spawnAlli;
    private String difficult;
    private Map map;
    private int level;
    
    public Ai(int alline, int axis, Player player, String difficult){
        this.player = player;
        persons = new LinkedList<Person>();
        move = new LinkedList<>();
        time = new Timer(10, this);
        time.start();
        this.difficult = difficult;
        level = (int) CollectionTanks.tanks.get(player.getMyTank().getNameTank())[12];
        //spanw Alline
        spawnAlli = alline>max? max-1: alline;
        for(int i=0;i<spawnAlli;i++){
            int flag = Calculate.randomNumber(1, 4);
            int num = level;
            if(level==4){
                num = 4;
                persons.add(new Person(2000+i*200, 4700, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
            else if(difficult.equals("hard")){
                num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2000+i*200, 4700, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
            else if(difficult.equals("normal")){
                num = Calculate.randomNumber(level, level);
                persons.add(new Person(2000+i*200, 4700, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
            else if(difficult.equals("easy")){
                if(level!=1){
                    num = Calculate.randomNumber(level-1, level);
                }
                persons.add(new Person(2000+i*200, 4700, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
                System.out.println(num);
            }
        }
        
        
        //spawn Axis
        spawnAxis = axis>max? max: axis;
        for(int i=0;i<spawnAxis;i++){
            int flag = Calculate.randomNumber(1, 4);
            int num = level;
            if(level==4){
                num = 4;
                persons.add(new Person(2000+i*200, 4500, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
            else if(difficult.equals("hard")){
                num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2000+i*200, 4500, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
            else if(difficult.equals("normal")){
                num = Calculate.randomNumber(level, level);
                persons.add(new Person(2000+i*200, 4500, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
            else if(difficult.equals("easy")){
                if(level!=1){
                    num = Calculate.randomNumber(level-1, level);
                }
                persons.add(new Person(2000+i*200, 4500, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
                System.out.println(num);
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //Bot shoot on Target
        for(int i=0;i<spawnAlli+spawnAxis;i++){
            if(persons.get(i).veiwOfBot(player, persons, i)!=-2){
                int target = persons.get(i).veiwOfBot(player, persons, i);
                int wrongShoot = 0;
                if(difficult.equals("easy"))
                    wrongShoot = Calculate.randomNumber(0, 10);
                else if(difficult.equals("normal"))
                    wrongShoot = Calculate.randomNumber(0, 5);
                switch (persons.get(i).shootOnTarGet(player,  persons, target, wrongShoot)) {
                    case "right":
                        move.get(i).add("E");
                        move.get(i).remove("Q");
                        break;
                    case "left":
                        move.get(i).add("Q");
                        move.get(i).remove("E");
                        break;
                    case "shoot":
                        move.get(i).remove("Q");
                        move.get(i).remove("E");
                        move.get(i).add("shoot");
                        break;
                    default:
                        break;
                    }

            }
            else if(persons.get(i).getMyTank().getTurret().getRotateHead() < -5){
                move.get(i).remove("shoot");
                move.get(i).remove("Q");
                move.get(i).add("E");
                move.get(i).remove("D");
                move.get(i).remove("A");
            }
            else if(persons.get(i).getMyTank().getTurret().getRotateHead() > 5){
                move.get(i).remove("shoot");
                move.get(i).add("Q");
                move.get(i).remove("E");
                move.get(i).remove("D");
                move.get(i).remove("A");
            }
            else{
                move.get(i).remove("shoot");
                move.get(i).remove("Q");
                move.get(i).remove("E");
                move.get(i).remove("D");
                move.get(i).remove("A");
            }
         //defualt เดิน
         move.get(i).remove("A");
         move.get(i).remove("D");
         move.get(i).add("W");
            
         //นับเวลา ชนซ้าย ชนขวา deadLock
         if(persons.get(i).getDeadLock() > 0){
             persons.get(i).setDeadLock(persons.get(i).getDeadLock()-1);
         }
         else{
             move.get(i).remove("S");
         }
         if(persons.get(i).getLeftHit() > 0){
             persons.get(i).setLeftHit(persons.get(i).getLeftHit()-1);
         }
         if(persons.get(i).getRightHit() > 0){
             persons.get(i).setRightHit(persons.get(i).getRightHit()-1);
         }
         
         //เช็คชนคน
         for(Person ebot: persons){
             for(int k=0; k < ebot.getMyTank().getArmours().length; k++){
                 for(int j=0; j < ebot.getMyTank().getArmours()[k].length; j++){
                     if((persons.get(i).getCheckLeft().intersects(ebot.getMyTank().getArmours()[k][j].getBounds()) || persons.get(i).getCheckRight().intersects(ebot.getMyTank().getArmours()[k][j].getBounds())) && persons.get(i)!=ebot){
                        persons.get(i).setDeadLock(4000);
                    }
                 }
             }
             
         }  
         for(int k=0; k < player.getMyTank().getArmours().length; k++){
             for(int j=0; j < player.getMyTank().getArmours()[k].length; j++){
                 if(persons.get(i).getCheckLeft().intersects(player.getMyTank().getArmours()[k][j].getBounds()) || persons.get(i).getCheckRight().intersects(player.getMyTank().getArmours()[k][j].getBounds())){
                    persons.get(i).setDeadLock(4000);
                }
             }
         }
         //เลี้ยวเมื่อใกล้สิ่งของ
         for(Builds mObject: map.getBuilds()) {
            if(persons.get(i).getRightHit() > 0 && persons.get(i).getLeftHit() > 0){
                persons.get(i).setDeadLock(4000);
            }
            if(persons.get(i).getDeadLock() > 2500){
                move.get(i).remove("W");
                move.get(i).add("S");
            }else if(persons.get(i).getDeadLock() > 0){
                if(persons.get(i).getRndly() > persons.get(i).getRndry()){
                    move.get(i).remove("S");
                    move.get(i).remove("W");
                    move.get(i).add("A");
                }else{
                    move.get(i).remove("S");
                    move.get(i).remove("W");
                    move.get(i).add("D");
                }
            }
            else if(persons.get(i).getCheckLeft().intersects(mObject.getBounds())){
                move.get(i).remove("W");
                move.get(i).remove("A");
                move.get(i).add("D");
                persons.get(i).setLeftHit(1000);
            }
            else if(persons.get(i).getCheckRight().intersects(mObject.getBounds())){
                move.get(i).remove("W");
                move.get(i).remove("D");
                move.get(i).add("A");
                persons.get(i).setRightHit(1000);
            }

        }   

        persons.get(i).behavior(move.get(i));
      }
          
    }
    
    
    public LinkedList<Person> getPersons() {
        return persons;
    }
    
    public Timer getTime(){
        return this.time;
    }
    
    public void throwMap(Map map){
        this.map = map;
    }

    public int getSpawnAxis() {
        return spawnAxis;
    }

    public void setSpawnAxis(int spawnAxis) {
        this.spawnAxis = spawnAxis;
    }

    public int getSpawnAlli() {
        return spawnAlli;
    }

    public void setSpawnAlli(int spawnAlli) {
        this.spawnAlli = spawnAlli;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

}
