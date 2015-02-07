package com.hust.qiudai.core.db;

import java.util.Date;

/**
 * 订单接单实体
 * @author chuanrong
 *
 */
public class QiudaiOrder {
	
	/**
	 * 订单接单id
	 */
	private long orderReceivingId;
	
	/**
	 * 求带信息订单id
	 */
	private long qiudaiInfoId;
	
	/**
	 * 订单接单者
	 */
	private long orderReceiverId;
	
	/**
	 * 接单时间
	 */
	private Date orderReceivingTime;


	public long getQiudaiInfoId() {
		return qiudaiInfoId;
	}

	public void setQiudaiInfoId(long qiudaiInfoId) {
		this.qiudaiInfoId = qiudaiInfoId;
	}


	public Date getOrderReceivingTime() {
		return orderReceivingTime;
	}

	public void setOrderReceivingTime(Date orderReceivingTime) {
		this.orderReceivingTime = orderReceivingTime;
	}

	public long getOrderReceivingId() {
		return orderReceivingId;
	}

	public void setOrderReceivingId(long orderReceivingId) {
		this.orderReceivingId = orderReceivingId;
	}

	public long getOrderReceiverId() {
		return orderReceiverId;
	}

	public void setOrderReceiverId(long orderReceiverId) {
		this.orderReceiverId = orderReceiverId;
	}

}
