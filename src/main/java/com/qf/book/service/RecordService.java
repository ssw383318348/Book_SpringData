package com.qf.book.service;

import com.qf.book.vo.Message;
import com.qf.book.pojo.Record;

import java.util.List;
//
public interface RecordService {

	//借书的信息
	Message borrow(Record record);
	//通过书名查询借书记录
	List<Record> findByLike(String uName);
	//查询所有借书记录
	List<Record> findAll();
	//通过Id更新借书记录
	Message updateRecord(Integer rId);

}
