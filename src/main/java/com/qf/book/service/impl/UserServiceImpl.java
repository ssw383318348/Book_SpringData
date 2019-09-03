package com.qf.book.service.impl;

import com.qf.book.vo.Message;
import com.qf.book.pojo.User;
import com.qf.book.repository.UserRepository;
import com.qf.book.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	//注册用户
	@Override
	public Message login(User user) {
		Message msg = new Message();
		User user1 = userRepository.findByUName(user.getuName());
		if (user1 != null) {
			String pwd = user.getuPwd();
			Md5Hash md5 = new Md5Hash(pwd);
			String pwd1 = md5.toString();
			if (user1.getuPwd().equals(pwd1)) {
				msg.setUser(user1);
				msg.setMsg("success");
				return msg;
			} else {
				msg.setMsg("密码错误");
				return msg;
			}
		} else {
			msg.setMsg("用户名错误");
			return msg;
		}
	}

	//查询所有用户
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	//添加用户消息
	@Override
	public Message addUser(User user) {
		Message msg = new Message();
		User user1 = userRepository.findByUName(user.getuName());
		if (user1 == null) {
			Md5Hash md5 = new Md5Hash("123");
			String pwd = md5.toString();
			user.setuPwd(pwd);
			userRepository.save(user);
			msg.setUser(user);
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("该用户已存在,请勿重复添加!");
			return msg;
		}
	}

	//删除用户
	@Override
	public Message deleteUser(Integer uId) {
		Message msg = new Message();
		User user = userRepository.findById(uId).get();
		if (user != null) {
			userRepository.delete(user);
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("该用户不存在!");
			return msg;
		}
	}

	//更新用户消息
	@Override
	public Message updateUser(User user) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		User user1 = userRepository.findById(user.getId()).get();
		if (user1 != null) {
			userRepository.save(user);
			msg.setUser(user);
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("该对象不存在!");
			return msg;
		}
	}

	//添加用户
	@Override
	public void addUsers(List<User> users) {
		for (User user : users) {
			Md5Hash md5 = new Md5Hash();
			String uPwd  = md5.toString();
			user.setuPwd(uPwd);
			userRepository.save(user);
		}
	}

	//更新用户密码
	@Override
	public void updatePwd(String newpwd,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Message m = (Message) request.getSession().getAttribute("msg1");
		User user = userRepository.findById(m.getUser().getId()).get();
		Md5Hash md5 = new Md5Hash(newpwd);
		String p = md5.toString();
		user.setuPwd(p);
		userRepository.save(user);
		
	}

	//通过用户名查询用户
	@Override
	public List<User> findByLike(String uName) {
		return userRepository.findAllByUNameLike("%"+uName+"%");
	}
}
