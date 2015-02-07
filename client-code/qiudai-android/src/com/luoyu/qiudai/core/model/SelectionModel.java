package com.luoyu.qiudai.core.model;

public abstract class SelectionModel {
	
	public int NUMBER_IS_CHOICE = 1;
	
	public int NUMBER_IS_NOT_CHOICE = 2;
	
	public abstract long getselectedId();
	
	public abstract String getSelectedName();
	
	public abstract String getDefaultName();

}
