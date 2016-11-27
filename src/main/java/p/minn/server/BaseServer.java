package p.minn.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import p.minn.controller.BaseController;
import p.minn.utils.BaseConstants;


/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class BaseServer<T,T1> {
    protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	private ServerSocketChannel channel;
	public BaseController<T,T1> controller;
	
	public BaseServer(){
		try {
			channel = ServerSocketChannel.open( );
			channel.socket( ).bind( new InetSocketAddress( BaseConstants.SOCKET_PORT ) );
		} catch (Exception e) {
		  logger.error(e.getMessage());
		}
	}
	
	public void start(){
	  logger.info("server running!");
	  logger.info("author:minn");
	  logger.info("QQ:394286006");
	  logger.info("monitor on port:"+BaseConstants.SOCKET_PORT );
		try {
		while(true){
			SocketChannel sc=channel.accept();
			sc.configureBlocking(BaseConstants.SOCKET_BLOCK);
			controller.hsClient(sc);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		  logger.error(e.getMessage());
		}
	}
	
	
}
