package httpClientTest;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SocketConnetc {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket socket = new Socket("www.baidu.com",80);
		/*SocketAddress socketAddress = new SocketAddress("www.baidu.com");*/
	//	socket.connect("www.baidu.com");
		//socket.shutdownInput();
	}
}
