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
    public void obradi(String b){
        //TODO smisli parsiranje ovog niza
    }
    
    
}
