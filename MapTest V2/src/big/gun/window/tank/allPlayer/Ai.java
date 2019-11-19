/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank.allPlayer;

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
    
    public Ai(int alline, int axis, Player player){
        this.player = player;
        persons = new LinkedList<Person>();
        move = new LinkedList<>();
        time = new Timer(10, this);
        //spanw Alline
        spawnAlli = alline>max? max: alline;
        for(int i=0;i<spawnAlli;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                persons.add(new Person(2400+i*100, 100, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2400+i*100, 100, CollectionTanks.getName(flag, num), 1));
                move.add(new HashSet<String>());
            }
        }
        
        
        //spawn Axis
        spawnAxis = axis>max? max: axis;
        for(int i=0;i<spawnAxis;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                persons.add(new Person(2400+i*100, 4500, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2600+i*100, 4500, CollectionTanks.getName(flag, num), 2));
                move.add(new HashSet<String>());
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        event = new LinkedList<String>();
//        event.add("W");
//        event.add("A");
//        persons.get(1).behavior(event);
//        event.remove("A");
//        event.add("D");
//        persons.get(4).behavior(event);

          for(int i=0;i<spawnAlli;i++){
             if(persons.get(i).veiwOfBot(player)){
                move.get(i).add("W");
                move.get(i).add("A");
                persons.get(i).behavior(move.get(i));
             }
             else{
                 move.get(i).remove("A");
             }
             
              
          }
          
          
    }
    
    
    public LinkedList<Person> getPersons() {
        return persons;
    }
    
    public Timer getTime(){
        return this.time;
    }
    
}
