package sdt.proxy.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sdt.proxy.socket.ProxySocket;

public class ThreadManager {
	/*public static  int corePoolSize ;
	public static  int maximumPoolSize; 
	public static  long keepLiveTime;
	public static  TimeUnit unit;*/
	public static  ThreadPoolExecutor threadPoolExecutor ;
	public static ExcSocketThread excSocketThread;
	public static void init(int corePoolSize,int maximumPoolSize,long keepLiveTime,TimeUnit unit){
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, 
				maximumPoolSize,
	            keepLiveTime,
	            unit,
	            new LinkedBlockingDeque<Runnable>());
		excSocketThread = new ExcSocketThread();
		
	}
	
	
}
