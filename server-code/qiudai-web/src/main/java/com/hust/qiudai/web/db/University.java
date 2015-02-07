package com.hust.qiudai.web.db;

/**
 * 大学DB类
 * 
 * @author chuanrong
 * 
 */
public class University {
	/** 大学Id **/
	private int universityId;
	
	/** 大学名称 **/
	private String universityName;
	
	/** 大学经度 **/
	private double universityLongitude;
	
	/** 大学纬度 **/
	private double universityLatitude;
	
	/** 大学所属城市id **/
	private int cityId;
	
	/**大学距离定位地点的坐标**/
	private double distanceFromLocation;

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public double getUniversityLongitude() {
		return universityLongitude;
	}

	public void setUniversityLongitude(double universityLongitude) {
		this.universityLongitude = universityLongitude;
	}

	public double getUniversityLatitude() {
		return universityLatitude;
	}

	public void setUniversityLatitude(double universityLatitude) {
		this.universityLatitude = universityLatitude;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public double getDistanceFromLocation() {
		return distanceFromLocation;
	}

	public void setDistanceFromLocation(double distanceFromLocation) {
		this.distanceFromLocation = distanceFromLocation;
	}

}
