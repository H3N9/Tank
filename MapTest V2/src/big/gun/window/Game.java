
package big.gun.window;


import big.gun.window.map.Builds;
import big.gun.window.map.Map;
import big.gun.window.map.Person;
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
    private String nameTank;
    
    //private TestDrawTank tdt;
    
    public Game(){
        importImg = new Import();
        collection = new CollectionTanks();
        start = new Timer(10, this);
        nameTank = "cromwell";
        player = new Player(nameTank, (Window.width*0.5)-(Import.tankImg.get(nameTank)[0].getWidth()*CollectionTanks.tanks.get(nameTank)[9])/2, (Window.height*0.5)-(Import.tankImg.get(nameTank)[0].getHeight()*CollectionTanks.tanks.get(nameTank)[9])/2);
        bot = new Ai(1, 1, player);
        map = new Map(2000, 0, bot.getPersons());
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
        
    }
    
    public void bulletCollision(){
        int breakall = 0;
            //hit map's objects
            for (int i=0; i < map.getBuilds().size(); i++){
                //player's bullet
                try{
                    if(player.getMyTank().getShell().getBounds().intersects(map.getBuilds().get(i).getBounds())){
                        player.getMyTank().setShell(null);
                    }
                }catch(NullPointerException e){
                    
                }
                
                //bot's bullets
                for(Person ebot: bot.getPersons()){
                    try{
                        if(ebot.getMyTank().getShell().getBounds().intersects(map.getBuilds().get(i).getBounds())){
                            ebot.getMyTank().setShell(null);
                        }
                    }catch(NullPointerException e){
            
                    }
                }
            }
            
        //player shoots bottank's armours
        breakall = 0;
        for(Person ebot: bot.getPersons()){
            for(int n=0; n < ebot.getMyTank().getArmours().length; n++){
                    for(int m=0; m < ebot.getMyTank().getArmours()[n].length; m++){
                        try{
                            if( player.getMyTank().getShell().getBounds().intersects(ebot.getMyTank().getArmours()[n][m].getBounds())){
                                double thickness;
                                if(n==0){
                                    thickness = ebot.getMyTank().getThickness()[0];
                                }
                                else if(n==ebot.getMyTank().getSizeOfArmoursArray()-1){
                                    thickness = ebot.getMyTank().getThickness()[3];
                                }
                                else{
                                    thickness = ebot.getMyTank().getThickness()[1];
                                }
                                
                                if(player.getMyTank().getShell().getPenetration() >= thickness){
                                    ebot.getMyTank().setHp(ebot.getMyTank().getHp()-player.getMyTank().getShell().getDamage());
                                    System.out.println(ebot.getMyTank().getHp());
                                }
                                else{
                                    System.out.println("not penetrate");
                                }
                                

                                player.getMyTank().setShell(null);
                                breakall = 1;
                                break;
                            }
                        }catch(NullPointerException e){
                            
                        }
                        
                    }
                    if(breakall == 1){break;}
                }
            if(breakall == 1){break;}
        }
        
        //bot shoots armours
        for(Person ebot: bot.getPersons()){
            //bot shoot player
            breakall = 0;
            for(int i=0; i < player.getMyTank().getArmours().length; i++){
                for(int j=0; j < player.getMyTank().getArmours()[i].length; j++){
                    try{
                        if(ebot.getMyTank().getShell().getBounds().intersects(player.getMyTank().getArmours()[i][j].getBounds())){
                            double thickness;
                            if(i==0){
                                thickness = player.getMyTank().getThickness()[0];
                            }
                            else if(i==player.getMyTank().getSizeOfArmoursArray()-1){
                                thickness = player.getMyTank().getThickness()[3];
                            }
                            else{
                                thickness = player.getMyTank().getThickness()[1];
                            }
                            
                            if(ebot.getMyTank().getShell().getPenetration() >= thickness){
                                player.getMyTank().setHp(player.getMyTank().getHp()-ebot.getMyTank().getShell().getDamage());
                            }
                            
                            ebot.getMyTank().setShell(null);
                            breakall = 1;
                            break;
                        }
                    }catch(NullPointerException e){
                        
                    }
                }
                if(breakall == 1){break;}
            }
            if(breakall == 1){continue;}
            
            //bot shoot bot
            for(Person ebot2: bot.getPersons()){
                for(int p=0; p < ebot2.getMyTank().getArmours().length; p++){
                    for(int q=0; q < ebot2.getMyTank().getArmours()[p].length; q++){
                        try{
                            if(ebot.getMyTank().getShell().getBounds().intersects(ebot2.getMyTank().getArmours()[p][q].getBounds()) && ebot != ebot2){
                                double thickness;
                                if(p==0){
                                    thickness = ebot2.getMyTank().getThickness()[0];
                                }
                                else if(p==player.getMyTank().getSizeOfArmoursArray()-1){
                                    thickness = ebot2.getMyTank().getThickness()[3];
                                }
                                else{
                                    thickness = ebot2.getMyTank().getThickness()[1];
                                }
                                
                                if(ebot.getMyTank().getShell().getPenetration() >= thickness){
                                    ebot2.getMyTank().setHp(ebot2.getMyTank().getHp()-ebot.getMyTank().getShell().getDamage());
                                }
                                ebot.getMyTank().setShell(null);
                                breakall = 1;
                                break;
                            }
                        }catch(NullPointerException e){
                            
                        }
                    }
                    if(breakall == 1){break;}
                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        updateTank();
        map.update();
        map.moveMap(player);
        map.playerCollision(player, bot);
        bulletCollision();
        FPS();
        repaint();
    }
    
}
