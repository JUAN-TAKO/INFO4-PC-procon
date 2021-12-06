package prodcons;

public interface IProdConsBuffer {
    //fifo

    public void put(Message m) throws InterruptedException;
    public Message get() throws InterruptedException;
    
    public int nmsg(); //nb msg in the buffer
    public int totmsg(); //nb total msg depuis le début
}
