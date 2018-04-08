package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

class SocketHandle extends Thread {

        private Socket socket;
    private int number;
    public SocketHandle(Socket socket,int number) {
    	//socket.set
        this.socket = socket;
        this.number=number;
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
            InputStreamReader in = new InputStreamReader(clientInput);
            BufferedReader bf = new BufferedReader(in);
            StringBuilder headStr = new StringBuilder();
            //读取HTTP请求头，并拿到HOST请求头和method
          /*  int c;
            while((c=clientInput.read())!=-1){
                System.out.print((char)c);
            }*/
            
            while (null != (line = bf.readLine())) {
           //     System.out.println(line);
                headStr.append(line + "\r\n");
                if (line.length() == 0) {
                       // System.out.println("执行break！----------------------");
                    break;
                } else {
                    String[] temp = line.split(" ");
                    if (temp[0].contains("Host")) {
                        host = temp[1];
                    }
                }
            }
            String type = headStr.substring(0, headStr.indexOf(" "));
            //根据host头解析出目标服务器的host和port
            String[] hostTemp = host.split(":");
            host = hostTemp[0];
            int port = 80;
            if (hostTemp.length > 1) {
                port = Integer.valueOf(hostTemp[1]);
            }
            //连接到目标服务器
            System.out.println("host:"+host+"    "+"post:"+port);
            proxySocket = new Socket(host, port);
            proxyInput = proxySocket.getInputStream();
            proxyOutput = proxySocket.getOutputStream();
            //根据HTTP method来判断是https还是http请求
            if ("CONNECT".equalsIgnoreCase(type)) {//https先建立隧道
                clientOutput.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
              //  clientOutput.flush();
            } else {//http直接将请求头转发
                proxyOutput.write(headStr.toString().getBytes());
            }
            //新开线程转发客户端请求至目标服务器
            new ProxyHandleThread(clientInput, proxyOutput,number).start();
            //转发目标服务器响应至客户端
            int s;
            while (true) {
                s=proxyInput.read();
                /*if(number==0){
                        System.out.print((char)s);      
                }*/
                
                clientOutput.write(s);
            }
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
