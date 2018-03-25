package sdt.proxy.socket;

public class BufferArea {
    public byte[] dataArea;
    public int length;
	public int writeCursor;
	public int readCursor;
   // Queue<String> a = new ArrayBlockingQueue<String>(1000);
	
	/**
	 * initial bufferArea
	 */
     public void init(int size){
    	 length = size;
    	 dataArea = new byte[size];
    	 //writeCursor[row]
    	 writeCursor=0;
    	 readCursor=0;
    	 
     }
     /**
      * add data into  dataArea
      */
     public void add(byte data){
    	 //该缓冲区数据已满，进入溢出状态，等待数据全部被访问之后清空该缓冲区
    	 if(writeCursor>=length){
		      if(readCursor==writeCursor){//数据已经被读完，进行缓冲区清空和指针重置操作
				 writeCursor = readCursor =0;
				 dataArea[writeCursor++] = data;
				 synchronized (dataArea) { //唤醒等待读的一方
					 dataArea.notifyAll();
				 }
		      }else{ //数据没有被读完
		    	  synchronized (dataArea){
		    		  try {
		    			     dataArea.wait();
							 writeCursor = readCursor =0;
							 dataArea[writeCursor++] = data;
							 dataArea.notifyAll();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
		    	 
		      }
    		 
    	 }else if(readCursor==0){ //当readCursor和writeCursor都为0时，take函数也会发生暂停，故需要唤醒
    		 synchronized (dataArea){
    		    dataArea[writeCursor++] = data;
    		    dataArea.notifyAll(); 
    		 }
    	 }else {
    	       dataArea[writeCursor++] = data;
    	 }
    	 
    	 
     }
     
     /**
      * get data from dataArea
      */
     public byte take(){
    	 if(readCursor==writeCursor){//当前没有数据可读，等待缓冲区刷新数据
    		 synchronized (dataArea) {
				try{
					dataArea.notifyAll();
					/*System.out.println("开始等待！");*/
					dataArea.wait();
					return dataArea[readCursor++];
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
    	 }
    	
    	 return dataArea[readCursor++];
    	 
    	
     }
    
    
}
