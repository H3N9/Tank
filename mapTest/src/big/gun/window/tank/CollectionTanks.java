
package big.gun.window.tank;

import java.util.HashMap;



public class CollectionTanks {
    private HashMap<String, double[]> tanks;
    public static double amount = 5;

    public static double getAmount() {
        return amount;
    }

    public CollectionTanks() {
        tanks = new HashMap<>();
        tanks.put("m4", new double[] {30, 10, 10, 5, 600, 160, 35, 400, 4, 50, 60});
        tanks.put("m26", new double[] {60, 40, 40, 15, 700, 200, 60, 10, 3, 50, 60});
        tanks.put("tiger2", new double[] {70, 50, 50 ,20, 1000, 240, 50, 700, 1, 80, 100});
        tanks.put("tiger", new double[] {40, 40, 40, 10, 800, 180, 58, 400, 3, 60, 70});
        tanks.put("firefly", new double[] {30, 10, 10, 5, 500, 200, 65, 600, 2, 50, 60});
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
