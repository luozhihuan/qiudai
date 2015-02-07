package com.luoyu.qiudai.core.model.impl;

import com.luoyu.qiudai.core.model.SelectionModel;


/**
 * 超市商铺小店实体
 * 
 * @author chuanrong
 * 
 */
public class Shops extends SelectionModel{

	/** 商铺小店id **/
	private long shopsId;
	
	/** 商铺小店名称 **/
	private String shopsName;
	
	/** 商铺小店所属校区id **/
	private int campusId;

	public long getShopsId() {
		return shopsId;
	}

	public void setShopsId(long shopsId) {
		this.shopsId = shopsId;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}
	
	@Override
	public long getselectedId() {
		return getShopsId();
	}
	
	@Override
	public String getSelectedName() {
		return getShopsName();
	}
	
	@Override
	public String getDefaultName() {
		// TODO Auto-generated method stub
		return "超市小店";
	}

}
