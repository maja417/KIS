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
    WebAppCommunication webAppCommunication;

    public ConsumerSerijski(MonitorSerijska monitor, int i,WebAppCommunication webAppCommunication) {
        super("Consumer"+i);
        this.monitor = monitor;
        this.webAppCommunication=webAppCommunication;
    }


    @Override
    public void run() {

        while(true) {
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

        byte[] nizX=new byte[4];
        byte[] nizY=new byte[4];

        byte[] temperatura= new byte[3];

        float x;
        float y;

        String PorukaAndroid, PorukaRPi;

        destinacija = b[0];
        id = b[1];
        pnam = b[2];
        int h;
        for (i = 4, h = 0; i < b.length; i++, h++) {
            poruka[h] = b[i];
        }

        // Android
        if(id==0x02)
        {

            PorukaAndroid=poruka.toString();
            System.out.println("Poruka:  "+PorukaAndroid);
            webAppCommunication.sendAndroid(PorukaAndroid);

        }
        // RPi
        if(id==0x03)
        {
            PorukaRPi=poruka.toString();
            System.out.println("Poruka:  "+PorukaRPi);
            webAppCommunication.sendRPI(PorukaRPi);



        }
        // koordinate
        if(destinacija==0x30)
        {
            for(i=0;i<4;i++)
                nizX[i]=poruka[i];

            for(i=4;i<8;i++)
                nizY[i-4]=poruka[i];

            x= ByteBuffer.wrap(nizX).order(ByteOrder.BIG_ENDIAN).getFloat();
            y= ByteBuffer.wrap(nizY).order(ByteOrder.BIG_ENDIAN).getFloat();

            System.out.println("X: "+x);
            System.out.println("Y: "+y);
            webAppCommunication.sendGPS(x, y);

        }

        // temperatura
        if(destinacija==0x25)
        {
            for(i=0;i<3;i++)
            {
                temperatura[i]=poruka[i];
            }
            System.out.println("Temperatura: "+temperatura.toString());
        }


    }
    catch(Exception e){
        System.out.println("odbacena poruka :(");
    }

    }
    
}
