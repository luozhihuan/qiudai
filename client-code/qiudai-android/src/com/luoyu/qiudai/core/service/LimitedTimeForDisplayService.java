package com.luoyu.qiudai.core.service;

import java.util.Date;

public class LimitedTimeForDisplayService {
	
	
	public static LimitedTimeForDisplayService limitedTimeForDisplayService = new LimitedTimeForDisplayService();
	
	/**
	 * 单例模式返回一个实体
	 * @return
	 */
	public static LimitedTimeForDisplayService getSingletonStance(){
		if(limitedTimeForDisplayService != null){
			return limitedTimeForDisplayService;
		}else{
			limitedTimeForDisplayService = new LimitedTimeForDisplayService();
			return limitedTimeForDisplayService;
		}
	}
	
	/**
	 * 根据订单的发布时间和商品截止送到的时长得到具体的截止时间（字符串形式）
	 * @param publishTime 订单的发布时间
	 * @param limitedTime 截止送到的时长
	 * @return 具体的截止时间
	 */
	public String getLimitedTimeForDisplay(Date publishTime, int limitedTime) {
		int publishMinutes = publishTime.getMinutes();
		int publishHours = publishTime.getHours();
		// int limitedTime = getItem(position).getDiliveryLimitedTime();

		int totalMinutes = limitedTime + publishMinutes;
		publishMinutes = totalMinutes % 60;
		if (publishMinutes != totalMinutes) {// 说明进位
			publishHours = (publishHours + 1) % 24;
		}

		StringBuffer result = new StringBuffer(publishHours + "点");
		if (0 <= publishMinutes && publishMinutes < 10) {
			result.append("0" + publishMinutes + "分");
		} else {
			result.append("" + publishMinutes + "分");
		}

		return result.toString();
	}
}
