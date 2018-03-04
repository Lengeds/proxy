package sdt.proxy.socket;

import java.net.ServerSocket;

public class AcceptClientSocket {
     public void startMonitor() {
         try {
             ServerSocket serverSocket = new ServerSocket(8081);
           // new My().start();
             while(true) {
                   System.out.println("启动线程");
                     //new SocketHandle(serverSocket.accept()).start();
                   System.out.println("启动一个线程");
             }
            
             
           }catch(Exception e){
                   e.printStackTrace();
           }
     }
}
