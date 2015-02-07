package com.luoyu.listener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.MyLocationData;

/**
 * 封装了百度定位的监听器
 * 
 * @author chuanrong
 * 
 */
public class QiuDaiLocationListenner implements BDLocationListener {

	private double latitude;

	private double longitude;

	// public QiuDaiLocationListenner(double latitude,double longitude){
	// this.latitude = latitude;
	// this.longitude = longitude;
	// }

	@Override
	public void onReceiveLocation(BDLocation location) {
		// map view 销毁后不在处理新接收的位置
		if (location == null) {
			return;
		}
		MyLocationData locData = new MyLocationData.Builder()
				.accuracy(location.getRadius())
				// 此处设置开发者获取到的方向信息，顺时针0-360
				.direction(100).latitude(location.getLatitude())
				.longitude(location.getLongitude()).build();

		latitude = location.getLatitude();

		longitude = location.getLongitude();

		// 此处获取坐标
		System.out.println("坐标1：" + location.getLatitude() + ";"
				+ location.getLongitude());
		System.out.println("坐标2：" + locData.latitude + ";" + locData.longitude);
	}

	@Override
	public void onReceivePoi(BDLocation poiLocation) {
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
