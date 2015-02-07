package com.luoyu.qiudai.util;

import java.lang.reflect.Type;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 公共工具类
 * 
 * @author 罗智寰
 * 
 */
public class CommonUtil {

	public static Gson gson = new Gson();
	
	
	
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
	/**
	 * 当键盘弹起时，点击非键盘处可以收回键盘
	 * 
	 * @param context
	 *            键盘所在的context，即Activity
	 * @param event
	 *            触摸事件
	 */
	public static void clickOtherHideTheKeyboard(Context context, MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (((Activity) context).getCurrentFocus() != null && ((Activity) context).getCurrentFocus().getWindowToken() != null) {
				InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				manager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}
	
	
	

	/**
	 * 设置图片配置选项
	 * 
	 * @param imageLoader
	 * @param options
	 * @param context
	 */
	public static void setTheConfigOfImageOptions(ImageLoader imageLoader, DisplayImageOptions options, Context context) {
		// 获取imageLoader的实例
		imageLoader = ImageLoader.getInstance();
		// 初始化一个默认配置的imageloader
		// ImageLoaderConfiguration.createDefault(context)
		// 返回一个默认配置的配置对象ImageLoaderConfiguration
		// init方法就是将该配置进行初始化
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));

		// 进行显示的图片的各种格式DisplayImageOptions 的设置
		options = new DisplayImageOptions.Builder()
		// 是否设置为圆角，弧度为多少
				.bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true)// 设置下载的图片缓存在内存中
				.cacheOnDisc(true)// 设置下载的图片缓存在SD卡中
				.build();// 构建完成
	}

}
