package prodcons;

public interface IProdConsBuffer {
    //fifo

    public void put(Message m) throws InterruptedException;
    public Message get() throws InterruptedException;
    public Message get_(int last_id_read) throws InterruptedException;
    public Message[] get(int k) throws InterruptedException;
    public Message[] get_(int last_id_read, int k) throws InterruptedException;
    
    public int nmsg(); //nb msg in the buffer
    public int totmsg(); //nb total msg depuis le début
}
