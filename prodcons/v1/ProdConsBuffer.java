package prodcons.v1;

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
        while(queue.size() == 0)
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
        return queue.size();
    }

    @Override
    public synchronized int totmsg() {
        return total;
    }
    
}
