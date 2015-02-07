package com.hust.qiudai.web.db;

import java.util.Date;

/**
 * 用户db类
 * 
 * @author chuanrong
 * 
 */
public class User {
	/** 用户id **/
	private long userId;
	/** 用户邮箱 **/
	private String email;
	/** 用户密码 **/
	private String password;
	/**用户名称**/
	private String userName;
	/** 注册时间 **/
	private Date registerDate;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
