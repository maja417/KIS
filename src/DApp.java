/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import okhttp3.*;

import java.io.IOException;
/**
 *
 * @author Marija
 */
public class DApp {
    static final int port = 6677;


    public static void main(String[] args) throws IOException {
        int kapacitet = 50;

        MonitorSerijska monitorS = new MonitorSerijska(kapacitet);

         MonitorRPI monitorrpi= new MonitorRPI();

        WebAppCommunication wa = new WebAppCommunication();
      // for(int i=1;i<10;i++) {
         //   wa.sendAndroid("caos"+i);

            // wa.sendAndroid("caos");
         ////   wa.sendAndroid("caos");
          //  wa.sendAndroid("caos");
     //   }




        Serijska s = new Serijska(monitorS,0);
        s.start();

     //   Serijska s1 = new Serijska(monitorS,1);
       // s1.start();


         RPIComm rpi=new RPIComm(port,monitorrpi);
         rpi.start();

         ConsumerRPI korisnikrpi=new ConsumerRPI(monitorrpi,wa);
          korisnikrpi.start();

        for (int k = 0; k < 3; k++) {
            new ConsumerSerijski(monitorS, k, wa).start();
        }
    }



}
    
