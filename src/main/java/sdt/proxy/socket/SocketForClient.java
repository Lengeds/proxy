package sdt.proxy.socket;

import java.net.Socket;

public class SocketForClient{
   private Socket socket;
   //the remote host and port to which socket is connected to
   private String host; 
   private int port;
   public SocketForClient(Socket socket) {
        this.socket = socket; 
        
    }

   /*
    * sdsd
    */
public Socket getSocket() {
      
    return socket;
}

/*public void setSocket(Socket socket) {
    this.socket = socket;
}*/
    
   
   
   
    
}
