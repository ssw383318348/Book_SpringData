package com.qf.book.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 借书记录
 */
@Entity
@Table(name = "record")
public class Record implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录的id
	 */
	@Id
	@Column(length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 借书用户的id
	 */
	@Column(length = 20)
	private String uName;
	
	/**
	 * 借出的书名
	 */
	@Column(length = 20)
	private String bName;
	
	/**
	 * 借书日期
	 * 设置日期格式，不设置将java中的日期导入数据库中会出错
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  borrowDate;
	
	/**
	 * 还书日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
	
	/**
	 * 借书的转态
	 */
	@Column(length = 20)
	private String state;

	public Record() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
