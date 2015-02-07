package com.hust.qiudai.web.db;

public class Location {
	
	public Location(double longitude,double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	private double longitude;
	
	private double latitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
