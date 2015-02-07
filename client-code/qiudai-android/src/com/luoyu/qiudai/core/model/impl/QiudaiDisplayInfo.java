package com.luoyu.qiudai.core.model.impl;

import java.util.Date;


/**
 * 用户发布的求带信息展现类
 * @author chuanrong
 *
 */
public class QiudaiDisplayInfo {
	
	
	/**
	 * 求带展示信息的主键id
	 */
	private long qiudaiDisplayInfoId;
	
	/**
	 * 送货截止时间
	 */
	private int diliveryLimitedTime;
	
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
	 * 求带信息的发布时间
	 */
	private Date publishTime;
	
	/**
	 * 发布者名称
	 */
	private String publisherName;
	
	/**
	 * 电话号码
	 */
	private String publisherPhoneNumber;

	public long getQiudaiDisplayInfoId() {
		return qiudaiDisplayInfoId;
	}

	public void setQiudaiDisplayInfoId(long qiudaiDisplayInfoId) {
		this.qiudaiDisplayInfoId = qiudaiDisplayInfoId;
	}

	public int getDiliveryLimitedTime() {
		return diliveryLimitedTime;
	}

	public void setDiliveryLimitedTime(int diliveryLimitedTime) {
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherPhoneNumber() {
		return publisherPhoneNumber;
	}

	public void setPublisherPhoneNumber(String publisherPhoneNumber) {
		this.publisherPhoneNumber = publisherPhoneNumber;
	}

	
	

}
