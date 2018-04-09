package sdt.proxy.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import sdt.proxy.thread.ExcSocketThread;
import sdt.proxy.thread.ThreadManager;

public class AcceptClientSocket {
    
     public void startMonitor() {
         try {
             ServerSocket serverSocket = new ServerSocket(8081);
             System.out.println("启动socket监听器完成............");
             long num=0;
             while(true) {
            	 System.out.println("线程池当前有"+ThreadManager.threadPoolExecutor.getPoolSize()+"个任务在执行........");
            	 ThreadManager.threadPoolExecutor.execute(ThreadManager.excSocketThread.excMethod(this,
                   		"startSocket", new Object[]{serverSocket.accept(),num++},Socket.class,long.class));
                  // startSocket(serverSocket.accept(),num++);
                   
                 
             }
            
             
           }catch(Exception e){
                   e.printStackTrace();
           }
     }
     
     public void startSocket(Socket socket,long num){
    	 System.out.println("*****收到第"+num+"个请求");
    	 SocketHandle socketHandle = new SocketHandle(socket);
    	 ThreadManager.threadPoolExecutor.execute(ThreadManager.excSocketThread.excMethod(socketHandle,
          		"run", new Object[]{}));
     }
     
     
}
