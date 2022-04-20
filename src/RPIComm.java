/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.corba.se.spi.activation.Server;
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

    public RPIComm( int socketNum, MonitorRPI m) throws IOException {
         s = new ServerSocket(socketNum);
         monitor=m;
    }

    @Override
    public void run() {
        try {
            Socket s1=s.accept();
            System.out.println("Start communication...");
//////////////////////////////////////// prvi nacin /////////////////////////////////////////////				
            // receive v2 and print
            try (DataInputStream dis = new DataInputStream(s1.getInputStream())) {
                // receive v2 and print
                int buflen = dis.readByte();
                byte[] buf = new byte[buflen*8];
                int ix = 0;
                while(ix < buf.length) {
                    ix += dis.read(buf, ix, buf.length - ix);
                }
                monitor.put(new String(buf, "UTF-8"));
              
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
 
    
    
}
