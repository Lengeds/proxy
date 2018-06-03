package sdt.proxy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import sdt.proxy.encryption.HalfMode;
import sdt.proxy.encryption.Mode;
import sdt.proxy.thread.ThreadManager;

public class SocketHandle {

    private ProxySocket clientSocket;
    private ProxySocket hostSocket;
    private OutputStream clientOutput = null;
    private InputStream clientInput = null;
    private InputStream hostInput = null;
    private OutputStream hostOutput = null;
    private Mode<Integer> halfMode = new HalfMode();
    public SocketHandle(Socket socket) {
    	clientSocket = new ProxySocket(socket);
    	hostSocket = new ProxySocket();
    	//this.clientSocket.setSocket(socket);
    }
    
  
    public void run() {
        
        try {
            clientInput = clientSocket.getSocket().getInputStream();
            clientOutput = clientSocket.getSocket().getOutputStream();
            String line;
            String host = "";
           InputStreamReader in = new InputStreamReader(clientInput);
            BufferedReader bf = new BufferedReader(in);
            StringBuilder headStr = new StringBuilder(1024);
            StringBuilder sb = new StringBuilder();
            int row = 1;
            int encrypt,decrypt;
            while((encrypt=clientInput.read())!=-1) {
            	decrypt = halfMode.decrypt(encrypt);
                //System.out.print(encrypt+" ");
                sb.append((char)decrypt);
                if(decrypt==10) {//读完一行
                    //System.out.print(sb);
                   headStr.append(sb);
                   if(row==1){//如果是第一行就分析是http连接还是https
                       String[] temp = sb.toString().split(" ");
                       if(temp[0].equals("CONNECT")){
                           hostSocket.setProtocolType("https");
                       }else{
                           hostSocket.setProtocolType("http");
                               }
                       row++;
                   }else{
                       String[] temp = sb.toString().split(" ");
                       if (temp[0].equals("Host:")) {
                           host = temp[1].split("\r\n")[0];//去掉换行符
                              }
                         }
                   if(sb.length()==2) {//读完http头
                             break;
                         }
                   sb.delete(0, sb.length());
                   }
                     
                }
          
           //  hostSocket.setProtocolType();  = headStr.substring(0, headStr.indexOf(" "));
            //根据host头解析出目标服务器的host和port
            String[] hostTemp = host.split(":");
            
            host = hostTemp[0];
         //   System.out.println("主机:"+host);
            int port = 80;
            if (hostTemp.length > 1) {
                port = Integer.valueOf(hostTemp[1]);
            }
            
            //连接到目标服务器
         //   System.out.println("host:"+host+"    "+"post:"+port);
            Socket socket = new Socket(host, port);
            hostSocket.setSocket(socket);
            hostSocket.connectHostName = host;
            hostInput = hostSocket.getSocket().getInputStream();
            hostOutput = hostSocket.getSocket().getOutputStream();
            //根据HTTP method来判断是https还是http请求
            if ("https".equals(hostSocket.getProtocolType())) {//https先建立隧道
                String str= "HTTP/1.1 200 Connection Established\r\n\r\n";
                byte[] responeOk;
                responeOk = str.getBytes();
                StringBuilder encriptStr = new StringBuilder();
            	for(int i=0;i<responeOk.length;i++){ //加密
            		int ds = halfMode.encrypt((int)responeOk[i]);
            		 clientOutput.write(ds);
            	}
            	    // System.out.println("返回："+encriptStr.getBytes());
                //clientOutput.write(encriptStr.toString().getBytes());
                     
                clientOutput.flush();
            } else {//http直接将请求头转发
                  /* if(host.equals("astro.fashion.qq.com")) {
                       System.out.println(headStr.toString());
                         }*/
                   
            	hostOutput.write(headStr.toString().getBytes());
            	//System.out.println("发送http请求成功");
            	
            }
           
            //新开线程继续转发客户端请求至目标服务器
            ThreadManager.ThreadPool.execute(
            		 ThreadManager.excSocketThread.excMethod(
            		   hostSocket,
            		   "send",
            		   new Object[]{clientInput}, 
            		   InputStream.class));
           
            //转发目标服务器响应至客户端
           int s;
            while ( (s=hostInput.read())!=-1) {
                
            	//System.out.print((char)s);
                        
            	clientOutput.write(halfMode.encrypt(s));
            }
          
        } catch (Exception e) {
       /* 	System.out.println("********************:"+clientSocket.getSocket().isClosed()+"     "+clientSocket.getSocket().isConnected()
        			+"      "+clientSocket.getSocket().isInputShutdown()+"      "+clientSocket.getSocket().isOutputShutdown()
        			);*/
        	System.out.print("Exception catch:");
        	e.printStackTrace();
        } finally {
            if (hostInput != null) {
                try {
                	hostOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (hostOutput != null) {
                try {
                	hostOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (hostSocket.getSocket() != null) {
                try {
                	System.out.println("close hostSokect!");
                	hostSocket.getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientInput != null) {
                try {
                    clientInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientOutput != null) {
                try {
                    clientOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientSocket.getSocket() != null) {
                try {
                	clientSocket.getSocket().close();
                } catch (IOException e) {
                	
                    e.printStackTrace();
                }
            }
        }

    }
}
