package com.hust.qiudai.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.core.db.GoodsInfo;

public interface IGoodsInfoDao {

	/**
	 * 根据传入的高校id，获取该校全部打折的商品
	 * 
	 * @param universityId
	 *            高校id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findDiscountingGoodsInfoListByUniversityId(@Param("universityid") int universityId);

	/**
	 * 根据传入的商铺id，获取该商铺全部打折的商品
	 * 
	 * @param shopsId
	 *            商铺id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findDiscountingGoodsInfoListByShopsId(@Param("shopsid") long shopsId);

	/**
	 * 根据传入的高校id和商品种类id，获取该高校中该商品种类的全部打折的商品
	 * 
	 * @param universityId
	 *            高校id
	 * @param goodsCategoryId
	 *            商品种类id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findDiscountingGoodsInfoListByUniversityIdGoodsCategoryId(@Param("universityid") long universityId,
			@Param("goodscategoryid") int goodsCategoryId);

	/**
	 * 根据传入的商铺id和商品种类id获取该商铺下该商品种类的全部打折商品信息
	 * 
	 * @param discountingShopsId
	 *            商铺id
	 * @param goodsCategoryId
	 *            商品种类id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findDiscountingGoodsInfoListByShopsIdGoodsCategoryId(@Param("shopsid") long discountingShopsId,
			@Param("goodscategoryid") int goodsCategoryId);

	/***************************** 不打折 ************************************/

	/**
	 * 根据传入的高校id，获取该校全部的商品
	 * 
	 * @param universityId
	 *            高校id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findGoodsInfoListByUniversityId(@Param("universityid")int universityId);

	/**
	 * 根据传入的商铺id，获取该商铺全部的商品
	 * 
	 * @param shopsId
	 *            商铺id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findGoodsInfoListByShopsId(@Param("shopsid")int shopsId);

	/**
	 * 根据传入的高校id和商品种类id，获取该高校中该商品种类的全部的商品
	 * 
	 * @param universityId
	 *            高校id
	 * @param goodsCategoryId
	 *            商品种类id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findGoodsInfoListByUniversityIdGoodsCategoryId(@Param("universityid") long universityId, @Param("goodscategoryid") int goodsCategoryId);

	/**
	 * 根据传入的商铺id和商品种类id获取该商铺下该商品种类的全部商品信息
	 * 
	 * @param shopsId
	 *            商铺id
	 * @param goodsCategoryId
	 *            商品种类id
	 * @return 商品信息链表
	 */
	List<GoodsInfo> findGoodsInfoListByShopsIdGoodsCategoryId(@Param("shopsid") long shopsId, @Param("goodscategoryid") int goodsCategoryId);

}
