package harry.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author harry
 *
 */
public final class HttpUtil {
	private enum RequestType {
		GET, POST
	}

	public static String sendGet(String url, Map<String, String> params) {
		StringBuffer sb = null;
		if (!CheckUtil.isNullOrZero(params)) {
			sb = new StringBuffer("?");
			int flag = 0;
			for (Map.Entry<String, String> entity : params.entrySet()) {
				sb.append(entity.getKey()).append("=").append(entity.getValue());
				if (flag++ != params.size()) {
					sb.append("&");
				}
			}
		}

		BufferedReader bufferedReader = null;
		StringBuffer result = new StringBuffer();
		try {
			URLConnection openConnection = new URL(sb != null ? url + sb.toString() : url).openConnection();
			openConnection.setRequestProperty("accept", "*/*");
			openConnection.setRequestProperty("connection", "Keep-Alive");
			openConnection.connect();
			bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result.toString();
	}

	@Test
	public void testSendGet() {
		System.out.println(
				sendGet("http://central.maven.org/maven2/org/apache/maven/plugins/maven-dependency-plugin/2.2/maven-dependency-plugin-2.2.pom",
						null));
	}

	@Test
	public void testGetUrlFileName() {
		String url = "http://central.maven.org/maven2/org/apache/maven/plugins/maven-dependency-plugin/2.2/maven-dependency-plugin-2.2.pom";
		String file = url.substring(url.lastIndexOf("/") + 1, url.length());
		System.out.println(file);
	}

	private static String getUrlFileName(String url) {

		return url.substring(url.lastIndexOf("/") + 1, url.length());
	}

	public static void sendPost(String url, Map<String, String> params) {

	}
	
	public static void httpResultToFile(String url, RequestType requestType, Map<String, String> params) throws IOException {
		if (requestType == RequestType.GET) {
			
			try(FileOutputStream fileOutputStream = new FileOutputStream(new File(getUrlFileName(url)));){
				fileOutputStream.write(sendGet(url, params).getBytes());
			}
		} else {

		}
	}
	
	@Test
	public void testHttpResultToFile() throws IOException{
		String url = "http://central.maven.org/maven2/org/apache/maven/plugins/maven-dependency-plugin/2.2/maven-dependency-plugin-2.2.pom";
		RequestType requestType = RequestType.GET;
		httpResultToFile(url,requestType,null);
	}
}
