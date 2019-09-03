package com.qf.book.service.impl;

import com.qf.book.pojo.Book;
import com.qf.book.pojo.Kind;
import com.qf.book.vo.Message;
import com.qf.book.repository.BookRepository;
import com.qf.book.repository.KindRepository;
import com.qf.book.service.KindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class KindServiceImpl implements KindService {

	@Resource
	private BookRepository bookRepository;

	@Resource
	private KindRepository kindRepository;
	
	//查询所以书籍类型
	@Override
	public List<Kind> findAll() {
		// TODO Auto-generated method stub
		List<Kind> kinds = kindRepository.findAll();
		
		return kinds;
	}
	//添加书籍类型
	@Override
	public Message addKind(Kind kind) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Kind kind1 = kindRepository.findByType(kind.getType());
		if (kind1 == null) {
			kindRepository.save(kind);
			msg.setKind(kind);
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("该类型已存在!!");
			return msg;
		}
	}

	//更新书籍类型
	@Override
	public Message updateKind(Kind kind) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Kind kind1 = kindRepository.findByType(kind.getType());
		if (kind1 == null) {
			kindRepository.save(kind);
			msg.setKind(kind);
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("输入的类型已存在!");
			return msg;
		}
	}

	//通过Id删除书籍类型
	@Override
	public Message deleteById(Integer kId) {
		Message msg = new Message();
		Kind kind = kindRepository.findById(kId).get();
		if (kind != null) {
			List<Book> books = bookRepository.findAllByKind(kId);
			for (Book book : books) {
				 book.setKind(null);
				 bookRepository.save(book);
			}
			kindRepository.deleteById(kind.getId());
			msg.setMsg("success");
			return msg;
		}else {
			msg.setMsg("该对象不存在!");
			return msg;
		}
	}

}
