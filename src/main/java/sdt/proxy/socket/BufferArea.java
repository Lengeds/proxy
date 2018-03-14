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
		      if(readCursor>=length){ //数据已经被读完，进行缓冲区清空和指针重置操作
				 writeCursor = readCursor =0;
				 dataArea[writeCursor++] = data;
		      }else{ //等待数据读完
		    	  synchronized (dataArea) {
		    		  try {
		    			     wait();
							 writeCursor = readCursor =0;
							 dataArea[writeCursor++] = data;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
		    	 
		      }
    		 
    	 }else{
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
					wait();
					return dataArea[readCursor++];
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
    	 }
    	 return dataArea[readCursor++];
     }
    
    
}
