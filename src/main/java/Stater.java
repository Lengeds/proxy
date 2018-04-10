import java.util.concurrent.TimeUnit;

import sdt.proxy.socket.AcceptClientSocket;
import sdt.proxy.thread.ThreadManager;

public class Stater {
	public static void main(String[] args) {
		System.out.println("正在初始化ThreadManager...........");
		ThreadManager.init(100,200,360,TimeUnit.SECONDS);
		System.out.println("完成初始化ThreadManager...........");
		
		System.out.println("正在启动socket监听器AcceptClient...........");
		 AcceptClientSocket acs = new AcceptClientSocket();
         acs.startMonitor();
	}
}
