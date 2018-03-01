package harry.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import harry.utils.FileUtil;
import harry.utils.MathUtil;

public class FileUtilsTest {
	
	@Test
	public void testIsArithmeticProgression(){
		int[] arrs = {1,4,7,10};
		
		System.out.println(MathUtil.isArithmeticProgression(arrs));
	}
	
	@Test
	public void test() throws IOException {
		String fileName = "zkclient-0.5.jar";
		String groupId = "com.github.sgroschupf";
		String artifactId = "zkclient";
		String version = "0.5";
		
		FileUtil.copy("D:\\download\\" + fileName, 
				      "F:\\test");
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("-DgroupId", groupId);
		hashMap.put("-DartifactId", artifactId);
		hashMap.put("-Dversion", version);
		hashMap.put("-Dfile", "F:\\test\\" + fileName);
		String result = FileUtil.getResult("test.txt",hashMap, "mvn deploy:deploy-file ");
		System.out.println(result);
	}
}
