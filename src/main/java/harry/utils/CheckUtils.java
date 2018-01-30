package harry.utils;

import java.util.Map;

/**
 * 
 * @author harry
 *
 */
public class CheckUtils {
	private static final int SIZE = 0;
	
	private CheckUtils(){}
	
	public static final boolean isNullOrZero(String... input){
		
		return input == null || input.length == SIZE;
	}
	
	public static final boolean isNullOrZero(Map input){
		
		return input == null || input.size() == SIZE;
	}
}
