
package big.gun;

import big.gun.window.SaveGame;
import big.gun.window.Window;
import big.gun.window.tank.CollectionTanks;
import menu.Home;


public class BigGun {

    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Home h = new Home();
                h.setTitle("Big Gun");
                h.setVisible(true);
                h.setLocationRelativeTo(null);
            }
        }); 
//        new Window(1280, 720, "t34", 1, "easy");
    }
}