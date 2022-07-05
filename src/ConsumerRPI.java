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
    WebAppCommunication wa;

    public ConsumerRPI(MonitorRPI monitor,WebAppCommunication wa) {
        this.monitor = monitor;
        this.wa=wa;
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
           wa.sendRPI(poruka.toString());

         }
         catch(Exception e){
             System.out.println("odbacena poruka :(");
         }
    }
    
    
}
