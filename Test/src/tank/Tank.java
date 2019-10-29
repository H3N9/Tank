/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

public class Tank{
    private int fontHull;
    private int leftHull;
    private int rightHull;
    private int backHull;
    private int reload;
    private int cal;
    private int hp;
    private int speed;
    private int damage;
    private int widthTank;
    private int longTank;
    private Collection tanks;

    public Tank(String name) {
        Collection tanks = new Collection();
        this.fontHull = tanks.getTanks(name)[0];
        this.leftHull = tanks.getTanks(name)[1];
        this.rightHull = tanks.getTanks(name)[2];
        this.backHull = tanks.getTanks(name)[3];
        this.hp = tanks.getTanks(name)[4];
        this.damage = tanks.getTanks(name)[5];
        this.cal = tanks.getTanks(name)[6];
        this.reload = tanks.getTanks(name)[7];
        this.speed = tanks.getTanks(name)[8];
        this.widthTank = tanks.getTanks(name)[9];
        this.longTank = tanks.getTanks(name)[10];
    }

    public void setFontHull(int fontHull) {
        this.fontHull = fontHull;
    }

    public void setLeftHull(int leftHull) {
        this.leftHull = leftHull;
    }

    public void setRightHull(int rightHull) {
        this.rightHull = rightHull;
    }

    public void setBackHull(int backHull) {
        this.backHull = backHull;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setWidthTank(int widthTank) {
        this.widthTank = widthTank;
    }

    public void setLongTank(int longTank) {
        this.longTank = longTank;
    }

    public void setTanks(Collection tanks) {
        this.tanks = tanks;
    }

    public int getFontHull() {
        return fontHull;
    }

    public int getLeftHull() {
        return leftHull;
    }

    public int getRightHull() {
        return rightHull;
    }

    public int getBackHull() {
        return backHull;
    }

    public int getReload() {
        return reload;
    }

    public int getCal() {
        return cal;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getWidthTank() {
        return widthTank;
    }

    public int getLongTank() {
        return longTank;
    }

    public Collection getTanks() {
        return tanks;
    }
   
    
    
    
    
    
    
    
    
}
