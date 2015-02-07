package com.luoyu.qiudai.standard.controller;

import java.lang.reflect.Type;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.luoyu.qiudai.register.service.CommonService;

/**
 * Controller标准类，用于其他controller类继承
 * 含有Context对象和CommonService对象
 * @author chuanrong
 *
 */
public class StandardController {

	public Context context;
	public CommonService commonService;
	public static Gson gson = new Gson();

	public StandardController(Context context) {
		this.context = context;
		this.commonService = CommonService.getSingletonCommonService();
	}

	public static Gson getSingletonGson(){
		return gson;
	}
	
	public <T> T fromJson(String jsonContent,Type typeOfT){
		return gson.fromJson(jsonContent, typeOfT);
	}
}
