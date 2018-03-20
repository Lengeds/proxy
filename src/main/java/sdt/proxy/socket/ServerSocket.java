package sdt.proxy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class ServerSocket extends BaseSocket{
  //the remote host and port to which socket is connected to
    private String host; 
    private int port;
    
 public ServerSocket(Socket socket) {
     //initialize socket for server
	 
	 super(socket);
 }

public String getHost() {
    return host;
}

public void setHost(String host) {
    this.host = host;
}

public int getPort() {
    return port;
}

public void setPort(int port) {
    this.port = port;
}

public void intitialize(){
	
}

@Override
public void readFromBuffer(BufferArea bufferArea) {
	 OutputStream outputStream= null;
	 StringBuffer sbhost = new StringBuffer();
	 StringBuffer sbport = new StringBuffer();
	    try {
			outputStream = getSocket().getOutputStream();
			while(true){
				outputStream.write(bufferArea.take());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    
}

@Override
public void writeToBuffer(BufferArea bufferArea) {
	 InputStream inputStream = null;
	    try {
	        inputStream  = getSocket().getInputStream();
	       // InputStreamReader in = new InputStreamReader(read);
	      /*  BufferedInputStream bfIn = new BufferedInputStream(read, 1000);
	        BufferedReader bf = new BufferedReader(in);*/
	   
	        int data;
	       while ((data=inputStream.read())!=-1) {
	    	   bufferArea.add((byte)data);
	             
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

public boolean connetHost(){
	try {
		SocketAddress socketAddress =new InetSocketAddress(host, port);
		getSocket().connect(socketAddress);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

	return true;
}



}
