package p.minn.packet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

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
  protected byte [ ] data;           
  protected ByteBuffer buffer;   
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
    List<T1> t1=null;
       buffer.clear();
       int read = 0;
       read = socket.read(buffer);
       if (read == -1){
        logger.error("Connection closed.");
        throw new IOException("Connection closed.");
       }
       if (read == 0){
           return;
       }
       t1= read(buffer.array());
       eventSwitch(t1);
     
 }
  protected abstract List<T1> read (byte[] data) throws Exception ;
  protected void eventSwitch (List<T1> wrappers) throws Exception {};
  

}
