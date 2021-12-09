package prodcons.Tests;

import java.util.Random;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class Consumer extends Thread{
    private IProdConsBuffer buffer;
    private int consTime;
    private int id;
    private int nb;
    private int last_id_read;

    public Consumer(IProdConsBuffer buff, int id, int time, int nb){
        buffer = buff;
        consTime = time;
        this.id = id;
        this.nb = nb;
        last_id_read = -1;
        this.start();
    }
    public void run(){
        while(!Thread.interrupted()){
            try {
                int to_wait = new Random().nextInt(2*consTime);
                System.out.println("C[" + id + "] reading...");
                
                if(nb == 1){
                    Message m = buffer.get_(last_id_read);
                    last_id_read = m.getID();
                    System.out.println("C[" + id + "] Message: " + m.getID());
                }
                else{
                    Message[] m = buffer.get_(last_id_read, nb);
                    last_id_read = m[m.length-1].getID();
                    System.out.print("C[" + id + "] Messages: ");
                    for(int i=0; i < m.length; i++){
                        System.out.print("" + m[i].getID() + ", ");
                    }
                    System.out.println("");
                }
                
                System.out.println("C[" + id + "] processing...");
                sleep(to_wait);

            } catch (InterruptedException e) {
                return;
            }
            
        }
    }
}
