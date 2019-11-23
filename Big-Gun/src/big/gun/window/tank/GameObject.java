
package big.gun.window.tank;

import java.awt.geom.Rectangle2D;

public class GameObject{
    private double posX, posY;
    private double width, height;
    private double speedX, speedY;
    private double centerX, centerY;
    private double rotate;
    
    public GameObject(){
        posX = 0; posY = 0;
        width = 0; height = 0;
        speedX = 0; speedY = 0;
        centerX = 0; centerY = 0;
        rotate = 0;
    }
    
    public Rectangle2D getBounds(){
        return new Rectangle2D.Double(posX,posY,width,height);
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getRotate() {
        return rotate;
    }
    
    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
        
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setRotate(double rotate) {
        if(rotate < 0){
            rotate = 360+rotate;
        }else if(rotate >= 360){
            rotate = 360-rotate;
        }
        this.rotate = rotate;
    }
    
}
