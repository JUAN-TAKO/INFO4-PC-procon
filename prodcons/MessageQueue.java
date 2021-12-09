package prodcons;

import prodcons.Message;
import java.lang.ArrayIndexOutOfBoundsException; 

public class  MessageQueue
   {
    
      public Message[] buf;          // Circular buffer
      public int      read, write, count;  // read and write pointers + count
    
      // Constructor
      public MessageQueue(int size)
      {
        buf = new Message[size];    // Create array for circular buffer
        count = 0;
        read = 0;                  // Initialized read & write pointers
        write = 0;
      }
    
      /* ====================================================
         enqueue(x ):
        ==================================================== */
      public void add( Message x )   throws ArrayIndexOutOfBoundsException
      {
        if ( length() == size() )  // Full...
           throw new ArrayIndexOutOfBoundsException("Queue is full");
    
        buf[write] = x;                 // Store x in buf at write pointer
        write = (write+1)%(buf.length); // Advance the write pointer
        count++;
      }
    
      /* ====================================================
         dequeue():
        ==================================================== */
      public Message get( ) throws ArrayIndexOutOfBoundsException
      {
        Message r;   // Variable used to save the return value
    
        if ( length() == 0 )
           throw new ArrayIndexOutOfBoundsException("Queue is empty");
        
    
        r = buf[read];                 // Save return value
        read = (read+1)%(buf.length);  // Advance the read pointer
        count--;
        return r;
      }

      public Message peek( ){
        if ( length() == 0 )
           return null;
    
        return buf[read];
      }

      public int length(){
        return count;
      }
      public int size(){
          return buf.length;
      }
      public int available(){
          return size()-length();
      }
   }