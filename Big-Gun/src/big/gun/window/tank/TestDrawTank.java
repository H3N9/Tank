/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.tank;

import big.gun.window.Import;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author USER
 */
public class TestDrawTank {
    private BufferedImage img;
    private Double ratio, turretPos ;
    private int tankWidth, tankHeight, turretWidth, turretHeight, turretPosX, turretPosY;
    public TestDrawTank(Graphics2D g2d){
        draw(g2d, 10, 10, "m4");
        draw(g2d, 210, 10, "m4a3e8");
        draw(g2d, 410, 10, "m26");
        draw(g2d, 610, 10, "m26s");
        
        draw(g2d, 10, 260, "panzer4");
        draw(g2d, 210, 260, "panther");
        draw(g2d, 410, 260, "tiger");
        draw(g2d, 610, 260, "tiger2");
        
        draw(g2d, 10, 510, "t34");
        draw(g2d, 210, 510, "kv1");
        draw(g2d, 410, 510, "is2");
        draw(g2d, 610, 510, "is3");
        
        draw(g2d, 10, 760, "cromwell");
        draw(g2d, 210, 760, "churchill");
        draw(g2d, 410, 760, "comet");
        draw(g2d, 610, 760, "firefly");
    }
    
    public void draw(Graphics2D g2d, int posX, int posY, String name){
        img = Import.tankImg.get(name)[0];
        ratio = CollectionTanks.tanks.get(name)[9];
        turretPos = CollectionTanks.tanks.get(name)[10];
        tankWidth = Import.tankImg.get(name)[0].getWidth();
        tankHeight = Import.tankImg.get(name)[0].getHeight();
        turretWidth = Import.tankImg.get(name)[1].getWidth();
        turretHeight = Import.tankImg.get(name)[1].getHeight();
        turretPosX = (int) (posX+(tankWidth*ratio)/2-(turretWidth*ratio)/2);
        turretPosY = (int) (posY+(tankHeight*ratio)*turretPos-(turretHeight*ratio)/2);
        g2d.drawImage(img, posX, posY, (int) (img.getWidth()*ratio), (int) (img.getHeight()*ratio), null);
        img = Import.tankImg.get(name)[1];
        g2d.drawImage(img, turretPosX, turretPosY, (int) (img.getWidth()*ratio), (int) (img.getHeight()*ratio), null);
        img = Import.tankImg.get(name)[2];
        g2d.drawImage(img, (int)(turretPosX+turretWidth*ratio/2-img.getWidth()*ratio/2), (int)(turretPosY-img.getHeight()*ratio), (int) (img.getWidth()*ratio), (int) (img.getHeight()*ratio), null);
    }
}
