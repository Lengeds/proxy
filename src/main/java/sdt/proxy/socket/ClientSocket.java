package sdt.proxy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket extends BaseSocket{
	 private BufferArea readArea;
	 private BufferArea writeArea;
  /* //the remote host and port to which socket is connected to
   private String host; 
   private int port;*/
   public ClientSocket(Socket socket,BufferArea readArea,BufferArea writeArea) {
        super(socket);
        this.readArea = readArea;
        this.writeArea = writeArea;
        initialize();
    }


/*public Socket getSocket() { 
    return getSocket();
}*/

public void initialize(){
	/*send();
	accept();*/
}


@Override
public void send() {
	 OutputStream outputStream= null;
	    try {
			outputStream = getSocket().getOutputStream();
			while(true){
				outputStream.write(readArea.take());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
}


@Override
public void accept() {
   
    
    InputStream inputStream = null;
    try {
        inputStream  = getSocket().getInputStream();
      
        int data;
       while ((data=inputStream.read())!=-1) {
    	//   System.out.print((char)data);
    	   writeArea.add((byte)data);
             
          }
    }catch(Exception e) {
      e.printStackTrace();
     }
    
}


    
   
   
   
    
}
