package harry.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

/**
 * 生活文件工具
 * 
 * @author harry
 *
 */
public final class FileUtil {
	private static final int LEN = 1024;
	private static final byte[] bytes = new byte[LEN];
	private static final String EMPTY = "";

	private FileUtil() {
	}

	public static final void update(String srcFile, Map<String, String> map) throws FileNotFoundException {
		File file = new File(srcFile);
		String fileName;
		if (!file.exists())
			throw new FileNotFoundException();
		else if (file.isFile())
			fileName = file.getName();
		else
			return;

		if (CheckUtils.isNullOrZero(map))
			return;
		
		
	}

	/**
	 * 复制文件到目的
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param destDirectories
	 *            目的目录
	 * @throws IOException
	 */
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

	/**
	 * 计算文件出现字符串的数量
	 * 
	 * @param file
	 *            文件
	 * @param string
	 *            字符串
	 * @return 数量
	 * @throws IOException
	 */
	public static final int count(File file, String string) throws IOException {
		int count = 0;

		try (FileInputStream in = new FileInputStream(file)) {
			StringBuffer s = new StringBuffer(EMPTY);
			int len = -1;
			while ((len = in.read(bytes)) != -1) {
				s.append(new String(bytes, 0, len));
				while (StringUtil.findString(s.toString(), string)) {
					count++;
					s.replace(0, s.length(), s.substring(s.indexOf(string) + string.length()));
				}

				s.replace(0, s.length(), EMPTY);
			}
		}

		return count;
	}
}
