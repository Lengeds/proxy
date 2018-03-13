package sdt.proxy.socket;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BufferArea {
  //  public Byte read;
    //public Byte write;
      public int size =1024;
     public Queue<Byte> read =  new ArrayQueue<Byte>(size); 
     public Queue<Byte> write;
     public void overflow() {
         read.size();
         
    }
    
}
