package big.gun.window.tank;

public class Calculate {
    public Calculate(){}
    
    public static double calculateRotateX(double px, double py, double cx, double cy, double r){
        return  (px-cx)*Math.cos(Math.toRadians(r))-(py-cy)*Math.sin(Math.toRadians(r)) + cx;
    }
    public static double calculateRotateY(double px, double py, double cx, double cy, double r){
        return (px-cx)*Math.sin(Math.toRadians(r))+(py-cy)*Math.cos(Math.toRadians(r)) + cy;
    }
    public static double calculateMoveX(double r, double s){
        return Math.sin(Math.toRadians(r))*s;
    }
    public static double calculateMoveY(double r, double s){
        return Math.cos(Math.toRadians(r))*s;
    }
    public static double calculateSlope(double x1, double y1, double x2, double y2){
        return (y2-y1)/(x2-x1);
    }
    public static int randomNumber(int min, int max){
        int range = (max-min)+1;
        return (int)(Math.random() * range) + min;
    }
    public static double calculateArcTan(double x1, double y1, double x2, double y2){
        return Math.atan(Math.abs(y2-y1)/Math.abs(x2-x1));
    }
    public static double calculateArcSin(double x1, double y1, double x2, double y2){
        return Math.asin((y2-y1)/((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));
    }
    public static double calculateArcCos(double x1, double y1, double x2, double y2){
        return Math.acos((x2-x1)/((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));
    }
}
