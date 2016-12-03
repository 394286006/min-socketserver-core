package p.minn.client;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import p.minn.listener.ControllerEventListener;
import p.minn.listener.ClientEventListener;
import p.minn.packet.Decoder;
import p.minn.packet.Encoder;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public abstract class BaseClient<T,T1> implements ClientEventListener<T,T1>{
    protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	public int byteIn;
	public String uuid=null;
	public int clientId;
	public int group=0;
	private boolean isHs=false;
	protected Decoder<T,T1> decoder;
	protected Encoder<T> encoder;
	protected ControllerEventListener<T,T1> evt;
	private SocketChannel socket;
	public void setGroup(int group) {
		this.group = group;
	}
	public BaseClient(String uuid,SocketChannel socket,int clientId,ControllerEventListener<T,T1> evt){
		super();
		this.uuid=uuid;
		this.socket=socket;
		this.clientId=clientId;
		this.evt=evt;
		
	}
	
	public void setSocket(SocketChannel socket){
	    this.socket=socket;
		decoder.setSocket(socket);
		encoder.setSocket(socket);
	}
    
	public void step() throws Exception  {
			decoder.step();
	 }
	
	public void setHs(boolean isHs) {
		this.isHs = isHs;
	}
	public Encoder<T> getEncoder() {
		return encoder;
	}

	public boolean isHs() {
		return isHs;
	}
	
	public void addPacket(T packet) throws IOException{
		encoder.add(packet);
	}
	public String getIp() {
	      return socket.socket().getInetAddress().getHostAddress();
	}
	
	public void existsMessage(double reponseId)throws Exception {};
	public void onAudioEvent(T packet,T1 wrapper)throws Exception {}
    public void onFlvEvent(T packet,T1 wrapper)throws Exception {}
    public void onPictureEvent(T packet,T1 wrapper)throws Exception {}
    public void onCustomizeEvent(T packet,T1 wrapper)throws Exception {}
   
	
}
