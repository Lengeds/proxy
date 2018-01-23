package httpProxy;

import java.net.ServerSocket;

public class HttpProxy {
	public static void main(String args[]) {
		try {
			  ServerSocket serverSocket = new ServerSocket(8081);
			  while(true) {
			        System.out.println("启动线程");
				  new SocketHandle(serverSocket.accept()).start();
				  System.out.println("启动一个线程");
			  }
			 
			  
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
}