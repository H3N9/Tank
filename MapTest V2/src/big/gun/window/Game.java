
package big.gun.window;


import big.gun.window.map.Builds;
import big.gun.window.map.Map;
import big.gun.window.map.Person;
import big.gun.window.tank.Calculate;
import big.gun.window.tank.CollectionTanks;
import big.gun.window.tank.Tank;
import big.gun.window.tank.TestDrawTank;
import big.gun.window.tank.allPlayer.Player;
import big.gun.window.tank.enemies.Ai;
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
    
    
    //private TestDrawTank tdt;
    
    public Game(){
        importImg = new Import();
        collection = new CollectionTanks();
        start = new Timer(10, this);
        player = new Player("tiger", 510, 250);
        bot = new Ai(5, 5, player);
        map = new Map(250, 1750, bot);
        start.start();
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
    
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //Draw below this
        map.draw(g2d);
        player.draw(g2d);
        g2d.drawRect(170, 170, Window.width-170-210, Window.height-170-210);
        //tdt = new TestDrawTank(g2d);
        
    }
    
    
    private void moveMap(){
        playerColison();
        int check = 0;
        Tank pTank = player.getMyTank();
        if (pTank.getPosX()+pTank.getWidth()+210 >= Window.width){
            check = 1;
        }
        
        else if (pTank.getPosY()+pTank.getHeight()+210 >= Window.height){
            check = 1;
        }
        
        else if (pTank.getPosX()<= 170){
            check = 1;
        }
        
        else if (pTank.getPosY() <= 170){
            check = 1;
        }
        
        if (check == 1){
            map.setPosX(map.getPosX()-Calculate.calculateMoveX(pTank.getRotate(), pTank.getSpeedX()));
            map.setPosY(map.getPosY()-Calculate.calculateMoveY(pTank.getRotate(), pTank.getSpeedY()));
            pTank.moveStop();
        }
    }
    public void playerColison(){
        Builds build;
        Tank pTank = player.getMyTank();
        for (int i=0; i < map.getBuilds().size(); i++){
            build = map.getBuilds().get(i);
            for(int n=0; n < pTank.getArmours().length; n++){
                for(int m=0; m < pTank.getArmours()[n].length; m++){
                    if( pTank.getArmours()[n][m].getBounds().intersects(build.getBounds())){
                        pTank.moveStop("non-Rotate");
                        break;
                    }
                }
            }
            for (Person enemy: map.getEnemys()){
                if (enemy.getMyTank().getBounds().intersects(build.getBounds())){
                    enemy.moveStop();
                }
                
//                  บัคไรก็ไม่รู้
//                if (enemy.getMyTank().getBounds().intersects(pTank.getBounds())){
//                    enemy.moveStop();
//                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        updateTank();
        updateBullet();
        map.update();
        this.moveMap();
        playerColison();
        repaint();
    }
    
}
