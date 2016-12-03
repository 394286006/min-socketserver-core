package p.minn.packet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author minn
 * @QQ:394286006
 * 
 */
public abstract class Encoder<T> {
  protected  Logger logger = LoggerFactory.getLogger(this.getClass());
  protected SocketChannel socket;
  protected BufferedOutputStream out;
  
  public void setSocket(SocketChannel socket) {
      this.socket = socket;
  }

  public Encoder ( SocketChannel socket)
  {
    this.socket=socket;
    try {
      out=new BufferedOutputStream(socket.socket().getOutputStream());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    }
  }
   public void add ( T packet)throws IOException{
       write(packet);
   }
 
  public abstract void write(T packet) throws IOException;
}
