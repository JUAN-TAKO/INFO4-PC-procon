package prodcons.v7;

import java.util.concurrent.atomic.AtomicInteger;

import prodcons.Message;

public class TaskMessage extends Message{
    private Runnable task;
    
    public TaskMessage(){
        super();
        task = null;
    }
    public TaskMessage(Message m){
        super(m);
        this.task = ((TaskMessage)m).task;
    }
    public TaskMessage(int count){
        super(count);
        this.task = null;
    }
    public TaskMessage(int count, Runnable task){
        super(count);
        this.task = task;
    }
    public void run(){
        task.run();
    }
}