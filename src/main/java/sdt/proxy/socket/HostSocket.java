package sdt.proxy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

public class HostSocket extends BaseSocket{
  //the remote host and port to which socket is connected to
    private String host; 
    private int port;
    private String buffer;
    private BufferArea readArea;
    private BufferArea writeArea;
 public HostSocket(Socket socket,BufferArea readArea,BufferArea writeArea) {
     //initialize socket for server
	  super(socket);
	  this.readArea = readArea;
      this.writeArea = writeArea;
      intitialize();
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
	 int row=0; //row代表http连接化还是https连接
	 StringBuffer sb     = new StringBuffer();
	 StringBuffer temp   = new StringBuffer();
	 String[] arrayStr=null;
	 byte v;
	    try {
			while(true){
				v = readArea.take();
				temp.append(v);
                if(v==10){//读完一行，如果是第一行就分析是http还是https，如果不是就分析是否包含host
                	
                	arrayStr = temp.toString().split(":");
                	if(arrayStr.length>0){
                		if( row==0 && arrayStr[0].equals("CONNECT")){//https连接
                			row=1;
                		}else if ( arrayStr[0].equals("Host")) { //判断是否包含“Host”
        	               if(arrayStr.length>2) {
        	                   host = arrayStr[1];
        	                   port = Integer.valueOf(arrayStr[2]);
        	               }else {
        	                   host = arrayStr[1];
        	                   port=(row==0?80:443);
        	                    }
        	              break;
        	            }
        	            
                	}
                	sb.append(temp);
      	            temp.delete(0, temp.length());
  	                sb.append(v);
    	            
                }else{
					temp.append(v);
				}
				
			}//while
			if(row==0){ //http连接
				setSocket(new Socket(host, port));
			}else{ //https连接
				setSocket((Socket)SSLSocketFactory.getDefault().createSocket(host,port));
			}
			
			buffer=sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    send();
	    accept();
	 
}
/*
 * 在发送数据给目标主机之前，先从缓冲区获取目标主机的ip和端口
 * (non-Javadoc)
 * @see sdt.proxy.socket.BaseSocket#readFromBuffer(sdt.proxy.socket.BufferArea)
 */
@Override
public void send() {
	 OutputStream outputStream= null;
	
	    try {
			outputStream = getSocket().getOutputStream();
			outputStream.write(buffer.getBytes());
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
	    	   writeArea.add((byte)data);
	      
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
