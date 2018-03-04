package sdt.proxy.thread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import sdt.proxy.socket.SocketForClient;

public class ExcSocketThread implements Runnable{
    private SocketForClient sfs;
    public void run() {
        
     }
     
    public void write() {
        
     }
     public void read() {
         InputStream read = null;
         try {
             read  = sfs.getSocket().getInputStream();
                  
         
             InputStreamReader in = new InputStreamReader(read);
             
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
     }
     

}
