package com.qf.book.controller;

import com.qf.book.pojo.Book;
import com.qf.book.vo.Message;
import com.qf.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图书信息控制层
 * 做页面跳转和数据交互的
 * 
 */
@Controller//定义为控制层（）
@RequestMapping("/books")//公共的访问路劲
public class BookController {

	@Resource
	private BookService bookService;


	@RequestMapping("/findAll")//自定义的访问路径
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Book> books = bookService.findAll();
		mav.setViewName("/book.jsp");
		mav.addObject("books", books);
		return mav;
	}

	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String bName) {
		ModelAndView mav = new ModelAndView();
		List<Book> books = bookService.findByLike(bName);
		mav.setViewName("/book.jsp");//请求转发
		/**
		 * addObject是Request作用域存放数据（只针对当前请求有效）
		 * 传统的4大作用域 1.page（旨在当前页面有效） 2.request 3.session（只在本次会话中有效） 4.Application（程序运行时中有效）
		 */
		mav.addObject("books", books);
		return mav;
	}

	@RequestMapping("/addBook")
	@ResponseBody//返回数据是json(springMVC会把返回的数据以json的格式返回)
	//后端
	public Message addBook(Book book, String type, MultipartFile file, HttpServletRequest request) {
		try {
			Message msg = bookService.addBook(book, type,file,request);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = new Message();
			/**
			 * 成功回存放success 失败就会存放其他的信息
			 */

			msg.setMsg("操作异常!");
			return msg;
		}
	}

	@RequestMapping("/updateBook")
	@ResponseBody
	public Message updataBook(Book book, String type , MultipartFile file, HttpServletRequest request) {
		try {
			Message msg = bookService.updateBook(book,type,file,request);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常!");
			return msg;
		}
	}

	@RequestMapping("/deleteBook")
	@ResponseBody
	public Message deleteBook(Integer bId) {
		try {
			Message msg = bookService.deleteById(bId);
			return msg;
		} catch (Exception e) {

			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常!");
			return msg;
		}
	}

}
