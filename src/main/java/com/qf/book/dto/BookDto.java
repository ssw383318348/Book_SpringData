package com.qf.book.dto;

import com.qf.book.pojo.Book;

public class BookDto {
	
	private Integer id;
	
	private String type;
	
	private String bName;
	
	private String author;
	
	private String intro;
	
	private String address;
	
	
	public BookDto() {
	}

	public BookDto(Book book) {
		this.id = book.getId();
		this.type = book.getKind().getType();
		this.bName = book.getbName();
		this.author = book.getAuthor();
		this.intro = book.getIntro();
		this.address = book.getAddress();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
