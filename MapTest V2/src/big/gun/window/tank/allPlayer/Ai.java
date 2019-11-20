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
        this.difficult = difficult;
        level = (int) CollectionTanks.tanks.get(player.getMyTank().getNameTank())[12];
        //spanw Alline
        spawnAlli = alline>max? max: alline;
        for(int i=0;i<spawnAlli;i++){
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                persons.add(new Person(2800+i*100, 100, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2800+i*100, 100, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
        }
        
        
        //spawn Axis
        spawnAxis = axis>max? max: axis;
        for(int i=0;i<spawnAxis;i++){
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                persons.add(new Person(2500+i*100, 300, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2500+i*100, 300, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(difficult){
            case "easy":
                time.setDelay(200);
                break;
            case "normal":
                time.setDelay(150);
            case "hard":
                time.setDelay(0);
            default:
                break;
        }
        
        //Bot shoot on Target
        for(int i=0;i<spawnAlli+spawnAxis;i++){
            if(persons.get(i).veiwOfBot(player, persons, i)!=-2){
                int target = persons.get(i).veiwOfBot(player, persons, i);
                switch (persons.get(i).shootOnTarGet(player,  persons, target)) {
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
         else{
             move.get(i).remove("shoot");
             move.get(i).remove("Q");
             move.get(i).remove("E");
             move.get(i).remove("D");
             move.get(i).remove("A");
         }
            
            
         if(persons.get(i).getDeadLock() < 300){
             persons.get(i).setDeadLock(persons.get(i).getDeadLock()+1);
             System.out.println(persons.get(i).getDeadLock());
         }
         else{
             move.get(i).remove("S");
         }
            
         //เลี้ยวเมื่อใกล้สิ่งของ
         move.get(i).remove("A");
         move.get(i).remove("D");
         move.get(i).add("W");
         for(Builds mObject: map.getBuilds()) {
            if(persons.get(i).getCheckLeft().intersects(mObject.getBounds()) && persons.get(i).getCheckRight().intersects(mObject.getBounds())){
                persons.get(i).setDeadLock(0);
            }
            if(persons.get(i).getDeadLock() < 300){
                move.get(i).remove("W");
                move.get(i).add("S");
                move.get(i).add("D");
            }
            else if(persons.get(i).getCheckLeft().intersects(mObject.getBounds())){
                move.get(i).remove("W");
                move.get(i).remove("A");
                move.get(i).add("D");
            }
            else if(persons.get(i).getCheckRight().intersects(mObject.getBounds())){
                move.get(i).remove("W");
                move.get(i).remove("D");
                move.get(i).add("A");
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

}
