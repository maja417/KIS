/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
/**
 *
 * @author Marija
 */
public class DApp {
    static final int port = 6677;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int kapacitet=50;
        MonitorSerijska monitorS=new MonitorSerijska(kapacitet);
        
       // MonitorRPI monitorrpi= new MonitorRPI();
             
          WebAppCommunication wa=new WebAppCommunication();
        Serijska s=new Serijska(monitorS);
        s.start();
        
       // RPIComm rpi=new RPIComm(port,monitorrpi);
      //  rpi.start();
        
       // ConsumerRPI korisnikrpi=new ConsumerRPI(monitorrpi);
      //  korisnikrpi.start();

        for(int k=0;k<5;k++) {
             new ConsumerSerijski(monitorS,k,wa).start();
        }
        
    }
    
    
}
