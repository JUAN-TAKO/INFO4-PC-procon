package prodcons;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private static AtomicInteger gid = new AtomicInteger(0);
    private final int id;
    private AtomicInteger count;
    
    public Message(){
        id = gid.get();
        this.count = new AtomicInteger(1);
        gid.incrementAndGet();
    }
    public Message(Message m){
        this.id = m.id;
        this.count = null;
    }
    public Message(int count){
        id = gid.get();
        this.count = new AtomicInteger(count);
        gid.incrementAndGet();
    }

    public int consume(){
        return count.decrementAndGet();
    }

    public int getID(){
        return id;
    }

    public static int getGID(){
        return gid.get();
    }
}