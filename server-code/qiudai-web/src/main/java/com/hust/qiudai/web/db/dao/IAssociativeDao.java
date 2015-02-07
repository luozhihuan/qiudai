package com.hust.qiudai.web.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.web.db.University;

public interface IAssociativeDao {

	/**
	 * 根据词语获取联系词学校信息
	 * @param word
	 * @return
	 */
	List<University> findAssociativeUniversities(@Param("word")String word);
	
	

}
