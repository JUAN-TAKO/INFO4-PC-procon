package prodcons.v7;

public class DummyTask implements Runnable{
    private String name;
    private long processing_time;
    
    public DummyTask(long processing_time, String name){
        this.processing_time = processing_time;
        this.name = name;
    }
    
    public void run(){
        System.out.println(name + " begin");
        try{
            Thread.sleep(processing_time);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        System.out.println(name + " end");
    }
}
