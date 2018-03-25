package sdt.proxy.socket;

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
                   System.out.println("启动第"+(++num)+"个代理线程");
                   startSocket(serverSocket.accept());
                   
                   //System.out.println("启动一个线程");
             }
            
             
           }catch(Exception e){
                   e.printStackTrace();
           }
     }
     
     public void startSocket(Socket socket){
    	 BufferArea area1 = new BufferArea();
    	 BufferArea area2 = new BufferArea();
    	 area1.init(1024);
    	 area2.init(1024);
    	 System.out.println("start create  client and host socket");
    	 ClientSocket clientSocket = new ClientSocket(socket, area2, area1);
    	 HostSocket hostSocket = new HostSocket(socket, area1, area2);
    	 System.out.println("end create socket"); 
    	 
    	 ExcSocketThread ex1 = new ExcSocketThread();
    	 ex1.init(clientSocket, "send");
    	 new Thread(ex1).start();
    	 System.out.println("start clientSocket send");
    	 
    	 ExcSocketThread ex2 = new ExcSocketThread();
       ex2.init(clientSocket, "accept");
       new Thread(ex2).start();
       System.out.println("start clientSocket accept");
       
       ExcSocketThread ex3 = new ExcSocketThread();
       ex3.init(hostSocket, "send");
       new Thread(ex3).start();
       System.out.println("start hostSocket send");
       
       ExcSocketThread ex4 = new ExcSocketThread();
       ex4.init(hostSocket, "accept");
       new Thread(ex4).start();
       System.out.println("start hostSocket accept");
     }
     
     
}
