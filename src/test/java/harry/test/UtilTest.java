package harry.test;

import org.junit.Test;

import harry.utils.Md5Util;

/**
 * 
 * @author Harry
 *
 */
public class UtilTest {
	@Test
	public void test(){
		System.out.println(Md5Util.encode("123"));
	}
}
