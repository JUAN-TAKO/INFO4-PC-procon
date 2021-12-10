package prodcons.v7;

import prodcons.v7.TaskExecutor;
import prodcons.v7.TaskMessage;
import prodcons.v7.DummyTask;

public class TestExecutor {
    private static TaskExecutor executor = null;
    private static void put(long t, String m){
        executor.put(new TaskMessage(1, new DummyTask(t, m)));
    }
    public static void main(String[] args){
        try{
            executor = new TaskExecutor(5, 3000);
            System.out.println("TEST");
            put(1000, "task 1");
            put(1000, "task 2");
            put(1000, "task 3");
            put(1000, "task 4");
            System.out.println("sleeping");
            Thread.sleep(1500);
            System.out.println("4 tasks should be done");
            System.out.println("2 new tasks, no new consumer expected");
            put(1000, "task 5");
            put(3000, "task 6");
            System.out.println("sleeping");
            Thread.sleep(3500);
            System.out.println("2 new tasks, 1 new consumer expected");
            put(1000, "task 7");
            put(1000, "task 8");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
}
