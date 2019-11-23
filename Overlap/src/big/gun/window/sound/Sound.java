/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window.sound;

import big.gun.window.Window;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import java.util.HashMap;

/**
 *
 * @author pooh
 */
public class Sound implements Runnable {

    public static double playerPosX, playerPosY;
    private float dB;
    public static HashMap<String, Clip> sounds;
    public Clip clip;

    public Sound(String name, double posX, double posY) {
        //File file = new File(path);
        if (posX > -100 && posX < Window.width + 100 && posY > -100 && posY < Window.height + 100) {
            clip = sounds.get(name);
            Thread t1 = new Thread(this);
            t1.start();
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double vol = ((float) Math.sqrt(Math.pow(playerPosX - posX, 2) + Math.pow(playerPosY - posY, 2)) / 5000) * 50;
            System.out.println(vol / 20);
            dB = (float) (Math.log(1 - vol / 20) / Math.log(10) * 20);
            gain.setValue(dB);
        }
    }

    /**
     * @return the clip
     */
    public static void downLoadSound() {
        String[] name = {"notPenetrate", "penetrate", "reload1", "shoot", "tankDestroyed"};
        sounds = new HashMap<String, Clip>();
        Clip clip;
        for (int i = 0; i < name.length; i++) {
            try {
                AudioInputStream sound = AudioSystem.getAudioInputStream(Sound.class.getResource("/res/sound/" + name[i] + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.setFramePosition(0);
                sounds.put(name[i], clip);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
//        try {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            gain.setValue(dB);
            while (true){
                if (clip.getFramePosition() >= clip.getFrameLength()){
                    clip.setFramePosition(0);
                    clip.stop();
                    break;
                }
            }
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
