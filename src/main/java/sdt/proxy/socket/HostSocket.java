package sdt.proxy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HostSocket extends BaseSocket{
  //the remote host and port to which socket is connected to
    private String host; 
    private int port;
    private String protocolType;
    
    private String buffer;
    private BufferArea readArea;
    private BufferArea writeArea;
    private OutputStream clientSocketOut;
 public HostSocket(Socket socket, BufferArea readArea,BufferArea writeArea) {
     //initialize socket for server
     super(socket);
     this.readArea = readArea;
     this.writeArea = writeArea;  
     try {
		clientSocketOut = socket.getOutputStream();
	} catch (IOException e) {
		e.printStackTrace();
	}
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
	System.out.println("intitialize.................");
	setSocket(connetHost());
	//System.out.println("intitialize+++++++++++++++++");
	 if(protocolType.equals("https")){//如果时https连接，发送一个状态已连接说明给客户端，否则直接发送请求头给目的主机
		 System.out.println("....................https");
		 byte[] results ="HTTP/1.1 200 Connection Established\r\n\r\n".getBytes();
		 for(int i= 0;i<results.length;i++){
			 writeArea.add(results[i]);
			// getSocket().getOutputStream().f
		 }
		 readArea.clear();
		 
	 }else if(protocolType.equals("http")){
		 System.out.println("....................http");
		 try {
			getSocket().getOutputStream().write(buffer.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	//System.out.println("执行完hostSocket初始化.............");
	 
}
/*
 * 在发送数据给目标主机之前，先从缓冲区获取目标主机的ip和端口
 * (non-Javadoc)
 * @see sdt.proxy.socket.BaseSocket#readFromBuffer(sdt.proxy.socket.BufferArea)
 */
@Override
public void send() {
   // if(connetHost()) {//在进行send操作之前，先连接上目的主机
        OutputStream outputStream= null;
        
        try {
                    //System.out.println("***********"+getSocket().isConnected()+"     "+getSocket().getRemoteSocketAddress());
                 // if(getSocket().isConnected()){
                	//  clientSocketOut.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
                	  outputStream = getSocket().getOutputStream();
                      while(true){
                              byte a=readArea.take(0);
                          //    System.out.println("&&&&&&&&&&&&&&&&"+(char)a+"  "+a);
                              outputStream.write(a);
                              
                      }
                //  }
        	     
            } catch (IOException e) {
                    e.printStackTrace();
            }

    //}
	 
}

@Override
public void accept() {
	 InputStream inputStream = null;
	    try {
	        inputStream  = getSocket().getInputStream();
	   
	        int data;
	      //  System.out.println("返回的数据:");
	       while ((data=inputStream.read())!=-1) {
	      //   System.out.print((char)data);
	    	   writeArea.add((byte)data);
              	      
	          }
	    }catch(Exception e) {
	      e.printStackTrace();
	     }
    
}

public Socket connetHost(){
    int row=0; //row代表行数
    StringBuffer sb     = new StringBuffer();
    StringBuffer temp   = new StringBuffer();
    String[] arrayStr=null;
    byte v;
       try {
    	   System.out.println("Area1的大小:"+readArea.writeCursor+"  "+readArea.readCursor);
             while(true){
                v = readArea.take(1);
                System.out.print((char)v);
                temp.append((char)v);
                if(v == 10){//读完一行，如果是第一行就分析是http还是https，如果不是就分析是否包含host
                	   row++;
                	 //  System.out.println("读完一行"+temp.toString());
                   if(row==1){//第一行，判断时http还是https连接
                	   arrayStr = temp.toString().split(" ");
                	   protocolType = arrayStr[0].equals("CONNECT")?"https":"http";
                   }else{
                	   arrayStr = temp.toString().split(":");
                	   if(arrayStr.length>0){
                           if ( arrayStr[0].equals("Host")) { //判断是否包含“Host”
                               if(arrayStr.length>2) {
                                   host = arrayStr[1].replaceAll(" ", "");
                                   String a = arrayStr[2].replaceAll("\n", "").replaceAll("\r", "");
                                   port = Integer.valueOf(a);
                                   
                               }else {
                              	 String a = arrayStr[1].replaceAll(" ", "");
                              	 host = a.replaceAll("\n", "").replaceAll("\r", "");
                                     port=(protocolType.equals("http")?80:443);
                                         }
                            //   System.out.println("跳出while循环");
                               sb.append(temp);
                               break;
                          }//else if
                         
                            }
                    }
                  
               sb.append(temp);
               temp.delete(0, temp.length());
              /* sb.append(v);*/
               
           }
                           
           }//while
             
             System.out.println("**************port:"+port+"      "+"host:"+host+"     "+"row:"+row+"  "+"protocolType:"+protocolType);
              buffer=sb.toString();
       //    if(protocolType.equals("http")){ //http连接
              return new Socket(host, port);
         //  }else{ //https连接
                       //    return SSLSocketFactory.getDefault().createSocket(host,port);
           //    }
                   
           } catch (Exception e) {
                   e.printStackTrace();
                   return null; 
           }
       

	
}



}
