/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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

    public void AndroidHTTP(String poruka)
    {





    }

     @Override   
    public void obradi(byte[] b){
    try {

        int i;
        byte destinacija;
        byte[] poruka = new byte[b[3]];
        byte id;
        byte pnam;

        byte[] nizX=new byte[4];
        byte[] nizY=new byte[4];

        float x;
        float y;

        String Poruka;

        destinacija = b[0];
        id = b[1];
        pnam = b[2];
        int h;
        for (i = 4, h = 0; i < b.length; i++, h++) {
            poruka[h] = b[i];
        }
<<<<<<< HEAD

        if(id==0x02)
        {

            Poruka=poruka.toString();
            System.out.println("Poruka:  "+Poruka);
        }

        
        if(id==0x01 && pnam==0x01)
        {
            for(i=0;i<4;i++)
                nizX[i]=poruka[i];

            for(i=4;i<8;i++)
                nizY[i-4]=poruka[i];

            x= ByteBuffer.wrap(nizX).order(ByteOrder.LITTLE_ENDIAN).getFloat();
            y= ByteBuffer.wrap(nizY).order(ByteOrder.LITTLE_ENDIAN).getFloat();

            System.out.println("X: "+x);
            System.out.println("Y: "+y);
        }
=======
        Poruka message=new Poruka(destinacija,id,pnam,poruka);
>>>>>>> 240342cb595f8a86facd5993e0a5784e2ee52750

      //TODO: salji po adresi destinacija poruku odnosno po protokolu http na odg port web aplikacije




    }
    catch(Exception e){
        System.out.println("odbacena poruka :(");
    }
<<<<<<< HEAD
        

        
        
        
=======
>>>>>>> 240342cb595f8a86facd5993e0a5784e2ee52750
    }
    
}
