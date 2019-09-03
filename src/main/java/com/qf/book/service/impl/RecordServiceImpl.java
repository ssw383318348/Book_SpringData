package com.qf.book.service.impl;

import com.qf.book.pojo.Book;
import com.qf.book.vo.Message;
import com.qf.book.pojo.Record;
import com.qf.book.repository.BookRepository;
import com.qf.book.repository.RecordRepository;
import com.qf.book.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
//借书记录的业务层
@Service
@Transactional
public class RecordServiceImpl implements RecordService {

	@Resource
	private RecordRepository recordRepository;

	@Resource
	private BookRepository bookRepository;

	//查询可借的书籍
	@Override
	public Message borrow(Record record) {
		Message msg = new Message();
		Book book = bookRepository.findByBName(record.getbName());
		if (book != null) {//判断书籍不为NULL
			record.setState("已借");
			recordRepository.save(record);
			msg.setMsg("借书成功!");
			return msg;
		} else {
			msg.setMsg("无该图书!");
			return msg;
		}
	}
	
	/**
	 * 通过用户名模糊匹配查询所有的借书用户	
	 */
	@Override
	public List<Record> findByLike(String uName) {
		return recordRepository.findAllByUNameLike("%"+uName+"%");
}

	//查询所有借书记录
	@Override
	public List<Record> findAll() {
		return recordRepository.findAll();
	}

	//更新借书记录
	@Override
	public Message updateRecord(Integer rId) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Record record = recordRepository.findById(rId).get();
		if(record!=null) {
			record.setState("已还!");
			recordRepository.save(record);
			msg.setRecord(record);
			msg.setMsg("success");
			return  msg;
		}else {
			msg.setMsg("该记录不存");
			return  msg;
		}
	}

}
