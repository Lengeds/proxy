package sdt.proxy.thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import sdt.proxy.socket.BaseSocket;
import sdt.proxy.socket.BufferArea;
import sdt.proxy.socket.ClientSocket;
import sdt.proxy.socket.HostSocket;

public class ExcSocketThread implements Runnable{
    public ClientSocket clientSocket;
    public HostSocket hostSocket;
  
    public void startSocket() {
        /*绑定socket的inputStream和outputStream到缓冲区(BufferArea)对应的区域,ClientSocket的inputStream和outputStream
        *分别绑定BufferArea的read和write,ServerSocket的inputStream和outputStream分别绑定BufferArea的write和read
        */
        
        
     }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
     
   
     }
