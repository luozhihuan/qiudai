package com.hust.qiudai.web.db;

public class Dormitory {
	
	/**宿舍楼id**/
	private int dormibuildingid;
	
	/**宿舍楼名字**/
	private String dormibuildingname;
	
	/**校区id**/
	private int campusid;
	
	/**宿舍楼纬度**/
	private double dormibuildinglatitude;
	
	/**宿舍楼经度**/
	private double dormibuildinglongitude;

	public int getDormibuildingid() {
		return dormibuildingid;
	}

	public void setDormibuildingid(int dormibuildingid) {
		this.dormibuildingid = dormibuildingid;
	}

	public String getDormibuildingname() {
		return dormibuildingname;
	}

	public void setDormibuildingname(String dormibuildingname) {
		this.dormibuildingname = dormibuildingname;
	}

	public int getCampusid() {
		return campusid;
	}

	public void setCampusid(int campusid) {
		this.campusid = campusid;
	}

	public double getDormibuildinglatitude() {
		return dormibuildinglatitude;
	}

	public void setDormibuildinglatitude(double dormibuildinglatitude) {
		this.dormibuildinglatitude = dormibuildinglatitude;
	}

	public double getDormibuildinglongitude() {
		return dormibuildinglongitude;
	}

	public void setDormibuildinglongitude(double dormibuildinglongitude) {
		this.dormibuildinglongitude = dormibuildinglongitude;
	}

}
