package sdt.proxy.socket;

import java.util.Queue;

public class BufferArea {
    public byte[][] dataArea;
    public int length;
	public int[] writeCursor;
	public int[] readCursor;
    
	//initial bufferArea
     public void init(int num,int size){
    	 length = size;
    	 dataArea = new byte[num][size];
    	 //writeCursor[row]
    	 writeCursor=new int[num];
    	 readCursor=new int[num];
    	 
     }
     //add data into one of dataArea
     public void add(int row,byte data){
    	 if(++writeCursor[row]>length){
    		 try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		 
    	 }else{
    		 dataArea[row][writeCursor[row]] = data;	 
    	 }
    	 
    	 
     }
     
     public byte take(int row){
    	 return dataArea[row][readCursor[row]++];
     }
     public void overflow() {
         
         
     }
    
}
