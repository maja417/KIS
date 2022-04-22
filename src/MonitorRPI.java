/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class MonitorRPI implements Monitor{
    List<byte[]> bafer;

    public MonitorRPI() {
        bafer=new LinkedList<>();
    }
    
    @Override
    public synchronized void put(byte[] line){
        
            bafer.add(line);
            //if(bafer.size()==1)
            notify();
           
       }
    @Override
    public synchronized byte[] get(){
            while(bafer.isEmpty()){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MonitorRPI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return bafer.remove(0);
    } 
}
