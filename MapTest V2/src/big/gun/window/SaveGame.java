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

    
    
public static void Save(int money){
        int p = money;
        
        try{
            try (FileOutputStream fot = new FileOutputStream("save.dat")) {
                DataOutputStream oot = new DataOutputStream(fot);
                oot.write(p);
                oot.close();
                fot.close();
            }
        }
        catch(IOException x){}
        
    }
    
    public static int LoadSave(){
        int p = 0;
        File file = new File("save.dat");
        if(file.exists()){
            try{
                FileInputStream fin = new FileInputStream("save.dat");
                DataInputStream oin = new DataInputStream(fin);
                p = oin.read();
                oin.close();
                fin.close();
                return p;
            }
            catch(IOException e){}
        }
        else{
            System.out.println("new");
            return p;
            
        }
        System.out.println("null");
        return p;
    }
    
    
    
}
