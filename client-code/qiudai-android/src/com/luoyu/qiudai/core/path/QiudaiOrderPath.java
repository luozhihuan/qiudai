package com.luoyu.qiudai.core.path;

import com.luoyu.qiudai.util.PathUtil;

public class QiudaiOrderPath extends PathUtil{

	
	public static String URL_LOCAL_QIUDAI_ORDER_RECIVING = URL_LOCAL + "/service/order/order/orderreciving";

	public static String URL_SERVER_QIUDAI_ORDER_RECIVING = URL_SERVER + "/service/order/order/orderreciving";

	/**
	 * 接单操作url
	 * @return
	 */
	public static String GET_QIUDAI_ORDER_RECIVING(){
		if (IS_SERVER) {
			return URL_SERVER_QIUDAI_ORDER_RECIVING;
		}
		return URL_LOCAL_QIUDAI_ORDER_RECIVING;
	}
	
}
