package com.hust.qiudai.web.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hust.qiudai.core.db.GoodsCategory;

public class CommonUtil {
	
	public static Gson gson = new Gson();
	
	/**
	 * 获取HttpServletRequest对象
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		return request;
	}
	
	/**
	 * 使用Gson将对象转换为一个json格式的字符串
	 * @param obj
	 * @return json格式字符串
	 */
	public static String getJsonStringByGson(Object obj){
		return gson.toJson(obj);
	}
	
	/**
	 * 使用Gson将typeOfObj这个类型的ibj对象转换为json格式的字符串
	 * @param obj
	 * @param typeOfObj
	 * @return json格式字符串
	 */
	public static String getJsonStringByGson(Object obj,Type typeOfObj){
		return gson.toJson(obj, typeOfObj);
	}
	
	public static  <T> T fromJson(String jsonContent,Type typeOfT){
		return gson.fromJson(jsonContent, typeOfT);
	}
	

}
