package com.luoyu.qiudai.core.model.impl;

public class SpecialOffer {

	public SpecialOffer() {

	}

	public SpecialOffer(int specialOfferId, String specialOfferName) {
		this.specialOfferId = specialOfferId;
		this.specialOfferName = specialOfferName;
	}

	private int specialOfferId;

	private String specialOfferName;

	public int getSpecialOfferId() {
		return specialOfferId;
	}

	public void setSpecialOfferId(int specialOfferId) {
		this.specialOfferId = specialOfferId;
	}

	public String getSpecialOfferName() {
		return specialOfferName;
	}

	public void setSpecialOfferName(String specialOfferName) {
		this.specialOfferName = specialOfferName;
	}

}
