package p.minn.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import p.minn.client.BaseClient;
import p.minn.listener.ControllerEventListener;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class JobThread<T,T1> extends Thread {
    protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	private ControllerEventListener<T,T1> evt;
	private Collection<BaseClient<T,T1>> clients;
	private ArrayList<BaseClient<T,T1>> lostClients;
	private ArrayList<BaseClient<T,T1>> newClients;
	private long lasttime=System.currentTimeMillis();
	private long currenttime;

	public JobThread(Hashtable<Integer, BaseClient<T,T1>> clienttable,ControllerEventListener<T,T1> evt) {
		this.evt=evt;
		this.clients = clienttable.values();
		lostClients = new ArrayList<BaseClient<T,T1>>();
		newClients = new ArrayList<BaseClient<T,T1>>();

	}

	@Override
	public void run() {

		while (true) {
		  
		  currenttime=System.currentTimeMillis();
		  if(currenttime-lasttime>1000){
			clients.removeAll(lostClients);
			clients.addAll(newClients);
			lostClients.clear();
			newClients.clear();
			lasttime=currenttime;
		  }
		 for (BaseClient<T,T1> client : clients) {
			try {
				if(client.isHs()){
					 client.step();
				}
			} catch (Exception e) {
				 e.printStackTrace();
				lostClients.add(client);
				evt.removeLostConnectionClient(client.uuid, client.group, client.clientId);
			}
		}
		
		}
	}

	public void addClient(BaseClient<T,T1> client) {
	  try{
           //lock.writeLock().lock();
		   newClients.add(client);
		}finally{
		  //lock.writeLock().unlock();
		}
	}

	public void removeClient(BaseClient<T,T1> client) {
	  try{
        //lock.writeLock().lock();
		lostClients.add(client);
	  }finally{
        //lock.writeLock().unlock();
      }
	}

}
