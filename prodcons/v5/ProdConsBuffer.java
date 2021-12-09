package prodcons.v6;

import java.util.concurrent.Semaphore;

import prodcons.IProdConsBuffer;
import prodcons.Message;
import prodcons.MessageQueue;

public class ProdConsBuffer implements IProdConsBuffer{
    private MessageQueue queue;
    private int total;
    private Semaphore sem;

    public ProdConsBuffer(int qsize){
        queue = new MessageQueue(qsize);
        sem = new Semaphore(1);
        total = 0;
    }
    @Override
    public synchronized void put(Message m) throws InterruptedException {
        while(queue.available() == 0)
            wait();

        try {
            queue.add(m);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        total++;
        notifyAll();
    }

    @Override
    public Message get() throws InterruptedException {
        sem.acquire();
        Message m = null;
        synchronized(this){
            while(queue.length() == 0)
                wait();

            
            try {
                m = queue.get();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            notifyAll();
        }
        sem.release();
        return m;
    }

    @Override
    public synchronized int nmsg() {
        return queue.length();
    }

    @Override
    public synchronized int totmsg() {
        return total;
    }

    public synchronized void finish() throws InterruptedException {
        while(queue.length() != 0)
            wait();
    }

    @Override
    public synchronized Message[] get(int k) throws InterruptedException {
        sem.acquire();
        Message[] msg = new Message[k];
        synchronized(this){
            
            for(int i=0; i < k; i++){
                while(queue.length() == 0)
                    wait();

                try {
                    msg[i] = queue.get();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                notifyAll();
            }
        }
        sem.release();
        return msg;
    }

    @Override
    public Message get_(int l) throws InterruptedException {
        System.out.println("Method not implemented");
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Message[] get_(int l, int k) throws InterruptedException {
        System.out.println("Method not implemented");
        // TODO Auto-generated method stub
        return null;
    }
    
}
