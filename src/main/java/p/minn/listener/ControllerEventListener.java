package p.minn.listener;

import java.nio.channels.SocketChannel;

import p.minn.client.BaseClient;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public interface ControllerEventListener<T,T1> {
  
    BaseClient<T,T1> getClientById(int group,int clientId);
	void loginClien(int id,BaseClient<T,T1> client);
	void hsClient(SocketChannel socket);
	BaseClient<T,T1> createClient(String uuid,SocketChannel socket);
	void broadcast(int group, T packet,int clientid)throws Exception ;
	void removeLostConnectionClient(String uuid,int group, int clientId);
	BaseClient<T,T1> getClient(String uuid);
}
