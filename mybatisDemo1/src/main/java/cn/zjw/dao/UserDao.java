package cn.zjw.dao;

import cn.zjw.po.User;

public interface UserDao {
	public User getUserById(int id) throws Exception;
	public void insertUser(User user) throws Exception;

}
