package prodcons.v7;

import java.util.concurrent.Semaphore;

import prodcons.IProdConsBuffer;
import prodcons.Message;
import prodcons.MessageQueue;

public class ProdConsBuffer implements IProdConsBuffer{
    private MessageQueue queue;
    private int total;
    private int last_multi_id;

    public ProdConsBuffer(int qsize){
        queue = new MessageQueue(qsize);
        total = 0;
        last_multi_id = -1;
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
    public Message get() throws InterruptedException{
        return get_(-1);
    }

    @Override
    public Message get_(int last_id_read) throws InterruptedException {
        Message m = null;
        synchronized(this){            
            try {
                while(   queue.length() == 0 
                      || (m = queue.peek()).getID() == last_id_read
                      || (last_multi_id >= 0 && last_multi_id != m.getID())){
                    

                    wait();
                }

                if(m.consume() == 0){
                    queue.get();
                    notifyAll();
                }
                m = new Message(m);

            } catch (IndexOutOfBoundsException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
    public Message[] get(int k) throws InterruptedException {
        return get_(-1, k);
    }

    @Override
    public synchronized Message[] get_(int last_id_read, int k) throws InterruptedException {
        Message[] msg = new Message[k];
    
        for(int i=0; i < k; i++){
            try {
                Message m;
                while(queue.length() == 0 || (m = queue.peek()).getID() == last_id_read){
                    wait();
                }

                if(m.consume() == 0){
                    queue.get();
                    notifyAll();
                }
                last_id_read = m.getID();
                last_multi_id = last_id_read;
                msg[i] = new Message(m);
            } catch (IndexOutOfBoundsException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        last_multi_id = -1;
        return msg;
    }
    
}
