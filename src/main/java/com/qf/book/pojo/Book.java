package com.qf.book.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 图书类
 *
 */

@Entity//定义为实体
@Table(name = "t_book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 * 设置主键自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Integer id;

	/**
	 * 书名
	 */
	@Column(length = 20)
	private String bName;

	/**
	 * 作者
	 */
	@Column(length = 20)
	private String author;

	/**
	 * 说明
	 */
	@Column(length = 100)
	private String intro;

	/**
	 * 图片url
	 */
	@Column(length = 100)
	private String address;
	
	/**
	 *与种类多对一 
	 */
	@ManyToOne
	@JoinColumn(name="kind_id") //name表示在Book表中的外键值
	private Kind kind;

	public Book() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
