/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.sound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 *
 * @author pooh
 */
public class Sound {
    public static double playerPosX, playerPosY;
    private Clip clip;
    
    public Sound(String path, double posX, double posY){
        //File file = new File(path);
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("/res/sound/"+path+".wav"));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double vol = ((float)Math.sqrt(Math.pow(playerPosX-posX, 2)+Math.pow(playerPosY-posY, 2))/5000)*50;
            System.out.println(playerPosX+", "+playerPosY+", "+posX+", "+posY);
            System.out.println(vol/20);
            float dB = (float)(Math.log(1-vol/20)/Math.log(10)*20);
            gain.setValue(dB);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the clip
     */
    public Clip getClip() {
        return clip;
    }
}
