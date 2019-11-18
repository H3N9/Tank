
package big.gun.window.tank;

import big.gun.window.Import;
import java.util.HashMap;



public class CollectionTanks {
    public static HashMap<String, double[]> tanks;
    public static double amount = 5;

    public static double getAmount() {
        return amount;
    }

    public CollectionTanks() {
        tanks = new HashMap<>();
        //{0frontArmor, 1LeftArmor, 2RightArmor, 3BackArmor, 4HP, 5Damage, 6Penetration, 7ReloadTime, 8Speed, 9sizeRatio, 10turretPos}
        
        tanks.put("m4", new double[] {30, 10, 10, 5, 600, 160, 30, 4, 3.5, 0.55, 0.5}); // class1
        
        tanks.put("m4a3e8", new double[] {35, 40, 40, 10, 800, 180, 58, 4, 2.5, 0.45, 0.5}); //class2
        
        tanks.put("m26", new double[] {40, 40, 40, 10, 800, 180, 58, 4, 2.7, 0.45, 0.5}); //class3
        
        tanks.put("m26s", new double[] {68, 40, 40, 20, 800, 200, 63, 4.2, 2.8, 0.5, 0.5}); //class4
        
        //Germany Tank
        tanks.put("panzer4", new double[] {32, 12, 12, 5, 600, 160, 35, 4.2, 3.2, 0.5, 0.5});
        
        tanks.put("panther", new double[] {40, 20, 20, 10, 800, 160, 45, 4.5, 3, 0.4, 0.5});
        
        tanks.put("tiger", new double[] {57, 40, 40, 20, 800, 180, 50, 4.5, 2.8, 0.7, 0.5});
        
        tanks.put("tiger2", new double[] {70, 50, 50 ,30, 1000, 240, 64, 7, 3, 0.15, 0.5});
        
        //Britan Tank
        tanks.put("cromwell", new double[] {25, 8, 8, 2, 400, 145, 28, 4, 3, 0.6, 0.4});
        
        tanks.put("churchill", new double[] {50, 40, 40, 30, 800, 160, 3, 400, 2.5, 0.45, 0.5});
        
        tanks.put("comet", new double[] {40, 20, 20, 15, 800, 160, 42, 4, 3, 0.5, 0.4});
        
        tanks.put("firefly", new double[] {30, 10, 10, 5, 500, 230, 67, 6, 1.5, 0.4, 0.5});
        
        //Soviet Tank
        tanks.put("t34", new double[] {28, 10, 10, 5, 600, 150, 29, 3, 3.8, 0.45, 0.4});
        
        tanks.put("kv1", new double[] {45, 32, 32, 20, 800, 160, 42, 4.1, 2.8, 0.45, 0.4});
        
        tanks.put("is2", new double[] {58, 40, 40, 20, 800, 180, 55, 4, 2.5, 0.5, 0.4});
        
        tanks.put("is3", new double[] {72, 40, 40, 25, 800, 200, 65, 5, 2.8, 0.5, 0.4});
        
        
    }
    
    

    public double[] getTanks(String name) {
        return tanks.get(name);
    }

    public void setTanks(String name, double f, double l, double r, double b,
            double hp, double damage, double cal, 
            double reload, double speed, double width, double longed){
        tanks.put(name,new double[] {f, l, r, b, hp, damage, cal, reload, speed, width, longed});
        amount++;
    }    
}
