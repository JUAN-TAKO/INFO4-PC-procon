package prodcons.Tests;

import java.util.Random;

import prodcons.IProdConsBuffer;

public class Consumer extends Thread{
    private IProdConsBuffer buffer;
    private int consTime;
    private int id;
    private int nb;
    public Consumer(IProdConsBuffer buff, int id, int time, int nb){
        buffer = buff;
        consTime = time;
        this.id = id;
        this.nb = nb;
        this.start();
    }
    public void run(){
        System.out.println("nb " + nb);
        while(!Thread.interrupted()){
            try {
                int to_wait = new Random().nextInt(2*consTime);
                System.out.println("C[" + id + "] reading...");
                if(nb == 1){
                    buffer.get();
                }
                else{
                    buffer.get(nb);
                }
                
                System.out.println("C[" + id + "] processing...");
                sleep(to_wait);

            } catch (InterruptedException e) {
                return;
            }
            
        }
    }
}
