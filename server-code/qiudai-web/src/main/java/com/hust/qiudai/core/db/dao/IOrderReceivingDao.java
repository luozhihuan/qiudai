package com.hust.qiudai.core.db.dao;

import org.apache.ibatis.annotations.Param;

import com.hust.qiudai.core.db.QiudaiOrder;

public interface IOrderReceivingDao {

	
	/**
	 * 提价求带接单信息
	 * @param qiudaiOrder
	 */
	public void addQiudaiOrder(QiudaiOrder qiudaiOrder);

	/**
	 * 将该求带信息订单的isReceivied字段改为1，表示该订单已被接
	 * @param qiudaiInfoId
	 */
	public void updateTheQiudaiInfoToBeReceivied(@Param("qiudaiinfoid")long qiudaiInfoId);

}
