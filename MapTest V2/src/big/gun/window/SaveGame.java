/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.gun.window;


import java.io.*;


public class SaveGame {
    public static Profile profile;
    
    
    public static void Save(int money, String buy){
        profile = LoadSave();
        profile.setMoney(profile.getMoney()+money);
        if(!buy.equals(""))
            profile.setSet(buy);
        try{
            FileOutputStream fot = new FileOutputStream("Profile.dat");
            ObjectOutput oot = new ObjectOutputStream(fot);
            oot.writeObject(profile);
            oot.close();
            fot.close();
        }
        catch(Exception x){}
        
    }
    
    public static Profile LoadSave(){
        File file = new File("Profile.dat");
        if(file.exists()){
            try{
                FileInputStream fin = new FileInputStream("Profile.dat");
                ObjectInputStream oin = new ObjectInputStream(fin);
                profile = (Profile) oin.readObject();
                oin.close();
                fin.close();
                return profile;
            }
            catch(Exception e){}
        }
        profile = new Profile();
        return profile;
    }
    
    
    
}
