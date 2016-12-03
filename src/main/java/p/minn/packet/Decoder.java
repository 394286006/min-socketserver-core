package p.minn.packet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import p.minn.listener.ClientEventListener;
/**
 * @author minn
 * @QQ:394286006
 * 
 */
public abstract class Decoder<T,T1> {
  protected  Logger logger = LoggerFactory.getLogger(this.getClass());
  private ByteBuffer buffer;   
  protected SocketChannel socket;
  protected ClientEventListener<T,T1> evt=null;
  
  public Decoder (SocketChannel socket )
  {
      buffer = ByteBuffer.allocate( 50000 );
      this.socket=socket;
  }
  
  public Decoder ( ClientEventListener<T,T1> evt,SocketChannel socket )
  {
      this.evt=evt;
      buffer = ByteBuffer.allocate( 50000 );
      this.socket=socket;
              
  }
  public void setSocket(SocketChannel socket) {
      this.socket = socket;
  }
  public void step() throws Exception{
    int read = 0;
       buffer.clear();
       read = socket.read(buffer);
       if (read == -1){
        logger.error("Connection closed.");
        throw new IOException("Connection closed.");
       }
       if (read == 0){
           return;
       }
       buffer.flip();
        read(buffer);
       
 }
  protected abstract void read (ByteBuffer buffer) throws Exception ;
  

}
