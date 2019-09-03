package com.qf.book.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qf.book.utlis.UpUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qf.book.pojo.Book;
import com.qf.book.dto.BookDto;
import com.qf.book.pojo.Kind;
import com.qf.book.vo.Message;
import com.qf.book.repository.BookRepository;
import com.qf.book.repository.KindRepository;
import com.qf.book.service.BookService;

/**
 * BookService的业务实现类
 * 业务层（编写前端，功能的操作代码 的地方）
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Resource//注入与依赖
	private BookRepository bookRepository;

	@Resource
	private KindRepository kindRepository;

	//查询所有的书籍
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	//通过书籍名查询书籍
	@Override
	public List<Book> findByLike(String bName) {
		//下面呢我们使用模糊查询
		return bookRepository.findAllByBNameLike("%"+bName+"%");
	}

	//添加书籍消息
	@Override
	public Message addBook(Book book, String type, MultipartFile file, HttpServletRequest request) {
		//这里紧耦合（获取消息对象）
		Message msg = new Message();
		Kind kind = kindRepository.findByType(type);
		//这里判断书籍类型是否存在 有就不添加，没有就添加
		if (kind != null) {
			Book book1 = bookRepository.findByBName(book.getbName());
			if (book1 == null) {
				//书籍类型
				book.setKind(kind);
				if (file.getSize() > 0) {
					String imgPath = UpUtils.upfile(file, request);
					book.setAddress(imgPath);
				} else {
					book.setAddress("");
				}
				bookRepository.save(book);
				BookDto bookDto = new BookDto(book);
				msg.setBook(bookDto);
				msg.setMsg("success");
			} else {
				msg.setMsg("该书已存在,请勿重复添加!");
			}
		} else {
			msg.setMsg("该类型不存在!");
		}
		return msg;
	}

	//更新书籍
	@Override
	public Message updateBook(Book book, String type, MultipartFile file, HttpServletRequest request) {
		Message msg = new Message();
		Kind kind = kindRepository.findByType(type);
		if (kind != null) {//判断书籍类型部位NULL
			Book book1 = bookRepository.findById(book.getId()).get();
			if (book1 != null) {//判断书籍不为NULL
				book.setKind(kind);
				if (file.getSize() > 0) {//判断上传的书籍大小大于0
					String imgPath = UpUtils.upfile(file, request);
					book.setAddress(imgPath);
				} else {
					book.setAddress(book1.getAddress());
				}
				bookRepository.save(book);
				BookDto bookDto = new BookDto(book);
				msg.setBook(bookDto);
				msg.setMsg("success");
			} else {
				msg.setMsg("该图书不存在,请刷新!");
			}
		} else {
			msg.setMsg("该类型不存在!!");
		}
		return msg;
	}

	//删除书籍
	@Override
	public Message deleteById(Integer bId) {
		Message msg = new Message();
		Book book = bookRepository.findById(bId).get();
		if (book != null) {
			bookRepository.deleteById(book.getId());
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("该对象已被删除");
			return msg;
		}
	}
}
