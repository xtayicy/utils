package harry.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import harry.test.constant.Constant;
import harry.test.entity.Jar;
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
		String groupId = "org.eclipse.aether";
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
	
	@Test
	public void delete() throws IOException{
		String fileName = "errors.txt";
		String destFileName = "errors.log";
		String m2_repository = "D:\\.m2\\repository";
		clean(fileName,destFileName);
		
		Set<Jar> jars = getJars(destFileName);
		
		for (Jar jar : jars) {
			StringBuffer sb = new StringBuffer(m2_repository);
			String groupId = jar.getGroupId();
			groupId = groupId.replace(".", "\\");
			sb.append("\\").append(groupId);
			sb.append("\\").append(jar.getArtifact());
			sb.append("\\").append(jar.getVersion());
			File[] listFiles = new File(sb.toString()).listFiles();
			for (File file : listFiles) {
				file.delete();
			}
		}
	}
	
	public Set<Jar> getJars(String fileName) throws IOException{
		Set<Jar> jars = new HashSet<Jar>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		String line;
		while((line = br.readLine()) != null){
			String[] arrs = null;
			
			if(line.startsWith(Constant.FAILURE_STR)){
				int beginIndex = line.indexOf(Constant.TRANSFER_STR);
				int endIndex = line.indexOf(Constant.FROM_STR);
				arrs = line.substring(beginIndex + Constant.TRANSFER_STR.length() + 1,endIndex).trim().split(":");
			}else if(line.startsWith(Constant.MISSING_STR)){
				int beginIndex = line.indexOf(Constant.ARTIFACT_STR);
				int endIndex = line.indexOf(Constant.POM_XML_STR);
				arrs = line.substring(beginIndex + Constant.ARTIFACT_STR.length() + 1,endIndex).trim().split(":");
			}
			
			jars.add(new Jar(arrs[0], arrs[1], arrs[3]));
		}
		
		br.close();
		
		return jars;
	}
	
	public void clean(String src,String dest) throws IOException{
		File destFile = new File(dest);
		if(destFile.exists()){
			destFile.delete();
		}
		
		Writer writer = new OutputStreamWriter(new FileOutputStream(destFile));
		BufferedWriter bw = new BufferedWriter(writer);
		
		Reader reader = new InputStreamReader(new FileInputStream(new File(src)));
		BufferedReader br = new BufferedReader(reader);
		String line;
		while((line = br.readLine()) != null){
			if(line.startsWith("Failure") || line.startsWith("Missing")){
				bw.write(line);
				bw.newLine();
			}
		}
		
		br.close();
		bw.close();
	}
}
