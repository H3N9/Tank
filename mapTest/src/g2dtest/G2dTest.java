/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2dtest;

/**
 *
 * @author pooh
 */
import javax.swing.JFrame;

public class G2dTest {

    /**
     */
    public static final int WIDTH=1080, HEIGHT=720;
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window = new JFrame("Test");
        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new Game());
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    
}
