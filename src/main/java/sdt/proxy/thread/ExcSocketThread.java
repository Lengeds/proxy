package sdt.proxy.thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import sdt.proxy.socket.BaseSocket;
import sdt.proxy.socket.BufferArea;
import sdt.proxy.socket.ClientSocket;
import sdt.proxy.socket.HostSocket;

public class ExcSocketThread implements Runnable{
    public BaseSocket baseSocket;
    public String action;
    public Method method;
    public void excSocket(Method method) {
        try {
			method.invoke(baseSocket);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        
     }
    
    
   public void init(BaseSocket baseSocket,String name,Class<?>... parameterTypes){
	   Class<BaseSocket> class1 = (Class<BaseSocket>) baseSocket.getClass();
	   try {
		   method=class1.getMethod(name,parameterTypes);
	   } catch (NoSuchMethodException e) {
		    e.printStackTrace();
	   } catch (SecurityException e) {
		    e.printStackTrace();
	   }
   }
    
	@Override
	public void run() {
		excSocket(method);
		
	}
     
   
     }
