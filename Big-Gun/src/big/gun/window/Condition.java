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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import menu.Home;
import menu.setting;


public class Condition extends KeyAdapter{
    public Player player;
    public LinkedList<Person> persons;
    private Game game;
    private Rectangle2D rect;
    private boolean isNextPage;
    
    public Condition(Player player, LinkedList<Person> persons, Game game){
        this.player = player;
        this.game = game;
        this.persons = persons;
        rect = new Rectangle2D.Double();
        isNextPage = false;
    }
    
    public void draw(Graphics2D g2d){
        
    }
    
    public String gameCondition(){
        //return who lose
        for(int i=0;i<persons.size();i++){
            if(persons.get(i).getTag()==2&&persons.get(i).getMyTank().getHp()>0){
                return "nothing";
            }
            if(player.getMyTank().getHp()<=0){
                return "Alli";
            }
        }
        return "Axis";
    }
    
    public void keyPressed(KeyEvent e){
       int key = e.getKeyCode();
       if(key==KeyEvent.VK_ENTER&&(gameCondition().equals("Alli")||gameCondition().equals("Axis"))){
           isNextPage = true;
           //this.setVisible(false);
       }
       nextPage();
    }
    
//    public void keyReleased(KeyEvent e){
//        int key = e.getKeyCode();
//        if (key == KeyEvent.VK_ENTER && isNextPage){
//            isNextPage = false;
//        }
//    }
    
    private void nextPage(){
        if (isNextPage){
            SaveGame.Save(player.getGotMoney(), "");
           //Window.jframe.dispatchEvent(new WindowEvent(Window.jframe, WindowEvent.WINDOW_CLOSING));
           game.gameClose();
           Home f = new Home();
           f.setTitle("Big Gun");
           f.setVisible(true);
           f.setLocationRelativeTo(null);
           isNextPage = false;
        }
    }
}
