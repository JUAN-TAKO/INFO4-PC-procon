package prodcons.v1;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer{
    private Message queue[];
    
    private int total;
    private int nb;

    public ProdConsBuffer(int qsize){
        queue = new Message[qsize];
        total = 0;
        nb = 0;
    }
    @Override
    public synchronized void put(Message m) throws InterruptedException {
        
    }

    @Override
    public synchronized Message get() throws InterruptedException {
        
        return null;
    }

    @Override
    public synchronized int nmsg() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public synchronized int totmsg() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
