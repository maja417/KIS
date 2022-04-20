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
    List<byte[]> bafer;

    public MonitorSerijska() {
        bafer=new LinkedList<>();
    }
    
    @Override
    public synchronized void put(String line){
        
            bafer.add(Base64.decode(line));
            //if(bafer.size()==1)
                notifyAll();
           
       }

    @Override
    public synchronized byte[] get(){
            System.out.print("Consumerpj");
            while(bafer.isEmpty()){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MonitorSerijska.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(Arrays.toString(bafer.get(0)));
            return bafer.remove(0);
    
    }
    
    
    
}
