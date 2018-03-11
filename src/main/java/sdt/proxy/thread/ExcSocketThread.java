package sdt.proxy.thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import sdt.proxy.socket.BaseSocket;
import sdt.proxy.socket.BufferArea;
import sdt.proxy.socket.ClientSocket;

public class ExcSocketThread implements Runnable{
    public BaseSocket baseSocket;
    public ClientSocket clientSocket;
    public BufferArea bufferArea;
    public void run() {
        /*绑定socket的inputStream和outputStream到缓冲区(BufferArea)对应的区域,ClientSocket的inputStream和outputStream
        *分别绑定BufferArea的read和write,ServerSocket的inputStream和outputStream分别绑定BufferArea的write和read
        */
        
        
     }
     
    public void write() {
        
     }
     public void read() {
         InputStream read = null;
         try {
             read  = baseSocket.getSocket().getInputStream();
                 
                        
             InputStreamReader in = new InputStreamReader(read);
             BufferedInputStream bfIn = new BufferedInputStream(read, 1000);
           
             BufferedReader bf = new BufferedReader(in);
           //  LineBuffer lineBuffer = new LineBuffer(1024);
             int i=0;
             String[] arrayStr;
             String currentLine, result;
             String host;
             int port =80;
             currentLine = result = null;
            while ((currentLine = bf.readLine()) != null) {
              //  System.out.println(currentLine);
                 arrayStr = currentLine.split(":");
                 if (arrayStr.length>0 && arrayStr[0].equals("Host")) { // https connection
                    if(arrayStr.length>2) {
                        host = arrayStr[1];
                        port = Integer.valueOf(arrayStr[2]);
                    }else {
                        host = arrayStr[1];
                       // bf. 
                         }
                     }
                 }
     }catch(Exception e) {
         
     }
     

}
     }
