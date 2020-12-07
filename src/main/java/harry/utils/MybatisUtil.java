package harry.utils;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @author Harry
 *
 */
public final class MybatisUtil{
	private static SqlSession session;
	private static SqlSessionFactory sqlSessionFactory;
	
	private MybatisUtil(){}
	
	static{
		try {
			sqlSessionFactory =new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("Mybatis-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession() throws IOException{
		if(session == null){
			session = sqlSessionFactory.openSession();
		}
		
		return session;
	}
	
	public static void close(){
		if(session != null){
			session.close();
		}
	}
}
