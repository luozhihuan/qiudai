package com.luoyu.qiudai.core.path;

import com.luoyu.qiudai.util.PathUtil;

public class ShopsPath extends PathUtil {

	public static String URL_LOCAL_SHOPS_LIST_BY_UNIVERSITY_ID = URL_LOCAL + "/service/shops/shops/shopslistbyuniversityid";

	public static String URL_SERVER_SHOPS_LIST_BY_UNIVERSITY_ID = URL_SERVER + "/service/shops/shops/shopslistbyuniversityid";

	public static String get_URL_SHOPS_LIST_BY_UNIVERSITY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_SHOPS_LIST_BY_UNIVERSITY_ID;
		}
		return URL_LOCAL_SHOPS_LIST_BY_UNIVERSITY_ID;
	}

	/***************************************************************/
	public static String URL_LOCAL_SHOPS_LIST_HAS_DISCOUNTING_GOODS_BY_UNIVERSITY_ID = URL_LOCAL
			+ "/service/shops/shops/shopslisthasdiscountingoodsbyuniversityid";

	public static String URL_SERVER_SHOPS_LIST_HAS_DISCOUNTING_GOODS_BY_UNIVERSITY_ID = URL_SERVER
			+ "/service/shops/shops/shopslisthasdiscountingoodsbyuniversityid";

	public static String get_URL_SHOPS_LIST_HAS_DISCOUNTING_GOODS_BY_UNIVERSITY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_SHOPS_LIST_HAS_DISCOUNTING_GOODS_BY_UNIVERSITY_ID;
		}
		return URL_LOCAL_SHOPS_LIST_HAS_DISCOUNTING_GOODS_BY_UNIVERSITY_ID;
	}

	/***************************************************************/
	public static String URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID = URL_LOCAL + "/service/shops/shops/goodsinfolistbyshopsid";

	public static String URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID = URL_SERVER + "/service/shops/shops/goodsinfolistbyshopsid";

	public static String get_URL_GOODS_INFO_LIST_BY_SHOPS_ID() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID;
		}
		return URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID;
	}

	/***************************************************************/
	public static String URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID = URL_LOCAL + "/service/shops/shops/discountinggoodsinfolistbyshopsid";

	public static String URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID = URL_SERVER + "/service/shops/shops/discountinggoodsinfolistbyshopsid";

	public static String get_URL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID() {
		if (IS_SERVER) {
			return URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID;
		}
		return URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID;
	}

}
