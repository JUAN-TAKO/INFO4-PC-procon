package prodcons.v1;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class ProdConsBuffer implements IProdConsBuffer{
    private MessageQueue queue;
    
    private int total;

    public ProdConsBuffer(int qsize){
        queue = new Message[qsize];
        total = 0;
    }
    @Override
    public synchronized void put(Message m) throws InterruptedException {
        while(m.available() == 0)
            wait();
    
        queue.add(m);
        total++;
        notifyAll();
    }

    @Override
    public synchronized Message get() throws InterruptedException {
        while(queue.size() == 0)
            wait();

        Message m = queue.get();
        notifyAll();
        
        return m;
    }

    @Override
    public synchronized int nmsg() {
        return queue.size();
    }

    @Override
    public synchronized int totmsg() {
        return total;
    }
    
}
