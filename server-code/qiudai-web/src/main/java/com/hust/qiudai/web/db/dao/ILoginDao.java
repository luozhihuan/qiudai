package com.hust.qiudai.web.db.dao;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.web.db.User;

public interface ILoginDao {
	
	/**
	 * 查询t_qiudai_user的总数
	 * @return
	 */
	public User checkTheUser(@Param("email") String email);

}
