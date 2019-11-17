/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.HashMap;

/**
 *
 * @author USER
 */
public class Import {
    public static HashMap<String, BufferedImage[]> tankImg;
    private BufferedImage hull, turret, gun;
    
    public Import(){
        tankImg = new HashMap<>();
        downloadImage("m4");
        downloadImage("m4a3e8");
        downloadImage("m26");
        downloadImage("m26s");
        
        downloadImage("panzer4");
        downloadImage("panther");
        downloadImage("tiger");
        downloadImage("tiger2");
        
        downloadImage("t34");
        downloadImage("kv1");
        downloadImage("is2");
        downloadImage("is3");
        
        downloadImage("cromwell");
        downloadImage("churchill");
        downloadImage("comet");
        downloadImage("firefly");
    }
    
    public void downloadImage(String name){
        try {
            hull = ImageIO.read(getClass().getResource("/res/"+name+"_hull.png"));
            turret = ImageIO.read(getClass().getResource("/res/"+name+"_turret.png"));
            gun = ImageIO.read(getClass().getResource("/res/"+name+"_gun.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tankImg.put(name, new BufferedImage[] {hull, turret, gun});
    }
}
