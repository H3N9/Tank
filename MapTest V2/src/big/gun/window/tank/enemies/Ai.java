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
import java.util.LinkedList;

/**
 *
 * @author h3n9
 */
public class Ai {
    private LinkedList<Person> person;
    private Player player;
    private final int max = 20;
    
    public Ai(int alline, int axis, Player player){
        this.player = player;
        
        //spanw Alline
        for(int i=0;i<alline;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level-1<0){
                int num = Calculate.randomNumber(level , level+1);
                person.add(new Person(2400+i*10, 100, CollectionTanks.getName(flag, num)));
            }
            else if(level+1>4){
                int num = Calculate.randomNumber(level-1, level);
                person.add(new Person(2400+i*10, 100, CollectionTanks.getName(flag, num)));
            }
        }
        
        
        //spawn Axis
        for(int i=0;i<alline;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level-1<0){
                int num = Calculate.randomNumber(level , level+1);
                person.add(new Person(2400+i*10, 4900, CollectionTanks.getName(flag, num)));
            }
            else if(level+1>4){
                int num = Calculate.randomNumber(level-1, level);
                person.add(new Person(2400+i*10, 4900, CollectionTanks.getName(flag, num)));
            }
        }
        
    }

    public LinkedList<Person> getPerson() {
        return person;
    }
    
    
    
    
}
