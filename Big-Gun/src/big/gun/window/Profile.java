/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;

import java.util.HashSet;

/**
 *
 * @author h3n9
 */
public class Profile {
    private long money;
    private HashSet<String> set;
    
    public Profile(){
        money = 0l;
        set = new HashSet<>();
        set.add("m4");
        set.add("t34");
        set.add("cromwell");
        set.add("panzer4");
    }
    
    

    public long getMoney() {
        return money;
    }

    public HashSet<String> getSet() {
        return set;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setSet(HashSet<String> set) {
        this.set = set;
    }
    
    
    
    
}
