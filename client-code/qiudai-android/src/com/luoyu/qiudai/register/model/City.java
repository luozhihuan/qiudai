package com.luoyu.qiudai.register.model;


/**
 * 城市db类
 * 
 * @author chuanrong
 * 
 */
public class City {
	
	/** 城市id **/
	private int cityId;
	
	/** 城市名称 **/
	private String cityName;
	
	/** 城市的坐标经度 **/
	private double cityLongitude;
	
	/** 城市的坐标纬度 **/
	private double cityLatitude;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getCityLongitude() {
		return cityLongitude;
	}

	public void setCityLongitude(double cityLongitude) {
		this.cityLongitude = cityLongitude;
	}

	public double getCityLatitude() {
		return cityLatitude;
	}

	public void setCityLatitude(double cityLatitude) {
		this.cityLatitude = cityLatitude;
	}


}
