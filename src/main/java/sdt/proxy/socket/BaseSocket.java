package sdt.proxy.socket;

import java.net.Socket;

public abstract class BaseSocket {
    private Socket socket;
    
    public abstract void readFromBuffer(BufferArea bufferArea);
    public abstract void writeToBuffer(BufferArea bufferArea);
    public BaseSocket(Socket socket) {
        this.socket = socket；
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
