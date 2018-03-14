package sdt.proxy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSocket extends BaseSocket{
  //the remote host and port to which socket is connected to
    private String host; 
    private int port;
    
 public ServerSocket(Socket socket) {
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

@Override
public void readFromBuffer(BufferArea bufferArea) {
	
    
}

@Override
public void writeToBuffer(BufferArea bufferArea) {
   
    
}

}
