package com.qf.book.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户类s
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@Id
	@Column(length = 20)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 登录名
	 */
	@Column(length = 20)
	private String uName;

	/**
	 * 登录密码
	 */
	@Column(length = 50)
	private String uPwd;
	
	/**
	 * 姓名
	 */
	@Column(length = 20)
	private String name;
	
	/**
	 * 手机号
	 */
	@Column(length = 11)
	private String phone;
	
	/**
	 * email
	 */
	@Column(length = 20)
	private String email;

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

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [" + (id != null ? "id=" + id + ", " : "") + (uName != null ? "uName=" + uName + ", " : "")
				+ (uPwd != null ? "uPwd=" + uPwd + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (phone != null ? "phone=" + phone + ", " : "") + (email != null ? "email=" + email : "") + "]";
	}

}
