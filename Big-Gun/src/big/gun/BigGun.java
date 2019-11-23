
package big.gun;

import big.gun.window.Window;
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
    }
}