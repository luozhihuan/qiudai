package com.luoyu.qiudai.core.path;

import com.luoyu.qiudai.util.PathUtil;

public class CategoryPath extends PathUtil {

	public static String URL_LOCAL_GET_GOODS_CATEGORY_LIST_ARRAY_LIST = URL_LOCAL + "/service/category/category/goodscategorylist";

	public static String URL_SERVER_GET_GOODS_CATEGORY_LIST_ARRAY_LIST = URL_SERVER + "/service/category/category/goodscategorylist";

	/**
	 * 获取到商品分类链表
	 * @return
	 */
	public static String get_URL_GOODS_CATEGORY_LIST_ARRAY_LIST() {
		if (IS_SERVER) {
			return URL_SERVER_GET_GOODS_CATEGORY_LIST_ARRAY_LIST;
		}
		return URL_LOCAL_GET_GOODS_CATEGORY_LIST_ARRAY_LIST;
	}

	/** ********************************************* **/
	public static String URL_LOCAL_GET_GOODS_INFO_LIST_BY_CATEGORY_ID = URL_LOCAL + "/service/category/category/goodsinfolistbycategoryid";

	public static String URL_SERVER_GET_GOODS_INFO_LIST_BY_CATEGORY_ID = URL_SERVER + "/service/category/category/goodsinfolistbycategoryid";
	/**
	 * 根据商品种类id获取该种类下的商品信息链表
	 * @return
	 */
	public static String get_URL_GET_GOODS_INFO_LIST_BY_CATEGORY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_GET_GOODS_INFO_LIST_BY_CATEGORY_ID;
		}
		return URL_LOCAL_GET_GOODS_INFO_LIST_BY_CATEGORY_ID;
	}

	/** ********************************************* **/
	public static String URL_LOCAL_GET_GOODS_INFO_LIST = URL_LOCAL + "/service/category/category/goodsinfolist";

	public static String URL_SERVER_GET_GOODS_INFO_LIST = URL_SERVER + "/service/category/category/goodsinfolist";

	public static String get_URL_GOODS_AND_CATEGORY_INFO_LIST() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_AND_CATEGORY_INFO_LIST;
		}
		return URL_LOCAL_GOODS_AND_CATEGORY_INFO_LIST;
	}

	/** ********************************************* **/
	public static String URL_LOCAL_GOODS_AND_CATEGORY_INFO_LIST = URL_LOCAL + "/service/category/category/goodsandcategorylist";

	public static String URL_SERVER_GOODS_AND_CATEGORY_INFO_LIST = URL_SERVER + "/service/category/category/goodsandcategorylist";

	public static String get_URL_GOODS_INFO_LIST() {
		if (IS_SERVER) {
			return URL_SERVER_GET_GOODS_INFO_LIST;
		}
		return URL_LOCAL_GET_GOODS_INFO_LIST;
	}

	/** ********************************************* **/
	public static String URL_LOCAL_GOODS_KIND_CATEGORY_LIST = URL_LOCAL + "/service/category/category/goodskindcategorylist";

	public static String URL_SERVER_GOODS_KIND_CATEGORY_LIST = URL_SERVER + "/service/category/category/goodskindcategorylist";
	/**
	 * 商品大类分类链表
	 * @return
	 */
	public static String get_URL_GOODS_KIND_CATEGORY_LIST() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_KIND_CATEGORY_LIST;
		}
		return URL_LOCAL_GOODS_KIND_CATEGORY_LIST;
	}

}
