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
        person = new LinkedList<Person>();
        //spanw Alline
        int spawn = alline>max? max: alline;
        for(int i=0;i<spawn;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                person.add(new Person(2400+i*100, 4500, CollectionTanks.getName(flag, num)));
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                person.add(new Person(2400+i*100, 4500, CollectionTanks.getName(flag, num)));
            }
        }
        
        
        //spawn Axis
        spawn = axis>max? max:axis;
        for(int i=0;i<spawn;i++){
            String name = player.getMyTank().getNameTank();
            int level = (int) CollectionTanks.tanks.get(name)[12];
            int flag = Calculate.randomNumber(1, 4);
            if(level==4){
                int num = 4;
                person.add(new Person(2400+i*100, 150, CollectionTanks.getName(flag, num)));
            }
            else{
                int num = Calculate.randomNumber(level, level+1);
                person.add(new Person(2600+i*100, 150, CollectionTanks.getName(flag, num)));
            }
        }
        
    }

    public LinkedList<Person> getPerson() {
        return person;
    }
    
    
    
    
}
