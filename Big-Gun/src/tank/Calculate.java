
package tank;

public class Calculate {
    public Calculate(){
    }
    
    public static double calculateRotateX(double x, double y, double centerX, double centerY, double r){
        return  (x-centerX)*Math.cos(Math.toRadians(r))-(y-centerY)*Math.sin(Math.toRadians(r)) + centerX;
    }
    public static double calculateRotateY(double x, double y, double centerX, double centerY, double r){
        return (x-centerX)*Math.sin(Math.toRadians(r))+(y-centerY)*Math.cos(Math.toRadians(r)) + centerY;
    }
    public static void calculateLine(){
        
    }
//x1 = (x-ตรงกลางรถ)*math.cos(math.radians(180))-y*math.sin(math.radians(180))
//y1 = x*math.sin(math.radians(180))+y*math.cos(math.radians(180))
}
