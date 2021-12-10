package prodcons.v3;

import java.util.concurrent.Semaphore;

import prodcons.IProdConsBuffer;
import prodcons.Message;
import prodcons.MessageQueue;

public class ProdConsBuffer implements IProdConsBuffer{
    private MessageQueue queue;
    private Semaphore semP, semG;
    private int total;

    public ProdConsBuffer(int qsize){
        queue = new MessageQueue(qsize);
        semP = new Semaphore(qsize, true);
        semG = new Semaphore(qsize, true);
        total = 0;
    }
    @Override
    public void put(Message m) throws InterruptedException {
        semP.acquire();
        synchronized(this){
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
        semP.release();
    }

    @Override
    public Message get() throws InterruptedException {
        semG.acquire();
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
        semG.release();
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
    public Message get_(int l) throws InterruptedException {
        return get();
    }
    
    @Override
    public Message[] get(int k) throws InterruptedException {
        if(k==1){
            Message[] m = new Message[1];
            m[0] = get();
            return m;
        }
        System.out.println("Method not implemented");
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Message[] get_(int l, int k) throws InterruptedException {
        return get(k);
    }
}
