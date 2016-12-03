package p.minn.handshake;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import p.minn.client.BaseClient;
import p.minn.listener.ControllerEventListener;
import p.minn.listener.HandShakeListener;
import p.minn.utils.BaseConstants;


/**
 * @author minn
 * @QQ:394286006
 * 
 */
public abstract class BaseHandShake<T,T1> extends Thread implements HandShakeListener{
    protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	protected ControllerEventListener<T,T1> evt;
	protected ByteBuffer receivebuffer;
	protected List<SocketChannel> sockets;
	protected List<SocketChannel> newsockets;
	protected List<SocketChannel> removesockets;
    protected BaseClient<T,T1> client = null;
    protected int bytes=0;
    protected String uuid;
    public int state = 0;
    public boolean isHsp=false;
    protected long lasttime=System.currentTimeMillis();
    private long currenttime;
    public boolean isHsp() {
        return isHsp;
    }
    public void setHsp(boolean isHsp) {
        this.isHsp = isHsp;
    }
	public BaseHandShake(ControllerEventListener<T,T1> evt) {
		this.evt = evt;
		sockets=new ArrayList<SocketChannel>();
		newsockets=new ArrayList<SocketChannel>();
		removesockets=new ArrayList<SocketChannel>();

	}
	
	@Override
	public void run() {
	  
		while (true) {
			try {
			      currenttime=System.currentTimeMillis();
			      if(currenttime-lasttime>BaseConstants.INTERVAL){
    				  if(removesockets.size()>0||newsockets.size()>0){
    					sockets.removeAll(removesockets);
    					int newsize=newsockets.size();
    					if(newsize>1){
    					  sockets.addAll(newsockets.subList(0, newsize-1));
                          removesockets.clear();
                          newsockets.subList(0, newsize-1).clear();
    					}else{
    					  sockets.addAll(newsockets);
                          removesockets.clear();
                          newsockets.clear();
    					}
    					
    				  }
    				  lasttime=currenttime;
			      }
				
				step();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
	

	public void registerSocket(SocketChannel socket) {
		  newsockets.add(socket);
	}
}
