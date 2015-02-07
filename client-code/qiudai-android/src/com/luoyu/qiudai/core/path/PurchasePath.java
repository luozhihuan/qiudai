package com.luoyu.qiudai.core.path;

import com.luoyu.qiudai.util.PathUtil;

public class PurchasePath extends PathUtil {

	/** 学校里的全部做活动打折的商品 **/
	public static String URL_LOCAL_PURCHASE_QIUDAI = URL_LOCAL + "/service/purchase/purchase/purchaseqiudai";
	/** 学校里的全部做活动打折的商品 **/
	public static String URL_SERVER_PURCHASE_QIUDAI = URL_SERVER + "/service/purchase/purchase/purchaseqiudai";

	/** 学校里的全部做活动打折的商品 **/
	public static String GET_URL_PURCHASE_QIUDAI() {
		if (IS_SERVER) {
			return URL_SERVER_PURCHASE_QIUDAI;
		}
		return URL_LOCAL_PURCHASE_QIUDAI;
	}

}
