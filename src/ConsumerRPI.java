/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marija
 */
public class ConsumerRPI extends Thread implements Consumeri{
    MonitorRPI monitor;

    public ConsumerRPI(MonitorRPI monitor) {
        this.monitor = monitor;
    }


    @Override
    public void run() {
        while(true){
            obradi(monitor.get());
        }
    }
    
     @Override  
    public void obradi(byte[] b){
         try {
             int i;
             byte destinacija;
             byte[] poruka = new byte[b[3]];
             byte id;
             byte pnam;
             destinacija = b[0];
             id = b[1];
             pnam = b[2];
             int h;
             for (i = 4, h = 0; i < b.length; i++, h++) {
                 poruka[h] = b[i];
             }
             //TODO: salji po adresi destinacija poruku odnosno po protokolu http na odg port web aplikacije

         }
         catch(Exception e){
             System.out.println("odbacena poruka :(");
         }
    }
    
    
}
