/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
    
public class MonitorSerijska implements Monitor {
    List<String> bafer;

    public MonitorSerijska() {
        bafer=new LinkedList<>();
    }
    
    @Override
    public synchronized void put(String line){
        byte[] poruka=line.getBytes();
        System.out.print("Added: ");
        for (int j=0;j<8;j++)
            System.out.print(String.format("%02x",poruka[j]));
        System.out.println("");
            bafer.add(line);
            //if(bafer.size()==1)
                notifyAll();
           
       }

    @Override
    public synchronized String get(){

            while(bafer.isEmpty()){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MonitorSerijska.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            byte[] poruka=bafer.get(0).getBytes();
        System.out.print(Thread.currentThread().getName()+" removed: ");
        for (int j=0;j<8;j++)
             System.out.print(String.format("%02x",poruka[j]));
        System.out.println("");


            return bafer.remove(0);
    
    }
    
    
    
}
