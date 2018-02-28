package httpProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class SocketHandle extends Thread {

    private Socket socket;

    public SocketHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream clientOutput = null;
        InputStream clientInput = null;
        Socket proxySocket = null;
        InputStream proxyInput = null;
        OutputStream proxyOutput = null;
        try {
            clientInput = socket.getInputStream();
            clientOutput = socket.getOutputStream();
           
            String line;
            String host = "";
            /* System.out.println(clientInput.toString()); */
            int read = 0;
            /*
             * while((read=clientInput.read())!=-1) { System.out.print((char)read); }
             */

            // 读取HTTP请求头，并拿到HOST请求头和method
            List<StringBuffer> list = new ArrayList<StringBuffer>();
            InputStreamReader in = new InputStreamReader(clientInput);
            
            BufferedReader bf = new BufferedReader(in);
          //  LineBuffer lineBuffer = new LineBuffer(1024);
            int i=0;
               
            String[] arrayStr;
            String currentLine, result;
            int port =80;
            currentLine = result = null;
           while ((currentLine = bf.readLine()) != null) {
               System.out.println(currentLine);
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
          /*  while ((read=clientInput.read())!=-1) {
                System.out.print((char)read);    
           
               }*/
         //   System.out.println("host:"+host+"\n"+"port:"+port);

           
            /*  while ((read=clientInput.read())!=-1) {
               System.out.print((char)read);    
          
              }
          */
            System.out.println("ceshi");
            System.out.println(host+"  "+port);
        /*    proxySocket=new Socket(host,port);
            proxyInput = proxySocket.getInputStream(); 
            proxyOutput = proxySocket.getOutputStream();
            new ProxyHandleThread(proxyInput, proxyOutput).start();
            while(true) {
                clientOutput.write(proxyInput.read());
                }*/
            /*
             * String type = headStr.substring(0, headStr.indexOf(" "));
             * //根据host头解析出目标服务器的host和port String[] hostTemp = host.split(":"); host =
             * hostTemp[0]; int port = 80; if (hostTemp.length > 1) { port =
             * Integer.valueOf(hostTemp[1]); } 
             //连接到目标服务器 proxySocket = new Socket(host,
             * port); proxyInput = proxySocket.getInputStream(); proxyOutput =
             * proxySocket.getOutputStream(); //根据HTTP method来判断是https还是http请求 if
             * ("CONNECT".equalsIgnoreCase(type)) {//https先建立隧道
             * clientOutput.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
             * clientOutput.flush(); } else {//http直接将请求头转发
             * proxyOutput.write(headStr.toString().getBytes()); } //新开线程转发客户端请求至目标服务器 new
             * ProxyHandleThread(clientInput, proxyOutput).start(); //转发目标服务器响应至客户端 while
             * (true) { clientOutput.write(proxyInput.read()); }
             */
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (proxyInput != null) {
                try {
                    proxyOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (proxyOutput != null) {
                try {
                    proxyOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (proxySocket != null) {
                try {
                    proxySocket.close();
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}