/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;

/**
 *
 * @author Marija
 */
public class Serijska extends Thread {

    private SerialPort serial;
    private InputStream in;
    private MonitorSerijska m;
    int id;

    public Serijska(MonitorSerijska mon,int id) {
        SerialPort[] niz=SerialPort.getCommPorts();
        this.id=id;
        System.out.println(niz[0]);

        serial = niz[id];
        
        serial.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        in = serial.getInputStream();
        m = mon;

    }

    @Override
    public void run() {
        {
            if (serial.openPort()) {
               int start = 0;
                int end = 0;
                int len=-1;
                int i=0;
                boolean begin = true;
                boolean datalen=false;
                boolean kraj=false;
                byte[] buffer = new byte[1];
                byte[] poruka=null;

           while(true){   
               start=0;
               /**
                *Ovaj if ispod je ako se iskljuci predajnik sa porta
                *i ponovo ukljuci da se aplikacija nastavi
                */
               if(!serial.isOpen()){
                   while(!serial.openPort());
               }
                while (serial.bytesAvailable() > 0) {
                    serial.readBytes(buffer, 1);

                   if (buffer[0] == 0x01 && begin) {
                        start++;
                        if (start == 3) {
                            start = 0;
                            begin = false;
                            datalen=true;
                          
                        }
                    }
                   else{
                            if(datalen){
                                len=buffer[0];
                                poruka=new byte[len];
                                datalen=false;
                            }
                            else{
                                if(i<len){
                                    poruka[i]=buffer[0];
                                    i++;
                                }
                                else{
                                    if(i==len)
                                        kraj=true;

                                    if (buffer[0] == 0x0B && kraj) {
                                        end++;
                                    }
                                    if (buffer[0] == 0x13 && end == 1 && kraj) {
                                        end = 0;
                                        i=0;
                                        m.put(poruka);
                                        kraj=false;
                                        len=-1;
                                        begin = true;
                                    }
                                }
                            }
                   }
                }
                poruka=null;
           }
        
        }

    }
    }
}


