package com.qf.book.service;

import com.qf.book.pojo.Kind;
import com.qf.book.vo.Message;

import java.util.List;

public interface KindService {

	//查询所有书籍类型
	List<Kind> findAll();
	//添加书籍类型
	Message addKind(Kind kind);
	//更新书籍类型
	Message updateKind(Kind kind);
	//通过ID删除书籍类型
	Message deleteById(Integer kId);



}
