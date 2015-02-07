package com.hust.qiudai.web.db.dao;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.web.db.User;

public interface IFindPasswordDao {

	/**
	 * 查询该用户表（t_qiudai_user）中字段email为传入参数的这个eamil的个数
	 * 
	 * @return
	 */
	public int findUserNumByUserEmail(@Param("email") String email);

	/**
	 * 修改该邮箱用户的登陆密码
	 * 
	 * @param email
	 *            ：邮箱地址
	 * @param password
	 *            ：密码
	 */
	public void updatePasswordOfThewEmail(@Param("email") String email,@Param("password") String password);
}
