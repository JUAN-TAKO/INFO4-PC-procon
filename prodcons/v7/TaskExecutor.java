package prodcons.v7;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import prodcons.v7.ProdConsBuffer;
import prodcons.v7.TaskMessage;
import prodcons.v7.TaskConsumer;

public class TaskExecutor extends Thread{
    private ProdConsBuffer buff;
    private LinkedList<TaskConsumer> workers;
    private long timeout;
    

    public TaskExecutor(int qsize, long timeout){
        buff = new ProdConsBuffer(qsize);
        workers = new LinkedList<TaskConsumer>();
        this.timeout = timeout;
        this.start();
    }

    private int update(){
        ListIterator<TaskConsumer> iter = workers.listIterator();
        int available = 0;
        while(iter.hasNext()){
            if(!iter.next().isAlive(timeout)){
                iter.remove();
                System.out.println("consumer removed (" + workers.size() + "w)");
            }
            else if(iter.next().isFree()){
                available++;
            }
        }
        return available;
    }
    public void run(){
        synchronized(this){
            while(!Thread.interrupted()){
                int available = update();
                if(available == 0){
                    workers.add(new TaskConsumer(buff));
                    System.out.println("consumer added (" + workers.size() + "w)");
                }
                try{
                    wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
            }
        }
    }
    
    public synchronized void put(TaskMessage t){
        try{
            
            buff.put(t);
            notifyAll();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}