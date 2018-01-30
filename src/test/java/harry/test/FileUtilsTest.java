package harry.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import harry.utils.FileUtil;

public class FileUtilsTest {
	
	/**
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		String fileName = "maven-war-plugin-2.6.jar";
		String groupId = "org.apache.maven.plugins";
		String artifactId = "maven-war-plugin";
		String version = "2.6";
		
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
