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
    private BufferedImage hull, turret;
    
    public Import(){
        tankImg = new HashMap<>();
        try {
            hull = ImageIO.read(getClass().getResource("/res/tiger_hull.png"));
            turret = ImageIO.read(getClass().getResource("/res/tiger_turret.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tankImg.put("tiger", new BufferedImage[] {hull, turret});
    }   
}
