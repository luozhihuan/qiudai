package com.luoyu.qiudai.core.model.impl;

import java.util.List;
/**
 * 单个求带信息展示类
 * @author chuanrong
 *
 */
public class SingleQiudaiInfo {
	

	/**
	 * 求带展示信息的主键id
	 */
	private long qiudaiDisplayInfoId;
	
	/**
	 * 送货截止时间
	 */
	private String diliveryLimitedTime;
	
	/**
	 * 信息发布者地址
	 */
	private String publisherAddress;
	
	/**
	 * 跑路费
	 */
	private double fee;
	
	/**
	 * 信息发布者id
	 */
	private long publisherId;
	
	/**
	 * 信息发布者的头像图片地址
	 */
	private String publisherHeadImgUrl;
	
	/**
	 * 信息发布者的网名
	 */
	private String publisherNickName;
	
	/**
	 * 信息发布者电话号码
	 */
	private String publisherPhone;
	
	/**
	 * 信息发布者请求带的商品链表
	 */
	private List<GoodsInfo> qiuidaiGoodsInfoList;

	public long getQiudaiDisplayInfoId() {
		return qiudaiDisplayInfoId;
	}

	public void setQiudaiDisplayInfoId(long qiudaiDisplayInfoId) {
		this.qiudaiDisplayInfoId = qiudaiDisplayInfoId;
	}

	public String getDiliveryLimitedTime() {
		return diliveryLimitedTime;
	}

	public void setDiliveryLimitedTime(String diliveryLimitedTime) {
		this.diliveryLimitedTime = diliveryLimitedTime;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(long publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherHeadImgUrl() {
		return publisherHeadImgUrl;
	}

	public void setPublisherHeadImgUrl(String publisherHeadImgUrl) {
		this.publisherHeadImgUrl = publisherHeadImgUrl;
	}

	public String getPublisherNickName() {
		return publisherNickName;
	}

	public void setPublisherNickName(String publisherNickName) {
		this.publisherNickName = publisherNickName;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public List<GoodsInfo> getQiuidaiGoodsInfoList() {
		return qiuidaiGoodsInfoList;
	}

	public void setQiuidaiGoodsInfoList(List<GoodsInfo> qiuidaiGoodsInfoList) {
		this.qiuidaiGoodsInfoList = qiuidaiGoodsInfoList;
	}

}
