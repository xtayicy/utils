package harry.utils;

/**
 * 
 * @author harry
 *
 */
public final class StringUtil {
	private static final String EMPTY = "";
	
	private StringUtil(){}
	
	
	public static String reverse(String arg0){
		if(arg0 == null){
			return EMPTY;
		}
		
		if(arg0 != null && arg0.length() == 1){
			return arg0;
		}
		
		return reverse(arg0.substring(1)) + arg0.charAt(0);
	}
	
	public static boolean findString(String arg0,String arg1){
		if(arg0 == null || arg1 == null){
			return false;
		}
		
		return arg0.indexOf(arg1) != -1;
	}
	
}
