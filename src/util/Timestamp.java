package util;

import java.util.Calendar;


/**
 * Class to make simpler calls to get the current timestamp. Only contains the method now 
 * which calls Calendar.getInstance().getTime().getTime().
 * @author Gustav
 *
 */

public class Timestamp {

	public static long now() {
		return Calendar.getInstance().getTime().getTime();
	}
	
}
