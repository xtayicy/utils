package harry.utils;

import java.util.Map;

/**
 * 
 * @author harry
 *
 */
public final class CheckUtil {
	private static final int SIZE = 0;
	
	private CheckUtil(){}
	
	public static final boolean isNullOrZero(String... input){
		
		return input == null || input.length == SIZE;
	}
	
	public static final boolean isNullOrZero(Map input){
		
		return input == null || input.size() == SIZE;
	}
}
