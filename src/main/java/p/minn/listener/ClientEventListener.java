package p.minn.listener;

import java.util.List;




/**
 * @author minn
 * @QQ:394286006
 * 
 */
public interface ClientEventListener<T,T1> {
	
	void onEvent(T1 wrapper)throws Exception;
	void onAudioEvent(T packet,T1 wrapper)throws Exception;
	void onFlvEvent(T packet,T1 wrapper)throws Exception;
	void onPictureEvent(T packet,T1 wrapper)throws Exception;
	void onCustomizeEvent(T packet,T1 wrapper)throws Exception;
    void onEvent(List<T1> wrappers, T newPacket)throws Exception;
    void onErrorPacketHandler(String valueOf)throws Exception;
}
