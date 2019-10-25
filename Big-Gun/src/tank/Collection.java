/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.util.*;

public class Collection {
    private HashMap<String, int[]> tanks;
    public static int amount = 5;

    public static int getAmount() {
        return amount;
    }

    public Collection() {
        tanks = new HashMap<>();
        tanks.put("m4", new int[] {30, 10, 10, 5, 600, 160, 35, 400, 4, 50, 60});
        tanks.put("m26", new int[] {60, 40, 40, 15, 700, 200, 60, 10, 3, 50, 60});
        tanks.put("tiger2", new int[] {70, 50, 50 ,20, 1000, 240, 50, 700, 1, 80, 100});
        tanks.put("tiger", new int[] {40, 40, 40, 10, 800, 180, 58, 400, 3, 60, 70});
        tanks.put("firefly", new int[] {30, 10, 10, 5, 500, 200, 65, 600, 2, 50, 60});
    }
    
    

    public int[] getTanks(String name) {
        return tanks.get(name);
    }

    public void setTanks(String name, int f, int l, int r, int b,
            int hp, int damage, int cal, 
            int reload, int speed, int width, int longed){
        tanks.put(name,new int[] {f, l, r, b, hp, damage, cal, reload, speed, width, longed});
        amount++;
    }

}
