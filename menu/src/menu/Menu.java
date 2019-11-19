/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javax.swing.JFrame;

/**
 *
 * @author PCoil
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         JFrame f = new JFrame("title");
        Home p1 = new Home();
//        prePlay p2 = new prePlay();
        about p3 = new about();
//        f.add(p1);
        f.add(p3);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
    
}
