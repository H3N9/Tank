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
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

public class Game extends JPanel implements ActionListener{
    public Timer loop;
    public Player p;
    public static double x, y;
    public static LinkedList<Builds> b;
    public static int state;
    
    public Game(){
        loop = new Timer(6, this);
        loop.start();
        x = 0.0;
        y = 0.0;
        p = new Player(100, 300);
        b = new LinkedList<Builds>();
        b.add(new Builds());
        b.add(new Builds(100, 120));
        b.add(new Builds(250, 120));
        this.addKeyListener(new KeyInputs(p));
        this.setFocusable(true);
    }
    
    //จัดการการวาดภาพ
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Builds.buildsPaint(b, g2d);
        p.draw(g2d);
    }
    
    //ทำงานตลอดเวลา
    @Override
    public void actionPerformed(ActionEvent ae) {
        p.update();
        Builds.buildsUpdate(b);
        this.repaint();
    }
}
