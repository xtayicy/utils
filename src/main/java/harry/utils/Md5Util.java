package harry.utils;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

/**
 * 
 * @author Harry
 *
 */
public final class Md5Util{
	private Md5Util(){}
	
	public static String encode(String rawPass){
		return encode(rawPass,null);
	}
	
	public static String encode(String rawPass,String salt){
		
		return new Md5PasswordEncoder().encodePassword(rawPass, salt);
	}
}
