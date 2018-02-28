package check;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class My{ 
    public static void  main(String args[]) throws Exception {
        Socket socket = new Socket("www.baidu.com",80);
        if(socket.isConnected()) {
            System.out.println("连接上");
        }
        StringBuffer sb = new StringBuffer("GET https://www.baidu.com/ HTTP/1.1\r\n");
        // 以下为请求头 
        sb.append("Host: www.baidu.com\r\n");
       // sb.append("User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0\r\n");
        sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
        sb.append("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
        // 注意这里不要使用压缩 否则返回乱码
        sb.append("Accept-Encoding: \r\n");
        sb.append("Connection: keep-alive\r\n");
        sb.append("Upgrade-Insecure-Requests: 1\r\n");
        // 注意这里要换行结束请求头
        sb.append("\r\n");
       // System.out.println(sb.toString());
        try {
            OutputStream os = socket.getOutputStream();
           
          os.write(sb.toString().getBytes());
         
            InputStream is = socket.getInputStream();
           /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];*/
            StringBuffer sbf =new StringBuffer();
            int len = 0;
            while ((len = is.read()) != -1) {
                   
                    System.out.print((char)len);
                sbf.append((char)len);
            }
            System.out.println(sbf.length());
            socket.close();
         }catch (Exception e) {
           e.printStackTrace();
        }


} 
    }
