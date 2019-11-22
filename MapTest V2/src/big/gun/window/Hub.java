/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;

import big.gun.window.map.Person;
import big.gun.window.tank.allPlayer.Ai;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author USER
 */
public class Hub {
    private double playerHp, playerReload;
    private LinkedList<Person> persons;
    private int allies, enemies;
    
    public Hub(Player player, Ai bot){
        playerHp = player.getMyTank().getHp();
        playerReload = player.getMyTank().getReload();
        persons = bot.getPersons();
        allies = bot.getSpawnAlli();
        enemies = bot.getSpawnAxis();
    }
    
    public void draw(Graphics2D g2d){
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
        g2d.setColor(Color.blue);
        g2d.drawString("Allies "+allies,(float) 530, (float) 35);
        g2d.setColor(Color.white);
        g2d.drawString(":",(float) 630, (float) 35);
        g2d.setColor(Color.red);
        g2d.drawString(enemies+" Enemies ",(float) 650, (float) 35);
    }
    
    public void update(Player player, Ai bot){
        playerHp = player.getMyTank().getHp();
        playerReload = player.getMyTank().getReload();
        persons = bot.getPersons();
        allies = bot.getSpawnAlli();
        enemies = bot.getSpawnAxis();
        for(int i=0; i < bot.getPersons().size(); i++){
            if(i >= 0 && i < bot.getSpawnAlli()){
                if(bot.getPersons().get(i).getMyTank().getHp() <= 0){
                    allies--;
                }
            }else{
                if(bot.getPersons().get(i).getMyTank().getHp() <= 0){
                    enemies--;
                }
            }
        }
        if(player.getMyTank().getHp() > 0){
            allies++;
        }
    }
}
