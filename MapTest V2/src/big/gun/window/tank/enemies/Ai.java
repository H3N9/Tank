/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank.enemies;

import big.gun.window.map.Person;
import big.gun.window.tank.*;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author h3n9
 */
public class Ai implements ActionListener{
    private LinkedList<Person> persons;
    private Player player;
    private final int max = 20;
    private Timer time;
    
    public Ai(int alline, int axis, Player player){
        this.player = player;
        persons = new LinkedList<Person>();
        time = new Timer(20, this);
        //spanw Alline
        for(int i=0;i<alline;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                persons.add(new Person(2400+i*100, 100, CollectionTanks.getName(flag, num)));
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                System.out.println(level+","+flag);
                persons.add(new Person(2400+i*100, 100, CollectionTanks.getName(flag, num)));
            }
        }
        
        
        //spawn Axis
        for(int i=0;i<axis;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                persons.add(new Person(2400+i*100, 4500, CollectionTanks.getName(flag, num)));
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                persons.add(new Person(2600+i*100, 4500, CollectionTanks.getName(flag, num)));
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        LinkedList<String> event = new LinkedList<String>();
        event.add("W");
        event.add("A");
        persons.get(0).behavior(event);
    }
    
    public LinkedList<Person> getPersons() {
        return persons;
    }
    
    public Timer getTime(){
        return this.time;
    }
    
}
