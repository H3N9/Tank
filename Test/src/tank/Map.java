/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
    public Map(){}
    
    public void Draw(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, Window.getWidth(), Window.getHeight());
    }
}
