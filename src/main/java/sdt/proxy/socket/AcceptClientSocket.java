package sdt.proxy.socket;

import java.net.ServerSocket;
import java.net.Socket;

import sdt.proxy.thread.ExcSocketThread;

public class AcceptClientSocket {
     public void startMonitor() {
         try {
             ServerSocket serverSocket = new ServerSocket(8081);
           // new My().start();
             while(true) {
                   System.out.println("启动线程");
                   startSocket(serverSocket.accept());
                   System.out.println("启动一个线程");
             }
            
             
           }catch(Exception e){
                   e.printStackTrace();
           }
     }
     
     public void startSocket(Socket socket){
    	 BufferArea readArea = new BufferArea();
    	 BufferArea writeArea = new BufferArea();
    	 readArea.init(1024);
    	 writeArea.init(1024);
    	 ClientSocket clientSocket = new ClientSocket(socket, readArea, writeArea);
    	 HostSocket hostSocket = new HostSocket(socket, readArea, writeArea);
    	 ExcSocketThread ex1 = new ExcSocketThread();
    	 ex1.init(clientSocket, "send");
    	 new Thread(ex1).start();
    	
    	 
     }
     
     
}
