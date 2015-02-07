package com.hust.qiudai.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.QiudaiDisplayInfo;

public interface IQiudaiDisplayInfoDao {

	/**
	 * 从数据库中获取求带信息展示链表
	 * @return 求带信息展示链表
	 */
	public List<QiudaiDisplayInfo> getQiudaiDisplayInfoList();

	
	
	
	/**
	 * 通过求带信息id获取到该求带订单中的商品链表
	 * @param qiudaiInfoId 求带信息id
	 */
	public List<GoodsInfo> getQiudaiGoodsInfoList(@Param("qiudaiinfoid")long qiudaiInfoId);
	

}
