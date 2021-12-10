package prodcons.v7;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import prodcons.IProdConsBuffer;
import prodcons.v7.TaskMessage;

public class TaskConsumer extends Thread{
    private IProdConsBuffer buffer;
    private int last_id_read;

    private AtomicLong timestamp;
    private AtomicBoolean running;
    private TaskMessage task;

    public TaskConsumer(IProdConsBuffer buff){
        buffer = buff;
        last_id_read = -1;
        timestamp = new AtomicLong(System.currentTimeMillis());
        running = new AtomicBoolean(false);
        this.start();
    }

    public void run(){
        while(!Thread.interrupted()){
            try{
                TaskMessage t = (TaskMessage)(buffer.get_(last_id_read));
                running.set(true);        
                last_id_read = t.getID();
                t.run();
                timestamp.set(System.currentTimeMillis());
                running.set(false);

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    public boolean isFree(){
        return !running.get();
    }
    public boolean isAlive(long timeout){
        boolean r = running.get();
        long ts = timestamp.get();
        long now = System.currentTimeMillis();
        return (r || (ts + timeout) < now );
    }
}

