package cn.zjw.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.zjw.po.User;

public class TestSelect {
	//会话工厂
		private SqlSessionFactory sqlSessionFactory;
		public void createSqlSessionFactory() throws IOException {
			// 配置文件
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

		}
		// 根据 id查询用户信息
		public void testFindUserById() {
			// 数据库会话实例
			SqlSession sqlSession = null;
			try {
				// 创建数据库会话实例sqlSession
				sqlSession = sqlSessionFactory.openSession();
				// 查询单个记录，根据用户id查询用户信息
				User user = (User) sqlSession.selectOne("cn.zjw.po.User.findUserById", 1);
				// 输出用户信息
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
				// 查询单个记录，根据用户id查询用户信息
				//这种查询如果出现多个结果，会出现异常
				User user = (User) sqlSession.selectOne("cn.zjw.po.User.findUserByUsername", "zjw1");
				// 输出用户信息
				System.out.println(user);
				System.out.println("userid="+user.getId()+" username ="+user.getUsername());
			} catch (TooManyResultsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("没有找到结果");
			}
			
			
			//为了解决上面的问题，使用selectlist解决
			List<User> list = sqlSession.selectList("cn.zjw.po.User.findUserByUsername","zjw") ;
			for (User user : list) {
				System.out.println(user.getUsername()+" id= "+user.getId());
			}
		}

		
		public static void main(String[] args) {
			TestSelect test = new TestSelect();
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
