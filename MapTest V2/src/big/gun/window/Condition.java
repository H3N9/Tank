/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;

import big.gun.window.map.Person;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;


public class Condition {
    public Player player;
    public LinkedList<Person> persons;
    private Rectangle2D rect;
    private int delay;
    
    public Condition(Player player, LinkedList<Person> persons){
        this.player = player;
        this.persons = persons;
        rect = new Rectangle2D.Double();
        delay = 0;
    }
    
    public void draw(Graphics2D g2d){
        
    }
    
    public String gameCondition(){
        //return who lose
        for(int i=0;i<persons.size();i++){
            if(persons.get(i).getTag()==2&&persons.get(i).getMyTank().getHp()>0){
                return "nothing";
            }
            else if(player.getMyTank().getHp()<=0){
                return "Alli";
            }
        }
        return "Axis";
    }
    
    
}
