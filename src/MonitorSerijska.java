/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xerces.internal.xs.datatypes.ByteList;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

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
    public synchronized void put(byte[] poruka){

        /******************ISPIS*****************************/

                    System.out.print("Added: ");
                    for (int j=0;j<poruka.length;j++)
                        System.out.print(String.format("%02x",poruka[j]));
                    System.out.println("");
        /******************ISPIS*****************************/
            bafer.add(poruka);
                notifyAll();
           
       }

    @Override
    public synchronized byte[] get(){

            while(bafer.isEmpty()){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MonitorSerijska.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        /******************ISPIS*****************************/
        byte[] poruka=bafer.get(0);
        System.out.print(Thread.currentThread().getName()+" removed: ");
        for (int j=0;j<poruka.length;j++)
             System.out.print(String.format("%02x",poruka[j]));
        System.out.println("");
        /******************ISPIS*****************************/


        return bafer.remove(0);
    
    }
    
    
    
}
