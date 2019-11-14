package Calculate;

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
}
