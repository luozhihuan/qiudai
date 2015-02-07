package com.luoyu.qiudai.core.path;

import com.luoyu.qiudai.util.PathUtil;

public class GoodsInfoPath extends PathUtil {

	/** 学校里的全部做活动打折的商品 **/
	public static String URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID = URL_LOCAL
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyuniversityid";
	/** 学校里的全部做活动打折的商品 **/
	public static String URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID = URL_SERVER
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyuniversityid";

	/** 学校里的全部做活动打折的商品 **/
	public static String GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID;
		}
		return URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID;
	}

	/***********************************************************************************************/
	/** 某个商铺打折的商品 **/
	public static String URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID = URL_LOCAL
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyshopsid";
	/** 某个商铺打折的商品 **/
	public static String URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID = URL_SERVER
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyshopsid";

	/** 某个商铺打折的商品 **/
	public static String GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID() {
		if (IS_SERVER) {
			return URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID;
		}
		return URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID;
	}

	/***********************************************************************************************/

	/** 学校里的某个商品种类做活动打折的商品 **/
	public static String URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID = URL_LOCAL
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyuniversityidandcategoryid";
	/** 学校里的某个商品种类做活动打折的商品 **/
	public static String URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID = URL_SERVER
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyuniversityidandcategoryid";

	/** 学校里的某个商品种类做活动打折的商品 **/
	public static String GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID;
		}
		return URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID;
	}

	/***********************************************************************************************/
	/** 某个商铺某个种类做活动打折的商品 **/
	public static String URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID = URL_LOCAL
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyshopsidandcategoryid";
	/** 某个商铺某个种类做活动打折的商品 **/
	public static String URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID = URL_SERVER
			+ "/service/goodsinfo/goodsinfo/getdiscountinggoodsinfolistbyshopsidandcategoryid";

	/** 某个商铺某个种类做活动打折的商品 **/
	public static String GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID;
		}
		return URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID;
	}

	/***********************************************************************************************/
	/** 学校里的全部的商品 **/
	public static String URL_LOCAL_GOODS_INFO_LIST_BY_UNIVERSITY_ID = URL_LOCAL + "/service/goodsinfo/goodsinfo/getgoodsinfolistbyuniversityid";
	/** 学校里的全部的商品 **/
	public static String URL_SERVER_GOODS_INFO_LIST_BY_UNIVERSITY_ID = URL_SERVER + "/service/goodsinfo/goodsinfo/getgoodsinfolistbyuniversityid";

	/** 学校里的全部的商品 **/
	public static String GET_URL_GOODS_INFO_LIST_BY_UNIVERSITY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_INFO_LIST_BY_UNIVERSITY_ID;
		}
		return URL_LOCAL_GOODS_INFO_LIST_BY_UNIVERSITY_ID;
	}

	/***********************************************************************************************/
	/** 某个商铺的商品 **/
	public static String URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID = URL_LOCAL + "/service/goodsinfo/goodsinfo/getgoodsinfolistbyshopsid";
	/** 某个商铺的商品 **/
	public static String URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID = URL_SERVER + "/service/goodsinfo/goodsinfo/getgoodsinfolistbyshopsid";

	/** 某个商铺的商品 **/
	public static String GET_URL_GOODS_INFO_LIST_BY_SHOPS_ID() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID;
		}
		return URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID;
	}

	/***********************************************************************************************/
	/** 学校里的某个商品种类的商品 **/
	public static String URL_LOCAL_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID = URL_LOCAL
			+ "/service/goodsinfo/goodsinfo/getgoodsinfolistbyuniversityidandcategoryid";
	/** 学校里的某个商品种类的商品 **/
	public static String URL_SERVER_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID = URL_SERVER
			+ "/service/goodsinfo/goodsinfo/getgoodsinfolistbyuniversityidandcategoryid";

	/** 学校里的某个商品种类的商品 **/
	public static String GET_URL_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID;
		}
		return URL_LOCAL_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID;
	}

	/***********************************************************************************************/
	/** 某个商铺某个种类的商品 **/
	public static String URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID = URL_LOCAL
			+ "/service/goodsinfo/goodsinfo/getgoodsinfolistbyshopsidandcategoryid";
	/** 学校里的某个商品种类的商品 **/
	public static String URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID = URL_SERVER
			+ "/service/goodsinfo/goodsinfo/getgoodsinfolistbyshopsidandcategoryid";

	/** 学校里的某个商品种类的商品 **/
	public static String GET_URL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID;
		}
		return URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID;
	}
}
