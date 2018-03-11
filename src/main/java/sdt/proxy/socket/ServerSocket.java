package sdt.proxy.socket;

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
    // TODO Auto-generated method stub
    
}

@Override
public void writeToBuffer(BufferArea bufferArea) {
    // TODO Auto-generated method stub
    
}

}
