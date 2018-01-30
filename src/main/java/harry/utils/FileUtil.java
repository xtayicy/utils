package harry.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 生活文件工具
 * 
 * @author harry
 *
 */
public final class FileUtil {
	private static final String BLANK = " ";

	private FileUtil() {}
	
	private static final String getContent(File file) throws IOException{
		StringBuffer sb = new StringBuffer();
		char[] buffer = new char[1024];
		int len;
		try (Reader reader = new FileReader(file)) {
			while ((len = IOUtils.read(reader, buffer)) > 0) {
				sb.append(buffer,0,len);
			}
		}
		
		return sb.toString();
	}
	
	public static final String getResult(String srcFile, Map<String, String> map,String prefContent) throws IOException {
		File file = new File(srcFile);
		if (!file.exists())
			throw new FileNotFoundException();
		else if (!file.isFile())
			throw new FileNotFoundException();

		if (CheckUtils.isNullOrZero(map))
			throw new IllegalArgumentException();
		
		
		Map<String,String> linkedHashMap = new LinkedHashMap<String,String>();
		for (String blankResults: getContent(file).split(" ")) {
			linkedHashMap.put(blankResults.split("=")[0], blankResults.split("=")[1]);
		}
		
		for (Entry<String, String> entry : map.entrySet()) {
			if(linkedHashMap.containsKey(entry.getKey()))
				linkedHashMap.put(entry.getKey(), entry.getValue());
		}
		
		StringBuffer sb = new StringBuffer(prefContent);
		for (Entry<String,String> entry : linkedHashMap.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append(BLANK);
		}
		
		return sb.toString();
	}

	public static final void copy(String sourceFile, String... destDirectories) throws IOException {
		File srcFile = new File(sourceFile);
		String fileName;
		if (!srcFile.exists())
			throw new FileNotFoundException();
		else if (srcFile.isFile())
			fileName = srcFile.getName();
		else
			return;

		if (CheckUtils.isNullOrZero(destDirectories))
			throw new IllegalArgumentException();

		for (String destDir : destDirectories) {
			if (!new File(destDir, fileName).exists()) {
				FileUtils.copyFileToDirectory(srcFile, new File(destDir));
			}
		}
	}
}
