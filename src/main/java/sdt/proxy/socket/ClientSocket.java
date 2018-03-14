package sdt.proxy.socket;

import java.io.InputStream;
import java.net.Socket;

public class ClientSocket extends BaseSocket{
   
  /* //the remote host and port to which socket is connected to
   private String host; 
   private int port;*/
   public ClientSocket(Socket socket) {
        super(socket);
        
    }


public Socket getSocket() { 
    return getSocket();
}

@Override
public void readFromBuffer(BufferArea bufferArea) {
    InputStream inputStream = null;
    try {
        inputStream  = getSocket().getInputStream();
       // InputStreamReader in = new InputStreamReader(read);
      /*  BufferedInputStream bfIn = new BufferedInputStream(read, 1000);
        BufferedReader bf = new BufferedReader(in);*/
        int i=0;
        String[] arrayStr;
        String currentLine, result;
        String host;
        int port =80;
        currentLine = result = null;
       while (inputStream.read()!=-1) {
    	   
             
         //  System.out.println(currentLine);
           /* arrayStr = currentLine.split(":");
            if (arrayStr.length>0 && arrayStr[0].equals("Host")) { // https connection
               if(arrayStr.length>2) {
                   host = arrayStr[1];
                   port = Integer.valueOf(arrayStr[2]);
               }else {
                   host = arrayStr[1];
                  // bf. 
                    }
                }*/
          }
    }catch(Exception e) {
      e.printStackTrace();
     }
}


@Override
public void writeToBuffer(BufferArea bufferArea) {
    // TODO Auto-generated method stub
    
}


    
   
   
   
    
}
