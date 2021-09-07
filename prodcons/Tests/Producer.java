package prodcons.Tests;

import java.util.Random;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class Producer extends Thread{
    private IProdConsBuffer buffer;
    private int id;
    private int prodTime;
    private int minP;
    private int maxP;

    public Producer(IProdConsBuffer buff, int id, int time, int minP, int maxP){
        this.id = id;
        buffer = buff;
        prodTime = time;
        this.minP = minP;
        this.maxP = maxP;
        this.start();
    }

    public void run(){
        int to_produce = new Random().nextInt(maxP-minP+1) + minP;
        while(to_produce > 0 && !interrupted()){
            try {
                int to_wait = new Random().nextInt(2*prodTime);
                System.out.println("P[" + id + "] producing...");
                sleep(to_wait);
                System.out.println("P[" + id + "] submitting...");
                buffer.put(new Message());
    
            } catch (InterruptedException e) {
                return;
            }
            to_produce--;
        }
        
    }
}
