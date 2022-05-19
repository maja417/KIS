/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class RPIComm extends Thread {
   final private ServerSocket s;
    final private MonitorRPI monitor;

    public RPIComm(int socketNum, MonitorRPI m) throws IOException {
         s = new ServerSocket(socketNum);
         monitor=m;
    }

    @Override
    public void run() {
        byte bajt;
        int start = 0;
        int end = 0;
        int len=-1;
        int i=0;
        boolean begin = true;
        boolean datalen=false;
        boolean kraj=false;
        byte[] poruka=null;
        try {
            Socket s1=s.accept();
            System.out.println("Start communication...");
//////////////////////////////////////// prvi nacin /////////////////////////////////////////////				
            // receive v2 and print
            try (DataInputStream dis = new DataInputStream(s1.getInputStream())) {
                // receive v2 and print
                while (true) {
                    while (dis.available() != 0) {
                        bajt = dis.readByte();
                        if (bajt == 0x01 && begin) {
                            start++;
                            if (start == 3) {
                                start = 0;
                                begin = false;
                                datalen=true;

                            }
                        }
                        else{
                            if(datalen){
                                len=bajt;
                                poruka=new byte[len];
                                datalen=false;
                            }
                            else {
                                if (i < len) {
                                    poruka[i] = bajt;
                                    i++;
                                } else {
                                    if (i == len)
                                        kraj = true;

                                    if (bajt == 0x0B && kraj) {
                                        end++;
                                    }
                                    if (bajt == 0x13 && end == 1 && kraj) {
                                        end = 0;
                                        i = 0;
                                        monitor.put(poruka);
                                        kraj = false;
                                        len = -1;
                                        begin = true;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
 
    
    
}
