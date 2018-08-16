package cn.zjw.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.zjw.po.User;

public class Test {
	//�Ự����
		private SqlSessionFactory sqlSessionFactory;
		public void createSqlSessionFactory() throws IOException {
			// �����ļ�
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// ʹ��SqlSessionFactoryBuilder��xml�����ļ��д���SqlSessionFactory
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

		}
		// ���� id��ѯ�û���Ϣ
		public void testFindUserById() {
			// ���ݿ�Ựʵ��
			SqlSession sqlSession = null;
			try {
				// �������ݿ�Ựʵ��sqlSession
				sqlSession = sqlSessionFactory.openSession();
				// ��ѯ������¼�������û�id��ѯ�û���Ϣ
				User user = (User) sqlSession.selectOne("cn.zjw.po.User.findUserById", 1);
				// ����û���Ϣ
				System.out.println(user);
				System.out.println("userid="+user.getId()+" username ="+user.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (sqlSession != null) {
					sqlSession.close();
				}
			}

		}
		
		public void testFindUserByUsername() {
			SqlSession sqlSession = null;
			sqlSession = sqlSessionFactory.openSession();
			try {
				// ��ѯ������¼�������û�id��ѯ�û���Ϣ
				//���ֲ�ѯ������ֶ�������������쳣
				User user = (User) sqlSession.selectOne("cn.zjw.po.User.findUserByUsername", "zjw1");
				// ����û���Ϣ
				System.out.println(user);
				System.out.println("userid="+user.getId()+" username ="+user.getUsername());
			} catch (TooManyResultsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("û���ҵ����");
			}
			
		}

		
		public static void main(String[] args) {
			Test test = new Test();
			try {
				test.createSqlSessionFactory();
				test.testFindUserById();
				test.testFindUserByUsername();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
