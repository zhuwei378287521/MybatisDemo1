package cn.zjw.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.zjw.po.User;

public class UpdateTest {
	private static SqlSessionFactory sqlSessionFactory;

	public void createSqlSessionFactory() throws IOException {
		// �����ļ�
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// ʹ��SqlSessionFactoryBuilder��xml�����ļ��д���SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}
	
	public static void main(String[] args) {
		UpdateTest updateTest = new UpdateTest();
		SqlSession session=null;
		
		try {
			updateTest.createSqlSessionFactory();
			session = sqlSessionFactory.openSession();
			User user = new User();
			user.setId(7);
			user.setUsername("updatex");
			user.setAddress("河南郑州");
			user.setSex("1");
			session.update("cn.zjw.po.User.updateUser",user);
			session.commit();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
			System.out.println("更新完毕");
		}
		
		
	}
}
