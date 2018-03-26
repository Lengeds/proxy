package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class SocketTest {

    public static void main(String[] args) throws Exception{
       Socket s = SSLSocketFactory.getDefault().createSocket("www.baidu.com",443);
      //  Socket s = new Socket(InetAddress.getByName("localhost"),8000);//端口号要一致。  
        
        OutputStream os = s.getOutputStream();
        StringBuffer sb =new StringBuffer();
          sb.append("CONNECT www.baidu.com:443 HTTP/1.1\r\n");
          sb.append("User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0\r\n");
          sb.append("Proxy-Connection: keep-alive");
          sb.append("Connection: keep-alive/r/n");
          sb.append("Host: www.baidu.com:443/r/n/r/n");
        os.write(sb.toString().getBytes());  
        InputStream is = s.getInputStream();  
        int c;
        System.out.println("********:"+sb.length());
          while((c=is.read())!=-1) {
                System.out.println((char)c);
          }
    }

}
