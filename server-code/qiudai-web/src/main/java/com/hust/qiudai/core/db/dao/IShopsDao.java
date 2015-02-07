package com.hust.qiudai.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.Shops;
import com.hust.qiudai.web.db.Campus;

public interface IShopsDao {

	/**
	 * 根据高校id查找出该高校内的全部商铺
	 * 
	 * @param universityId
	 *            高校id
	 * @return 商铺链表
	 */
	public List<Shops> findShopsListByUniversityId(@Param("univeristyid") int universityId);

	/**
	 * 根据高校id查找出该高校内的全部有打折商品的商铺
	 * 
	 * @param universityId
	 *            高校id
	 * @return 商铺链表
	 */
	public List<Shops> findShopsListHasDiscountinGoodsByUniversityId(@Param("univeristyid") int universityId);

	/**
	 * 根据商铺id获取该商铺下的全部商品
	 * 
	 * @param shopsid
	 * @return
	 */
	public List<GoodsInfo> findGoodsInfoListByShopsId(@Param("shopsid") long shopsid);

	/**
	 * 根据传入的商铺id获取该商铺的全部打折商品信息
	 */
	public List<GoodsInfo> findDiscountingGoodsInfoListByShopsId(@Param("shopsid") long shopsid);

	
	/**
	 * 根据传入的高校id获取校区链表
	 * @param universityId 高校id
	 * @return 校区链表
	 */
	public List<Campus> findCampusListByUniversityId(int universityId);

}
