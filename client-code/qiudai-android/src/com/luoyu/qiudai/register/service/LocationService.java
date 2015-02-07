package com.luoyu.qiudai.register.service;

import android.content.Context;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.luoyu.listener.QiuDaiLocationListenner;

public class LocationService {

	private static QiuDaiLocationListenner listenner;

	public void start(Context context) {
		listenner = new QiuDaiLocationListenner();
		// 定位初始化
		LocationClient mLocClient = new LocationClient(context);
		mLocClient.registerLocationListener(listenner);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(8000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	public static Double getLatitude() {
		if(listenner == null){
			return null;
		}
		return listenner.getLatitude();
	}

	public static Double getLongitude() {
		if(listenner == null){
			return null;
		}
		return listenner.getLongitude();
	}

}
