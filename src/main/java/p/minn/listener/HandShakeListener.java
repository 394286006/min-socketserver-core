package p.minn.listener;

import java.nio.channels.SocketChannel;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public interface HandShakeListener {

	public void handshake(SocketChannel socket)throws Exception;
	public void step() throws Exception;
}
