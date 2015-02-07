package com.hust.qiudai.core.db.dao;

import com.hust.qiudai.core.db.GoodsIdAndBuyNum;
import com.hust.qiudai.core.db.QiudaiInfo;

public interface IPurchaseDao {

	/**
	 * 添加求带信息
	 * @param qiudaiInfo 求带信息
	 */
	public void addQiudaiInfo(QiudaiInfo qiudaiInfo);

	
	/**
	 * 添加求带商品购买信息
	 * @param goodsIdAndBuyNum 求带商品购买信息
	 */
	public void addQiudaiGoodsBuyInfo(GoodsIdAndBuyNum goodsIdAndBuyNum);

}
