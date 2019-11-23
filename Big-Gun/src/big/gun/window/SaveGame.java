/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;


import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;


public class SaveGame {

    
    
    public static void Save(int money, String buy){
        Profile p = LoadSave();
        p.setMoney(p.getMoney()+money);
        if(!buy.equals("")){
            p.getSet().add(buy);
        }
        try{
            FileOutputStream fot = new FileOutputStream("Profile.dat");
            ObjectOutputStream oot = new ObjectOutputStream(fot);
            oot.writeObject(p);
            oot.close();
            fot.close();
        }
        catch(Exception x){}
        
    }
    
    public static Profile LoadSave(){
        File file = new File("Profile.dat");
        Profile p = new Profile();
        if(file.exists()){
            try{
                FileInputStream fin = new FileInputStream("Profile.dat");
                ObjectInputStream oin = new ObjectInputStream(fin);
                p = (Profile) oin.readObject();
                oin.close();
                fin.close();
                return p;
            }
            catch(Exception e){}
        }
        return p;
    }
    
    
    
}
