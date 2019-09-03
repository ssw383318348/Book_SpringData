package com.qf.book.service;

import com.qf.book.vo.Message;
import com.qf.book.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

	Message login(User user);

	List<User> findAll();

	Message addUser(User user);

	Message deleteUser(Integer uId);

	Message updateUser(User user);

	void addUsers(List<User> users);

	void updatePwd(String newpwd, HttpServletRequest request);

	List<User> findByLike(String uName);
	
	


}
