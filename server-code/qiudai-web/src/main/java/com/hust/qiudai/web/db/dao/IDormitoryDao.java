package com.hust.qiudai.web.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.web.db.Campus;
import com.hust.qiudai.web.db.Dormitory;

public interface IDormitoryDao {
	
	/**
	 *存储宿舍号至用户信息中 
	 */
	public void saveTheDormitoryNumberDaoToUser(@Param("dormitoryNum")String dormitoryNum,@Param("userId")long userId);

	
	
	/**
	 * 通过campusid获取宿舍楼列表
	 * @param campusid
	 * @return
	 */
	public List<Dormitory> getDormitoryListByCampusId(@Param("campusid")int campusid);

	
	
	
}
