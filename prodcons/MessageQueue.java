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

    public int size(){
        return size;
    }

    public void add(Message m) throws Exception{
        available --;
        if (available == 0){
            throw new Exception("Queue Full: no available places in queue");
        }
        if (end == size-1){
            end = 0;
            message[end] = m;
        }
        else{
            message[++end] = m;
        }
    }

    public Message get() throws Exception{
        available ++;
        if (available == size) throw new Exception("Queue Empty: no message in queue");
        if (begin == size-1){
            begin = 0;
            return message[size-1];
        }
        return message[begin++];
    }
}
