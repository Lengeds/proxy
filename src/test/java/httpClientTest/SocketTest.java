package httpClientTest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1;

public class SocketTest {

    public static void main(String[] args) throws Exception{
   //  Socket s = SSLSocketFactory.getDefault().createSocket("www.baidu.com",443);
      /*  Socket s = new Socket("127.0.0.1",9000);//端口号要一致。  
        
        OutputStream os = s.getOutputStream();
        StringBuffer sb =new StringBuffer();
          sb.append("GET http://www.baidu.com/ HTTP/1.1\r\n");
          sb.append("Host: 主机\r\n");
          sb.append("User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0\r\n");
          sb.append("Proxy-Connection: keep-alive\r\n");
          sb.append("Connection: keep-alive\r\n\r\n");*/
         
          
       // os.write(sb.toString().getBytes()); 
          String sd = "主";
          byte[] byt = sd.getBytes();
         
          for(int i=0;i<byt.length;i++){
        	 // int r=(int)(byt[i]&7f);
        	 System.out.println(byt[i]);
          }
     //   os.w
     //   String ss= "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=测试&rsv_pq=93e7074000012c15&rsv_t=231cpNSc+qiO276kCmmIq29BmEdueMaBEUo3GfuKKr1sPJ+lDlSTnpj357A&rqlang=cn&rsv_enter=1&rsv_sug3=6&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&inputT=25&rsv_sug4=6594&rsv_sug=1";
      //  os.write(ss.getBytes());
      /*  System.out.println(s.isConnected());
        InputStream is = s.getInputStream();  
        int c;*/
    
          /*while((c=is.read())!=-1) {
                System.out.print((char)c);
          }*/
    }

}
