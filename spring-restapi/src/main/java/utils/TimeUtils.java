package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	public static String getNormalYMDTime(long value) {  
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;  
	    String time = format.format(new Date(value)) ;  
	    return time;  
	}
}
