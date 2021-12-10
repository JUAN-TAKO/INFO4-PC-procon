package prodcons.v7;

import java.util.concurrent.atomic.AtomicInteger;
import prodcons.Message;

public class TaskMessage extends Message{
    private static AtomicInteger gid = new AtomicInteger(0);
    private final int id;
    private AtomicInteger count;
    private Runnable task;
    
    public TaskMessage(){
        super();
        task = null;
    }
    public TaskMessage(Message m){
        super(m);
        this.task = m.task;
    }
    public TaskMessage(int count){
        super(count);
        this.task = null;
    }
    public TaskMessage(int count, Runnable task){
        super(count);
        this.task = task;
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
    public void run(){
        task.run();
    }
}