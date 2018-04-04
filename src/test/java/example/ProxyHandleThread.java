package example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProxyHandleThread extends Thread {

    private InputStream input;
    private OutputStream output;
    private int number;
    public ProxyHandleThread(InputStream input, OutputStream output,int number) {
        this.input = input;
        this.output = output;
        this.number=number;
    }

    @Override
    public void run() {
        try {
        	int s;
            while (true) {
                s= input.read();   	
                output.write(s);
                //if(number==0){
                //	System.out.print((char)s);	
               // }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}