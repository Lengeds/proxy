package sdt.proxy.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import sdt.proxy.thread.ExcSocketThread;

public class AcceptClientSocket {
    
     public void startMonitor() {
         try {
             ServerSocket serverSocket = new ServerSocket(8081);
                 int num=0;
           // new My().start();
             while(true) {
                  
                   startSocket(serverSocket.accept());
                   System.out.println("启动第"+(++num)+"个代理线程");
                 
             }
            
             
           }catch(Exception e){
                   e.printStackTrace();
           }
     }
     
     public void startSocket(Socket socket){
    	 SocketHandle socketHandle = new SocketHandle(socket);
    	 ExcSocketThread exc = new ExcSocketThread();
         exc.init1(socketHandle, "run");
         exc.init2();
         new Thread(exc).start();
    	
    	
     }
     
     
}
