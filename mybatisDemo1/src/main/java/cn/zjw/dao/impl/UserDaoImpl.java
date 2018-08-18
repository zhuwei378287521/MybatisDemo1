package cn.zjw.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.zjw.dao.UserDao;
import cn.zjw.po.User;

public class UserDaoImpl  extends SqlSessionDaoSupport implements UserDao{

	//注入SqlSessionFactory
		public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
			this.setSqlSessionFactory(sqlSessionFactory);
		}

		private SqlSessionFactory sqlSessionFactory;

		public User getUserById(int id) throws Exception {
			SqlSession session = sqlSessionFactory.openSession();
			User user = null;
			try {
				//通过sqlsession调用selectOne方法获取一条结果集
				//参数1：指定定义的statement的id,参数2：指定向statement中传递的参数
				user = (User) session.selectOne("test.findUserById", 1);
				System.out.println(user);
							
			} finally{
				session.close();
			}
			return user;

		}

		public void insertUser(User user) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				sqlSession.insert("insertUser", user);
				sqlSession.commit();
			} finally{
				sqlSession.close();
			}

		}
	

}
