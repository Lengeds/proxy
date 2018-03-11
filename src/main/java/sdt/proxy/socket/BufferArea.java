package sdt.proxy.socket;

import java.util.Queue;

public class BufferArea {
    public byte[] read;
    public byte[] write;
     public Queue<byte[]> queue ; 
    
     public void overflow() {
      
    }
    
}
