package p.minn.utils;

import java.text.SimpleDateFormat;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class DateUtil {

	
	public static String toDateTime(long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
	}
	
	public static void main(String[] args){
		
	}
}
