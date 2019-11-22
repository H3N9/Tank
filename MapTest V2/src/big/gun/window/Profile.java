/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;

import java.util.HashSet;

/**
 *
 * @author USER
 */
public class Profile {
    
    private int money;
    private HashSet<String> set;
    
    public Profile(){
        money = 0;
        set = new HashSet();
        set.add("m4");
        set.add("t34");
        set.add("panzer4");
        set.add("cromwell");
    }
    
    public void setMoney(int m){
        money = m;
    }
    
    public void setSet(String name){
        set.add(name);
    }
    
    public int getMoney(){
        return money;
    }
    
    public HashSet getSet(){
        return set;
    }
    
}
