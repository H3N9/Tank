
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
        nameTank = "firefly";
        player = new Player(nameTank, (Window.width*0.5)-(Import.tankImg.get(nameTank)[0].getWidth()*CollectionTanks.tanks.get(nameTank)[9])/2, (Window.height*0.5)-(Import.tankImg.get(nameTank)[0].getHeight()*CollectionTanks.tanks.get(nameTank)[9])/2);
        bot = new Ai(5, 5, player);
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
        g2d.drawRect(170, 170, Window.width-170-210, Window.height-170-210);
        //FPS
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("", 20,20));
        g2d.drawString("FPS: "+currentFPS, 10, 20);
        
    }
    
    
    private void moveMap(){
        int check = 0;
        Tank pTank = player.getMyTank();
        if (pTank.getPosX()+pTank.getWidth()+(Window.width*0.3) >= Window.width){
            check = 1;
        }
        
        else if (pTank.getPosY()+pTank.getHeight()+(Window.width*0.3) >= Window.height){
            check = 1;
        }
        
        else if (pTank.getPosX()<= (Window.width*0.3)){
            check = 1;
        }
        
        else if (pTank.getPosY() <= (Window.width*0.3)){
            check = 1;
        }
        
        if (check == 1){
            map.setPosX(map.getPosX()-Calculate.calculateMoveX(pTank.getRotate(), pTank.getSpeedX()));
            map.setPosY(map.getPosY()-Calculate.calculateMoveY(pTank.getRotate(), pTank.getSpeedY()));
            pTank.moveStop();
        }
    }
    public void playerCollision(){
        Builds build;
        Tank pTank = player.getMyTank();
        int breakall = 0;
        
        //เช็คชนสิ่งของ
        for (int i=0; i < map.getBuilds().size(); i++){
            build = map.getBuilds().get(i);
            //player ชนสิ่งของ
            for(int n=0; n < pTank.getArmours().length; n++){
                for(int m=0; m < pTank.getArmours()[n].length; m++){
                    if( pTank.getArmours()[n][m].getBounds().intersects(build.getBounds())){
                        pTank.moveRotateStop();
                        breakall = 1;
                        break;
                    }
                }
                if(breakall == 1){break;}
            }
            //bot ชนสิ่งของ
            breakall = 0;
            for (Person ebot: bot.getPersons()){
                for(int n=0; n < ebot.getMyTank().getArmours().length; n++){
                    for(int m=0; m < ebot.getMyTank().getArmours()[n].length; m++){
                        if( ebot.getMyTank().getArmours()[n][m].getBounds().intersects(build.getBounds())){
                            ebot.moveStop();
                            breakall = 1;
                            break;
                        }
                    }
                    if(breakall == 1){break;}
                }
            }
        }
        
        
        //player ชนคนอื่น
        breakall = 0;
        for(int n=0; n < pTank.getArmours().length; n++){
                for(int m=0; m < pTank.getArmours()[n].length; m++){
                    for(Person ebot: bot.getPersons()){
                        for(int p=0; p < ebot.getMyTank().getArmours().length; p++){
                            for(int q=0; q < ebot.getMyTank().getArmours()[p].length; q++){
                                if(pTank.getArmours()[n][m].getBounds().intersects(ebot.getMyTank().getArmours()[p][q].getBounds())){
                                    pTank.moveStop();
                                    ebot.moveStop();
                                    breakall = 1;
                                    break;
                                }
                            }
                            if(breakall == 1){break;}
                        }
                        if(breakall == 1){break;}
                    }
                    if(breakall == 1){break;}
                }
                if(breakall == 1){break;}
            }
        
        //bot ชน bot
        breakall = 0;
        for(Person ebot: bot.getPersons()){
            for(int m=0; m < ebot.getMyTank().getArmours().length; m++){
                for(int n=0; n < ebot.getMyTank().getArmours()[m].length; n++){
                    for(Person ebot2: bot.getPersons()){
                        for(int p=0; p < ebot2.getMyTank().getArmours().length; p++){
                            for(int q=0; q < ebot2.getMyTank().getArmours()[p].length; q++){
                                if(ebot.getMyTank().getArmours()[m][n].getBounds().intersects(ebot2.getMyTank().getArmours()[p][q].getBounds()) && ebot != ebot2){
                                    ebot.moveStop();
                                    ebot2.moveStop();
                                    breakall = 1;
                                    break;
                                }
                            }
                            if(breakall == 1){break;}
                        }
                        if(breakall == 1){break;}
                    }
                    if(breakall == 1){break;}
                }
                if(breakall == 1){break;}
            }
            if(breakall == 1){break;}
        }
    }
    
    public void bulletCollision(){
        
            for (int i=0; i < map.getBuilds().size(); i++){
                //player's bullet
                try{
                    if(player.getMyTank().getShell().getBounds().intersects(map.getBuilds().get(i).getBounds())){
                        System.out.println("myhit");
                        player.getMyTank().setShell(null);
                    }
                }catch(NullPointerException e){
                    
                }
                
                //bot's bullets
                for(Person ebot: bot.getPersons()){
                    try{
                        if(ebot.getMyTank().getShell().getBounds().intersects(map.getBuilds().get(i).getBounds())){
                            System.out.println("ebothit");
                            ebot.getMyTank().setShell(null);
                        }
                    }catch(NullPointerException e){
            
                    }
                }
            }

    }
    
    public void actionPerformed(ActionEvent ae) {
        updateTank();
        updateBullet();
        map.update();
        this.moveMap();
        playerCollision();
        bulletCollision();
        FPS();
        repaint();
    }
    
}
