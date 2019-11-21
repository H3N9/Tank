
package big.gun.window.tank;

import big.gun.window.Import;
import java.util.HashMap;
import java.util.Map.Entry;



public class CollectionTanks {
    public static HashMap<String, double[]> tanks;
    public static String[] name;
    public static double amount = 16;

    public static double getAmount() {
        return amount;
    }

    public CollectionTanks() {
        tanks = new HashMap<>();
        //{0frontArmor, 1LeftArmor, 2RightArmor, 3BackArmor, 4HP, 5Damage, 6Penetration, 7ReloadTime, 8Speed, 9sizeRatio, 10turretPos, 11flagTank, 12classTank}
        
        tanks.put("m4", new double[] {30, 10, 10, 5, 600, 160, 30, 4, 3.5, 0.25, 0.5, 1, 1}); // class1
        
        tanks.put("m4a3e8", new double[] {35, 15, 15, 10, 800, 180, 38, 4, 3, 0.19, 0.5, 1, 2}); //class2
        
        tanks.put("m26", new double[] {48, 40, 40, 10, 800, 180, 50, 4, 3, 0.21, 0.5, 1, 3}); //class3
        
        tanks.put("m26s", new double[] {60, 40, 40, 20, 800, 200, 57, 4.2, 2.8, 0.25, 0.5, 1, 4}); //class4
        
        //Germany Tank
        tanks.put("panzer4", new double[] {32, 12, 12, 5, 600, 160, 35, 4.2, 3.2, 0.23, 0.5, 2, 1});
        
        tanks.put("panther", new double[] {40, 20, 20, 10, 800, 160, 45, 4.5, 3, 0.17, 0.5, 2, 2});
        
        tanks.put("tiger", new double[] {50, 40, 40, 20, 800, 180, 50, 4.5, 2.8, 0.31, 0.5, 2, 3});
        
        tanks.put("tiger2", new double[] {60, 50, 50 ,30, 800, 210, 58, 7, 1.5, 0.2, 0.5, 2, 4});
        
        //Britan Tank
        tanks.put("cromwell", new double[] {30, 8, 8, 2, 600, 160, 28, 4, 4.5, 0.26, 0.4, 3, 1});
        
        tanks.put("firefly", new double[] {30, 10, 10, 5, 600, 230, 55, 6, 1.8, 0.19, 0.5, 3, 2});
        
        tanks.put("churchill", new double[] {62, 55, 55, 30, 1000, 160, 39, 4, 2, 0.19, 0.5, 3, 3});
        
        tanks.put("comet", new double[] {55, 35, 35, 20, 800, 200, 59, 4.5, 3.2, 0.21, 0.4, 3, 4});
        
        
        
        //Soviet Tank
        tanks.put("t34", new double[] {28, 10, 10, 5, 600, 160, 29, 3, 3.8, 0.2, 0.4, 4, 1});
        
        tanks.put("kv1", new double[] {50, 35, 35, 20, 800, 160, 35, 4, 2.8, 0.17, 0.4, 4, 2});
        
        tanks.put("is2", new double[] {55, 38, 38, 20, 800, 180, 55, 4, 2.5, 0.2, 0.4, 4, 3});
        
        tanks.put("is3", new double[] {61, 45, 45, 25, 800, 200, 58, 5, 2.8, 0.23, 0.4, 4, 4});
        
        name = new String[] {"m4", "panzer4", "cromwell", "t34",
                             "m4a3e8", "panther", "firefly", "kv1",
                             "m26", "tiger", "churchill", "is2",
                             "m26s", "tiger2", "comet", "is3"};
                             //0-3 class 1, 4-7 class 2, 8-11 class 3, 12-15 class 4
                             //american 0 4 8 12
                             //german 1 5 9 13
                             //britan 2 6 10 14
                             //soviet 3 7 11 15
    }
    
    

    public double[] getTanks(String name) {
        return tanks.get(name);
    }
    
    public static String getName(int flag, int level){
        return name[(flag+(4*(level-1)))-1];
    }


    public void setTanks(String name, double f, double l, double r, double b,
            double hp, double damage, double cal, 
            double reload, double speed, double width, double longed){
        tanks.put(name,new double[] {f, l, r, b, hp, damage, cal, reload, speed, width, longed});
        amount++;
    }    
}
