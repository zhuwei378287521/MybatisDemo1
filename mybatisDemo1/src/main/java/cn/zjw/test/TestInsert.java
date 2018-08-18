package cn.zjw.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.zjw.po.User;

public class TestInsert {

	// �Ự����
	private static SqlSessionFactory sqlSessionFactory;

	public void createSqlSessionFactory() throws IOException {
		// �����ļ�
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// ʹ��SqlSessionFactoryBuilder��xml�����ļ��д���SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}

	public static void main(String[] args) {
		TestInsert testInsert = new TestInsert();
		try {
			testInsert.createSqlSessionFactory();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		SqlSession sqlSession = null;
		try {
			// 创建sqlSession
			sqlSession = sqlSessionFactory.openSession();
//			System.out.println(sqlSession);
			// ����û���Ϣ
			User user = new User();
			user.setUsername("注重租住");
			user.setAddress("河南郑州");
			user.setSex("1");
//			user.setPrice(1999.9f);
			sqlSession.insert("cn.zjw.po.User.insertUser", user);
			// 提交事务
			sqlSession.commit();
			System.out.println("事务提交");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
				System.out.println("连接CLOSE");
			}
		}
	}

}
