package com.hust.qiudai.web.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.web.db.Campus;
import com.hust.qiudai.web.db.City;
import com.hust.qiudai.web.db.University;
import com.hust.qiudai.web.db.User;

public interface IRegisterDao {

	/**
	 * 检测该邮箱是否已经注册
	 * 
	 * @return 返回0 表示没有，返回值大于0表示已经注册
	 */
	public int findNumOfTheUser(String email);

	/**
	 * 添加用户到数据库表中
	 * 
	 * @param user
	 *            用户对象
	 */
	public int addUser(User user);

	/**
	 * 获取城市的全部信息
	 * 
	 * @return
	 */
	public List<City> findCityInfo();

	/**
	 * 根据城市id获取大学的信息
	 * 
	 * @return
	 */
	public List<University> findUniversitiesInfoByCityId(int cityId);

	/**
	 * 根据用户id修改用户名称
	 * 
	 * @param username
	 * @param userid
	 */
	public void updateUserNameByUserId(@Param("username") String username, @Param("userid") long userid);

	/**
	 * 根据universityId获取校区列表
	 * 
	 * @param universityId
	 * @return
	 */
	public List<Campus> getCampusListByUniversityId(@Param("universityid") int universityId);

	/**
	 * 根据大学id，获取这个大学里全部含有打折商铺的校区链表
	 * 
	 * @param universityId
	 * @return
	 */
	public List<Campus> getCampusListHasDiscountinShopsByUniversityId(@Param("universityid") int universityId);

}
