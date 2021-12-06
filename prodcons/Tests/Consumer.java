package prodcons.Tests;

import java.util.Random;

import prodcons.IProdConsBuffer;

public class Consumer extends Thread{
    private IProdConsBuffer buffer;
    private int consTime;
    private int id;
    public Consumer(IProdConsBuffer buff, int id, int time){
        buffer = buff;
        consTime = time;
        this.id = id;
        this.start();
    }
    public void run(){
        while(true){
            try {
                int to_wait = new Random().nextInt(2*consTime);
                System.out.println("C[" + id + "] reading...");
                buffer.get();
                System.out.println("C[" + id + "] processing...");
                sleep(to_wait);

            } catch (InterruptedException e) {
                System.out.println("C[" + id + "] ERROR");
                
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
}
