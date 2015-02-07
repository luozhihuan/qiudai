package com.luoyu.qiudai.register.service;

/**
 * 密码检测
 * @author chuanrong
 *
 */
public class PasswordCheckService {
	/**
	 *检测密码是否过于简单 
	 * @param password 密码
	 * @return 简单则返回false，提示密码过于简单
	 */
	public boolean checkPassword(String password)
	{
		if(password == null || password.length() == 0){
			return false;
		}
		return true;
	}
}
