package com.qf.book.repository;


import com.qf.book.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	//通过书名查询借书用户
	List<User> findAllByUNameLike(String uName);

	//通过书名查询还书用户
	User findByUName(String getuName);


}
