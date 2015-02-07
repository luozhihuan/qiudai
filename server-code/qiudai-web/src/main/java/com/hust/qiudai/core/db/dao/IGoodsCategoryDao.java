package com.hust.qiudai.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.core.db.GoodsCategory;
import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.GoodsKindCategory;

public interface IGoodsCategoryDao {

	/**
	 * 获取商品类别链表
	 * 
	 * @return
	 */
	public List<GoodsCategory> getGoodsCategoryList(@Param("kindcategoryid") int kindCategoryId);

	/**
	 * 根据商品类别id获取该类别下的全部商品的链表
	 * 
	 * @param goodsCategoryId
	 *            商品种类id
	 * @return 商品信息链表
	 */
	public List<GoodsInfo> findGoodsInfoListByGoodsCategoryId(@Param("categoryid") int goodsCategoryId);

	/**
	 * 获取商品的链表
	 * @return 商品信息链表
	 */
	public List<GoodsInfo> findGoodsInfoList();

	/**
	 * 获取商品大类分类链表
	 * @return 商品大类分类链表
	 */
	public List<GoodsKindCategory> findGoodsKindCategoryList();

}
