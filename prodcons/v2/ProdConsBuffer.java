package prodcons.v2;

import prodcons.IProdConsBuffer;
import prodcons.Message;
import prodcons.MessageQueue;

public class ProdConsBuffer implements IProdConsBuffer{
    private MessageQueue queue;
    
    private int total;

    public ProdConsBuffer(int qsize){
        queue = new MessageQueue(qsize);
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
    public synchronized Message get() throws InterruptedException {
        while(queue.length() == 0)
            wait();

        Message m = null;
        try {
            m = queue.get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        notifyAll();
        
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
        System.out.println("Method not implemented");
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Message[] get(int k) throws InterruptedException {
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
