
package big.gun.window;


import big.gun.window.map.Builds;
import big.gun.window.map.Map;
import big.gun.window.map.Person;
import big.gun.window.sound.Sound;
import big.gun.window.tank.Calculate;
import big.gun.window.tank.CollectionTanks;
import big.gun.window.tank.Tank;
import big.gun.window.tank.TestDrawTank;
import big.gun.window.tank.allPlayer.Player;
import big.gun.window.tank.allPlayer.Ai;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{
    private Timer start;
    private Player player;
    private Map map;
    private Import importImg;
    private CollectionTanks collection;
    private Ai bot;
    private static long lastFPS;
    private static int currentFPS, totalFrames;
    private Condition con;
    private String whoLose, diff;
    private Hub hub;
    private int delay, delayEnd, countMoney;
    
    //private TestDrawTank tdt;
    
    public Game(String name, int amountBout, String diff){
        importImg = new Import();
        collection = new CollectionTanks();
        start = new Timer(10, this);
        player = new Player(name, (Window.width*0.5)-(Import.tankImg.get(name)[0].getWidth()*CollectionTanks.tanks.get(name)[9])/2, (Window.height*0.5)-(Import.tankImg.get(name)[0].getHeight()*CollectionTanks.tanks.get(name)[9])/2);
        Sound.playerPosX = (Window.width*0.5)-(Import.tankImg.get(name)[0].getWidth()*CollectionTanks.tanks.get(name)[9])/2;
        Sound.playerPosY = (Window.height*0.5)-(Import.tankImg.get(name)[0].getHeight()*CollectionTanks.tanks.get(name)[9])/2;
        Sound.downLoadSound();
        bot = new Ai(amountBout-1, amountBout, player, diff);
        hub = new Hub(player, bot);
        con = new Condition(player, bot.getPersons(), this);
        addKeyListener(con);
        whoLose = "nothing";
        this.diff = diff;
        delay = 0; countMoney = 0; delayEnd = 0;
        map = new Map(1800-Window.width/2, 4700-Window.height/2, bot.getPersons());
        bot.throwMap(map);
        start.start();
        bot.getTime().start();
        addKeyListener(new Input(player));
        setFocusable(true);
        
    }
    
    public void updateTank(){
        player.getMyTank().move(); 
    }
    public void updateBullet(){
        if(player.getMyTank().getShell().isIsShot()){
            player.getMyTank().getShell().move(); 
        }
    }
    
    public void FPS(){
       totalFrames++;
        if(System.nanoTime()>lastFPS+1000000000){
            lastFPS = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        } 
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //Draw below this
        map.draw(g2d);
        player.draw(g2d);
        //FPS
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("", 20,20));
        g2d.drawString("FPS: "+currentFPS, 10, 20);
        hub.draw(g2d);
        con.draw(g2d);
        if(whoLose.equals("Axis")){
            if(delayEnd<500){
                double bonus = 0;
                switch(diff){
                    case "easy":
                        bonus = 1.2;
                        break;
                    case "normal":
                        bonus = 1.5;
                        break;
                    case "hard":
                        bonus = 2;
                        break;    
                }
                g2d.setColor(Color.black);
                g2d.fillRect(0, 0, Window.width, Window.height);
                g2d.setColor(Color.white);
                g2d.setFont(new Font("Impack", 30, 30));
                g2d.drawString("Money: "+countMoney, Window.width/2-80, Window.height/2-40);
                if(countMoney<=player.getGotMoney()){
                    countMoney += 5;
                }
                else{
                    g2d.drawString("Difficult: "+(player.getGotMoney()*bonus)+" x "+bonus, Window.width/2-80, Window.height/2+0);
                    g2d.drawString("Wallet: "+(SaveGame.LoadSave().getMoney()+player.getGotMoney()*bonus), Window.width/2-80, Window.height/2+40);

                }
                if(delay-player.getGotMoney()<500){
                    delay += 5;
                }
                else{
                    con.gotBonus(bonus);
                    g2d.drawString("Press: Enter back to menu", Window.width/2-160, Window.height/2+100);
                }
            }
            else{
                delayEnd++;
            }
        }else if(whoLose.equals("Alli")){
            if(delayEnd<500){
                double bonus = 0;
                switch(diff){
                    case "easy":
                        bonus = 1;
                        break;
                    case "normal":
                        bonus = 1.2;
                        break;
                    case "hard":
                        bonus = 1.5;
                        break;    
                }
                g2d.setColor(Color.black);
                g2d.fillRect(0, 0, Window.width, Window.height);
                g2d.setColor(Color.white);
                g2d.setFont(new Font("Impack", 30, 30));
                g2d.drawString("Money: "+countMoney, Window.width/2-80, Window.height/2-40);
                if(countMoney<=player.getGotMoney()){
                    countMoney += 5;
                }
                else{
                    g2d.drawString("Difficult: "+(player.getGotMoney()*bonus)+" x "+bonus, Window.width/2-80, Window.height/2);
                    g2d.drawString("Wallet: "+(SaveGame.LoadSave().getMoney()+player.getGotMoney()*bonus), Window.width/2-80, Window.height/2+40);

                }
                if(delay-player.getGotMoney()<500){
                    delay += 5;
                }
                else{
                    con.gotBonus(bonus);
                    g2d.drawString("Press: Enter back to menu", Window.width/2-160, Window.height/2+100);
                }
            }
            else{
                delayEnd++;
            }
        
        }
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        updateTank();
        map.update();
        map.moveMap(player);
        map.playerCollision(player, bot);
        map.bulletCollision(bot, player);
        hub.update(player, bot);
        FPS();
        if(whoLose.equals("nothing")){
            whoLose = con.gameCondition();
        }
        repaint();
    }
    
    public void gameClose(){
        start.stop();
        bot.getTime().stop();
    }

    /**
     * @return the start
     */
    public Timer getStart() {
        return start;
    }

    /**
     * @param aStart the start to set
     */
    public void setStart(Timer Start) {
        this.start = Start;
    }
    
}
