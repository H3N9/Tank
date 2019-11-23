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
        Profile profile = LoadSave();
        profile.setMoney(profile.getMoney()+money);
        if(!buy.equals("")){
            profile.getSet().add(buy);
        }
        try{
            FileOutputStream fot = new FileOutputStream("save.dat");
            ObjectOutputStream oot = new ObjectOutputStream(fot);
            oot.writeObject(profile);
            oot.close();
            fot.close();
            
        }
        catch(IOException x){}
        
    }
    
    public static Profile LoadSave(){
        Profile profile = new Profile();
        File file = new File("save.dat");
        if(file.exists()){
            try{
                FileInputStream fin = new FileInputStream("save.dat");
                ObjectInputStream oin = new ObjectInputStream(fin);
                profile = (Profile)oin.readObject();
                oin.close();
                fin.close();
                return profile;
            }
            catch(IOException | ClassNotFoundException e){}
        }
        else{
            return profile;
            
        }
        return profile;
    }
    
    
    
}
