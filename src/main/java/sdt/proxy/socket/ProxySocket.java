package sdt.proxy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import sdt.proxy.encryption.HalfMode;
import sdt.proxy.encryption.Mode;

public  class ProxySocket {
	
	private Socket socket;

	private String protocolType;
	private Mode<Integer> halfMode = new HalfMode();
        public String connectHostName;
    public ProxySocket(){
    	
    }
    public ProxySocket(Socket socket) {
        this.socket = socket; 
          
    }
    public Socket getSocket() {
        return socket;
        
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
	
	public String getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	
	
	
	public void send(InputStream in)  {

		 OutputStream outputStream= null;
		    try {
				outputStream = getSocket().getOutputStream();
				int s;
				//if(connectHostName.equals("astro.fashion.qq.com")) {
				    System.out.println("****************************");
				    while((s=in.read())!=-1){
                                         int k = halfMode.decrypt(s);
				                 System.out.print((char)k);
                                         outputStream.write(k);
                                         
                                     }
				//}
		            /*else {
				    while((s=in.read())!=-1){
                                        outputStream.write(halfMode.decrypt(s));
                                         
                                     }
				}*/
					
				
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	public int accept() {
	   try {
		  return getSocket().getInputStream().read();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
	   return -1;  
	}
}
