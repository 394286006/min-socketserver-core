package p.minn.controller;

import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import p.minn.client.BaseClient;
import p.minn.handshake.BaseHandShake;
import p.minn.job.GroupId;
import p.minn.job.JobThread;
import p.minn.listener.ControllerEventListener;
import p.minn.utils.BaseConstants;

/**
 * @author minn
 * @param <T>
 * @QQ:394286006
 * 
 */
public abstract class BaseController<T,T1> extends Thread implements ControllerEventListener<T,T1>{
    protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Hashtable<Integer, Hashtable<Integer,BaseClient<T,T1>>> groups=new Hashtable<Integer,  Hashtable<Integer,BaseClient<T,T1>>>();  
	protected Hashtable<Integer,JobThread<T,T1>> jobs=new Hashtable<Integer, JobThread<T,T1>>();
	protected Hashtable<Integer,BaseClient<T,T1>> newClients=new Hashtable<Integer,BaseClient<T,T1>>();
	protected Hashtable<Integer,BaseClient<T,T1>> removeClients=new Hashtable<Integer,BaseClient<T,T1>>();
	protected Hashtable<String,GroupId> currentClients=new Hashtable<String,GroupId>();
	protected JobThread<T,T1> job;
	protected Object syn=new Object();
	protected int clientId=0;
	protected BaseHandShake<T,T1> hsclient;
	private int currentGroup;
	
	public BaseController(){
		
	}
	public synchronized int getClientId(){
		
		return ++clientId;
	}
	
	@Override
	public void run() {
	   logger.debug("thread number˸"+BaseConstants.THREAD_NUMBER);
		while(true){
			try{
			  if(jobs.size()<BaseConstants.THREAD_NUMBER)
      			for(int i=0;i<BaseConstants.THREAD_NUMBER;i++){
      				currentGroup=i;
      				if(!jobs.containsKey(i)){
      					if(groups.containsKey(i)&&groups.get(i).size()<=BaseConstants.ONE_GROUP_NUMBER)
      						break;
      					groups.put(i, new Hashtable<Integer, BaseClient<T,T1>>());
      					job=new JobThread<T,T1>(groups.get(i),this);
      					jobs.put(i, job);
      					job.start();
      				}
      			  }
				   Iterator<Integer>  it=newClients.keySet().iterator();
					while(it.hasNext()){
					Integer	 key=it.next();
					if(groups.containsKey(currentGroup)&&groups.get(currentGroup).size()<BaseConstants.ONE_GROUP_NUMBER){
						if(!groups.get(currentGroup).containsKey(key)){
						   BaseClient<T,T1> bc=newClients.get(key);
						    bc.setGroup(currentGroup);
							currentClients.put(bc.uuid, new GroupId(currentGroup,bc.clientId));
							groups.get(currentGroup).put(key, newClients.get(key));
							 newClients.remove(key);
							break;
						}
					}
					}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	public BaseClient<T,T1> getClientById(int group,int clientId){
		return groups.get(group).get(clientId);
	}
	
	public void hsClient(SocketChannel socket) {
		hsclient.registerSocket(socket);
	}

	public void removeLostConnectionClient(String uuid,int group, int clientId) {
			groups.get(group).remove(clientId);
			currentClients.remove(uuid);
		 logger.info("***********user logout**************");
		 logger.info("******current user count:"+currentLoginClients()+"*****");
	}
	
	public void loginClien(int id, BaseClient<T,T1> obj) {
            this.newClients.put(id, obj);
          logger.info("****************user login************");
          logger.info("******current login user count:" + currentLoginClients() + "*****");
    }
	   
	protected int currentLoginClients(){
		int rs=0;
		Collection<Hashtable<Integer,BaseClient<T,T1>>> cs=groups.values();
		for(Hashtable<Integer,BaseClient<T,T1>> c:cs ){
			rs+=c.size();
		}
		return rs;
	}
	 public BaseClient<T,T1> getClient(String uuid) {
       // TODO Auto-generated method stub
       if(uuid==null)
         return null;
       GroupId gi=currentClients.get(uuid);
       if(gi==null){
         return null;
       }
       return  groups.get(gi.getGroup()).get(gi.getClientId());
          
     }
}
