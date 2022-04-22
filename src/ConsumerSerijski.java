/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Marija
 */
public class ConsumerSerijski extends Thread implements Consumeri{
MonitorSerijska monitor;

    public ConsumerSerijski(MonitorSerijska monitor, int i) {
        super("Consumer"+i);
        this.monitor = monitor;
    }


    @Override
    public void run() {

        while(true) {
           obradi(monitor.get());
        }
    }
    
     @Override   
    public void obradi(String b){
       //  System.out.println(currentThread().getName());
                  /*      int i;
       
        byte[] dest= new byte[3];
        byte[] poruka=null;
        byte id;
        byte pnam;
        for(i=0;i<b.length;i++)
        {
            dest[i]=b[i];
            if(i==3)
            {  
                id=b[i];
            }
            
            if(i==4)
            {  
                pnam=b[i];
            }
            if(i>4)
            poruka[i]=b[i];
        }
        
        
        
      
         System.out.println(new String(poruka,0,i));
        
        */
        
        
        
        
    }
    
}
