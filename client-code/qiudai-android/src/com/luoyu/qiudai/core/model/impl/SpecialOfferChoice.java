package com.luoyu.qiudai.core.model.impl;

import com.luoyu.qiudai.core.model.SelectionModel;


public class SpecialOfferChoice extends SelectionModel{

	public SpecialOfferChoice(int specialOfferChoiceId, String specialOfferChoiceName,boolean isChoice) {
		this.specialOfferChoiceId = specialOfferChoiceId;
		this.specialOfferChoiceName = specialOfferChoiceName;
		this.isChoice = isChoice;
	}

	private int specialOfferChoiceId;

	private String specialOfferChoiceName;
	
	private boolean isChoice;

	public int getSpecialOfferChoiceId() {
		return specialOfferChoiceId;
	}

	public void setSpecialOfferChoiceId(int specialOfferChoiceId) {
		this.specialOfferChoiceId = specialOfferChoiceId;
	}

	public String getSpecialOfferChoiceName() {
		return specialOfferChoiceName;
	}

	public void setSpecialOfferChoiceName(String specialOfferChoiceName) {
		this.specialOfferChoiceName = specialOfferChoiceName;
	}

	public boolean isChoice() {
		return isChoice;
	}

	public void setChoice(boolean isChoice) {
		this.isChoice = isChoice;
	}

	
	/**
	 * 由于此处返回应该是选择或者是未选择两个答案，所以我们需要手动转换一下，选择就返回1，未选择就返回0
	 */
	@Override
	public long getselectedId() {
		if(isChoice()){
			return NUMBER_IS_CHOICE;
		}else{
			return NUMBER_IS_NOT_CHOICE;
		}
	}

	@Override
	public String getSelectedName() {
		// TODO Auto-generated method stub
		return "特价活动（已选）";
	}

	@Override
	public String getDefaultName() {
		// TODO Auto-generated method stub
		return "特价活动（未选）";
	}

	
	
}
