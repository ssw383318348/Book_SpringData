package com.qf.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.qf.book.pojo.Book;
import com.qf.book.vo.Message;

/**
 * service接口就是对前端的一个个的功能点（操作）
 */
public interface BookService {

	//查询多有的户籍
	List<Book> findAll();
	//通过书名查询书籍
	List<Book> findByLike(String bName);
	//添加书籍的消息
	Message addBook(Book book, String type, MultipartFile file, HttpServletRequest request);
	//更新书籍的消息
	Message updateBook(Book book, String type, MultipartFile file, HttpServletRequest request);
	//通过ID删除消息
	Message deleteById(Integer bId);

}
