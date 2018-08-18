package cn.zjw.mybaits.mapper;

import java.util.List;

import cn.zjw.mybaits.mapper.po.User;

public interface UserMapper {
	//根据用户id查询用户信息
	public User findUserById(int id) throws Exception;
	//查询用户列表
	public List<User> findUserByUsername(String username) throws Exception;
	//添加用户信息
	public void insertUser(User user)throws Exception; 

}
