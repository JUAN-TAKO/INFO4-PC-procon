package prodcons;

import prodcons.Message;

public class MessageQueue {
    private int size;
    private int begin;
    private int end;
    private int available;
    private Message message[];

    public MessageQueue(int size){
        this.size = size;
        begin = 0;
        end = 0; 
        available = size;
        message = new Message[size];
    }

    public int available(){
        return available;
    }

    public int begin(){
        return begin;
    }

    public int end(){
        return end;
    }

    public int length(){
        return size-available;
    }

    public void add(Message m) throws Exception{
        if (available == 0){
            throw new Exception("Queue Full: no available places in queue");
        }
        available --;
        if (end == size-1){
            end = 0;
            message[end] = m;
        }
        else{
            message[++end] = m;
        }
    }

    public Message get() throws Exception{
        if (available == size) throw new Exception("Queue Empty: no message in queue");
        this.available ++;
        if (begin == size-1){
            begin = 0;
            return message[size-1];
        }
        return message[begin++];
    }

    public static void main(String[] args){
        // test de la classe
        System.out.println("test");
        MessageQueue m = new MessageQueue(10);
        
        for (int k = 0; k < 7 ; k++){
            try {
                m.add(new Message());
            } catch (Exception e) {
                String s = "Queue out of bounds:" + k;
                System.out.println(s);
                e.printStackTrace();
            }
        }
        System.out.println("Test A");
        System.out.println("Available:" + m.available());
        System.out.println("Begin:" + m.begin());
        System.out.println("End:" + m.end());
        for (int k = 0; k <3 ; k++){
            try {
                m.get();
            } catch (Exception e) {
                String s = "Queue out of bounds:" + k;
                System.out.println(s);
                e.printStackTrace();
            }
        }
        System.out.println("Test B");
        System.out.println("Available:" + m.available());
        System.out.println("Begin:" + m.begin());
        System.out.println("End:" + m.end());
        for (int k = 0; k < 5 ; k++){
            try {
                m.add(new Message());
            } catch (Exception e) {
                String s = "Queue out of bounds";
                System.out.println(s);
                e.printStackTrace();
            }
        }
        System.out.println("Test C");
        System.out.println("Available:" + m.available());
        System.out.println("Begin:" + m.begin());
        System.out.println("End:" + m.end());
        for (int k = 0; k < 7 ; k++){
            try {
                m.get();
            } catch (Exception e) {
                String s = "Queue out of bounds";
                System.out.println(s);
                e.printStackTrace();
            }
        }
        System.out.println("Test D");
        System.out.println("Available:" + m.available());
        System.out.println("Begin:" + m.begin());
        System.out.println("End:" + m.end());

        MessageQueue m2 = new MessageQueue(1);
        try {
            m2.add(new Message());
            m2.get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Test E");
        System.out.println("Available:" + m2.available());
        System.out.println("Begin:" + m2.begin());
        System.out.println("End:" + m2.end());
        try {
            m2.add(new Message());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Test F");
        System.out.println("Available:" + m2.available());
        System.out.println("Begin:" + m2.begin());
        System.out.println("End:" + m2.end());
    }
}
