package com.qf.book.vo;

import com.qf.book.dto.BookDto;
import com.qf.book.pojo.Kind;
import com.qf.book.pojo.Record;
import com.qf.book.pojo.User;

/**
 * 消息类：反馈前端操作数据的信息，只起到一个提示的作用
 */
public class Message {

	//
	private String msg;
	//用户
	private User user;
	//书籍信息
	private BookDto book;
	//书籍类型
	private Kind kind;
	//借书记录
	private Record record;
	
	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
