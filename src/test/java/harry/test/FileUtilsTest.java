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
	public void testRemove() throws FileNotFoundException{
		File file = new File("F:/sql/demo.sql");
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("F:/sql/demo.sql.bak"))));){
			String line;
			while((line = reader.readLine()) != null){
				if(line.contains("ON UPDATE CURRENT_TIMESTAMP")){
					line = line.replace("ON UPDATE CURRENT_TIMESTAMP", "");
				}
				
				if(line.contains("DEFAULT CURRENT_TIMESTAMP")){
					line = line.replace("DEFAULT CURRENT_TIMESTAMP", "");
				}
				
				writer.write(line);
				writer.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("end");
	}
	
	@Test
	public void test() throws IOException {
		String fileName = "geode-wan-9.1.1.jar";
		String groupId = "io.pivotal.gemfire";
		String artifactId = "geode-wan";
		String version = "9.1.1";
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
	public void testDeleteFile(){
		String directory = "D:\\.m2\\repository";
		String fileName = "m2e-lastUpdated.properties";
		deleteFile(directory, fileName,null);
	}
	
	@Test
	public void testDeleteEndWithFile(){
		String directory = "D:\\.m2\\repository";
		String suffix = ".lastUpdated";
		deleteFile(directory, null,suffix);
	}
	
	public void deleteFile(String directory,String fileName,String suffix){
		File[] files = new File(directory).listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				deleteFile(file.getAbsolutePath(),fileName,suffix);
			}else{
				if(file.getName().equals(fileName)| file.getName().endsWith(suffix)){
					file.delete();
					System.out.println(file.getAbsolutePath() + " has been deleted!");
					
				}
			}
		}
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
				System.out.println(file.getAbsolutePath() + " has been deleted.");
			}
		}
	}
	
	public Set<Jar> getJars(String fileName) throws IOException{
		Set<Jar> jars = new HashSet<Jar>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));){
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
		}
		
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
	
	@Test
	public void clean(){
		String m2_repository = "D:\\.m2\\repository";
		
		//String file = "D:\\.m2\\repository\\org\\springframework\\boot\\spring-boot-test\\2.0.0.RELEASE";
		listFiles(m2_repository);
	}
	
	public void listFiles(String dir){
		File m2_repository_file = new File(dir);
		File[] m2_repository_file_listFiles = m2_repository_file.listFiles();
		for (File m2_repository_file_listFile : m2_repository_file_listFiles) {
			if(m2_repository_file_listFile.isDirectory()){
				listFiles(m2_repository_file_listFile.getAbsolutePath());
			}else{
				String fileName = m2_repository_file_listFile.getName();
				if(fileName.endsWith("-sources.jar")){
					break;
				}
				
				if(fileName.endsWith(".jar")){
					String name = fileName.substring(0, fileName.indexOf(".jar"));
					String sourceFileName = name + "-sources.jar";
					File parentFile = m2_repository_file_listFile.getParentFile();
					String[] child_list = parentFile.list();
					boolean isFound = false;
					for (String child : child_list) {
						if(child.equals(sourceFileName)){
							isFound = true;
							break;
						}
					}
					
					if(!isFound){
						for (File file : parentFile.listFiles()) {
							file.delete();
						}
					}
				}
			}
		}
	}
}
