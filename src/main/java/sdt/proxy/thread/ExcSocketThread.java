package sdt.proxy.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import sdt.proxy.socket.ProxySocket;

public class ExcSocketThread<T> implements Runnable{
    public T t;
   // public String action;
    public Method method;
    public Object[] args;
    public void excSocket(Method method) {
        try {
        	//System.out.println("----------------"+agrs.length);
			method.invoke(t,args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        
     }
    
    
   public void init1(T t,String methodname,Class<?>... parameterTypes){
	   Class<T> class1 = (Class<T>) t.getClass();
	   try {
	       this.t =t;
		   method=class1.getMethod(methodname,parameterTypes);
	   } catch (NoSuchMethodException e) {
		    e.printStackTrace();
	   } catch (SecurityException e) {
		    e.printStackTrace();
	   }
   }
    
   public void init2(Object...args){
	 
	   this.args=args;
   }
   
	@Override
	public void run() {
		excSocket(method);
		
	}
     
   
     }
