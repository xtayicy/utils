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
		String fileName = "maven-dependency-plugin-2.10.jar";
		String groupId = "org.apache.maven.plugins";
		String artifactId = "maven-dependency-plugin";
		String version = "2.10";
		String packaging = "jar";
		
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("-DgroupId", groupId);
		hashMap.put("-DartifactId", artifactId);
		hashMap.put("-Dversion", version);
		hashMap.put("-Dfile", "D:\\download\\" + fileName);
		hashMap.put("-Dpackaging", packaging);
		String result = FileUtil.getResult("test.txt",hashMap, "mvn deploy:deploy-file ");
		System.out.println(result);
	}
}
