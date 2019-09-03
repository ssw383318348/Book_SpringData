package com.qf.book.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 图书种类
 * 
 */
@Entity
@Table(name = "kind")
public class Kind implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Integer id;
	
	/**
	 * 图书的分类
	 */
	@Column(length = 20)
	private String type;
	
	/**
	 * 与书一对多，mappedBy表示放弃对关系的维护
	 */
	@OneToMany(mappedBy="kind")
	private List<Book> books;

	public Kind() {
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
