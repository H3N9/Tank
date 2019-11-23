/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;

import big.gun.window.map.Person;
import big.gun.window.tank.CollectionTanks;
import big.gun.window.tank.allPlayer.Ai;
import big.gun.window.tank.allPlayer.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

/**
 *
 * @author USER
 */
public class Hub {
    private double playerHp, playerReload, fullReload;
    private LinkedList<Person> persons;
    private int allies, enemies;
    private String name;
    
    public Hub(Player player, Ai bot){
        playerHp = player.getMyTank().getHp();
        playerReload = player.getMyTank().getReload();
        persons = bot.getPersons();
        allies = bot.getSpawnAlli();
        enemies = bot.getSpawnAxis();
        name = player.getMyTank().getNameTank();
        fullReload = player.getMyTank().getSpeedReload();
    }
    
    public void draw(Graphics2D g2d){
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
        g2d.setColor(Color.blue);
        g2d.drawString("Allies "+allies,(float) Window.width/2-90, (float) 35);
        g2d.setColor(Color.white);
        g2d.drawString(":",(float) Window.width/2, (float) 35);
        g2d.setColor(Color.red);
        g2d.drawString(enemies+" Enemies ",(float) Window.width/2+15, (float) 35);
        g2d.setColor(new Color(80, 80, 80));
        g2d.fill(new Rectangle2D.Double(0, Window.height-115, 350, 115));
        g2d.setColor(Color.white);
        g2d.drawString("HP",(float) 10, (float) Window.height-80);
        g2d.drawString("Reload",(float) 10, (float) Window.height-45);
        g2d.setColor(new Color((int)(255*((CollectionTanks.tanks.get(name)[4]-playerHp)/CollectionTanks.tanks.get(name)[4])), (int) (255*(playerHp/CollectionTanks.tanks.get(name)[4])), 0));
        g2d.fill(new Rectangle2D.Double(100, Window.height-105, 200*(playerHp/CollectionTanks.tanks.get(name)[4]), 25));
        g2d.setColor(new Color(191, 191, 191));
        g2d.fill(new Rectangle2D.Double(100, Window.height-70, 200*(playerReload/fullReload), 25));
        g2d.setColor(Color.white);
        g2d.fill(new Rectangle2D.Double(300, Window.height-105, 10, 25));
        g2d.fill(new Rectangle2D.Double(300, Window.height-70, 10, 25));
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
