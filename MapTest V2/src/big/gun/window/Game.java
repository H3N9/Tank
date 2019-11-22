
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
    private String whoLose;
    private Hub hub;
    
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
        con = new Condition(player, bot.getPersons());
        whoLose = "nothing";
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
        try{
            player.getMyTank().getShell().move(); 
        }catch(Exception e){
            
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
            addKeyListener(con);
            g2d.setColor(Color.BLUE);
            g2d.fillRect(0, 0, Window.width, Window.height);
            g2d.setColor(Color.white);
            g2d.drawString("Money: "+player.getGotMoney(), Window.width/2-50, Window.height/2);
        }else if(whoLose.equals("Alli")){
            addKeyListener(con);
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, Window.width, Window.height);
            g2d.setColor(Color.white);
            g2d.drawString("Money: "+player.getGotMoney(), Window.width/2-50, Window.height/2);
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
    
}
