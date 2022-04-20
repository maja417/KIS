/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.java2d.cmm.ColorTransform.In;

/**
 *
 * @author Marija
 */
public class Serijska extends Thread {

    private SerialPort serial;
    private InputStream in;
    private MonitorSerijska m;

    public Serijska(MonitorSerijska mon) {
        SerialPort[] niz=SerialPort.getCommPorts();
        
      //  System.out.println(Arrays.toString(niz));
     //   Scanner sc=new Scanner(System.in);
     //  int izbor=sc.nextInt();
        serial = niz[0];
        
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
               // byte[] poruka=new byte[20];
                
                int d=0;
                
                int fl=0;
           while(true){   
               //System.out.println("");
                while (serial.bytesAvailable() > 0) {
                    serial.readBytes(buffer, 1);
                  //  System.out.print(String.format("%02x",buffer[0]));
                   // poruka[d]=buffer[0];
                   //// fl=1;
                   // d++;
                   if (buffer[0] == 0x01 && begin) {
                        start++;
                        if (start == 3) {
                            start = 0;
                            begin = false;
                            datalen=true;
                          
                        }
                    }else{
                    if(datalen){
                        len=buffer[0];
                        fl=1;
                        poruka=new byte[len];
                        datalen=false;
                    }
                    else{
                    if(i<len){
                        poruka[i]=buffer[0];
                        i++;
                    }else{
                    if(i==len) 
                    { kraj=true;}
                    
                    if (buffer[0] == 0x0B && kraj) {
                        end++;
                    }
                    if (buffer[0] == 0x13 && end == 1 && kraj) {
                        end = 0;
                        i=0;
                        m.put(new String(poruka,0,len));
                        kraj=false;
                        len=-1;
                        begin = true;
                        
                    }
                 }}
                   }
                }
                if(poruka!=null)
                {//String p=new String(poruka,0,len);
                for (int j=0;j<8;j++)
                    System.out.print(String.format("%02x",poruka[j]));
                    System.out.println("");
                }    // len=-1;
                
                poruka=null;
                   // System.out.print(new String(poruka,0,len));    
           }
        
        }

    }
    }
}


