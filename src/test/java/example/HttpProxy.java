package example;
import java.net.ServerSocket;

public class HttpProxy {
        public static void main(String args[]) {
                try {
                          ServerSocket serverSocket = new ServerSocket(8081);
                          int i=0;
                        // new My().start();
                          while(true) {
                                //System.out.println("启动线程");
                                  new SocketHandle(serverSocket.accept(),i).start();
                                  System.out.println("启动第"+(++i)+"个线程");
                          }
                         
                          
                        }catch(Exception e){
                                e.printStackTrace();
                        }
        }
        
}