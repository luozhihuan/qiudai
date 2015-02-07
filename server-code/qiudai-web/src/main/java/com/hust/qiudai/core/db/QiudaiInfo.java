package com.hust.qiudai.core.db;

import java.util.Date;

public class QiudaiInfo {
	
	private long qiudaiInfoId;
	
	private int deliveryLimitedTime;
	
	private Date publishTime;
	
	private String publisAddress;
	
	private double fee;
	
	private long publisherId;
	
	private int publisherDorimitoryBuildingId;
	
	private String supply;

	public long getQiudaiInfoId() {
		return qiudaiInfoId;
	}

	public void setQiudaiInfoId(long qiudaiInfoId) {
		this.qiudaiInfoId = qiudaiInfoId;
	}

	public int getDeliveryLimitedTime() {
		return deliveryLimitedTime;
	}

	public void setDeliveryLimitedTime(int deliveryLimitedTime) {
		this.deliveryLimitedTime = deliveryLimitedTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublisAddress() {
		return publisAddress;
	}

	public void setPublisAddress(String publisAddress) {
		this.publisAddress = publisAddress;
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

	public int getPublisherDorimitoryBuildingId() {
		return publisherDorimitoryBuildingId;
	}

	public void setPublisherDorimitoryBuildingId(int publisherDorimitoryBuildingId) {
		this.publisherDorimitoryBuildingId = publisherDorimitoryBuildingId;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

}
