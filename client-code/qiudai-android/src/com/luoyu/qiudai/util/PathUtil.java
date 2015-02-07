package com.luoyu.qiudai.util;

import com.luoyu.qiudai.core.path.CategoryPath;
import com.luoyu.qiudai.core.path.GoodsInfoPath;
import com.luoyu.qiudai.core.path.PurchasePath;
import com.luoyu.qiudai.core.path.QiudaiOrderPath;
import com.luoyu.qiudai.core.path.ShopsPath;

public class PathUtil {

	public static boolean IS_SERVER = true;
	
	public static GoodsInfoPath GOODS_INFO_PATH;
	
	public static CategoryPath CATEGORY_PATH;
	
	public static ShopsPath SHOPS_PATH;
	
	public static PurchasePath PURCHASE_PATH;
	
	public static QiudaiOrderPath QIUDAI_ORDER_PATH;
	
	// public static String URL_LOCAL =
	// "http://192.168.1.101:8080/qiudai-web   ";
	public static String URL_LOCAL = "http://192.168.191.1:8080/qiudai-web";

	public static String URL_SERVER = "http://121.40.185.83:8080/qiudai-web";

	public static String URL_LOCAL_PUBLISH = URL_LOCAL + "/service/message/message/message";

	public static String URL_SERVER_PUBLISH = URL_SERVER + "/service/message/message/message";

	public static String URL_LOCAL_LOGIN = URL_LOCAL + "/LoginServlet";

	public static String URL_SERVER_LOGIN = URL_SERVER + "/service/login/login/login";

	public static String URL_LOCAL_REGISTER = URL_LOCAL + "/service/register/register/register";

	public static String URL_SERVER_REGISTER = URL_SERVER + "/service/register/register/register";

	public static String URL_LOCAL_GET_QIUDAIINFO = URL_LOCAL + "/service/message/message/qiudaiinfolist";

	public static String URL_SERVER_GET_QIUDAIINFO = URL_SERVER + "/service/message/message/qiudaiinfolist";

	public static String URL_LOCAL_PHOTO = URL_LOCAL + "/image/ouyang.jpg";

	public static String URL_SERVER_PHOTO = URL_SERVER + "/image/ouyang.jpg";

	public static String URL_LOCAL_GOODS_URL = URL_LOCAL + "/service/goods/goods/goodsinfolist";

	public static String URL_SERVER_GOODS_URL = URL_SERVER + "/service/goods/goods/goodsinfolist";

	public static String URL_LOCAL_USERNAME_COMMIT = URL_LOCAL + "/service/register/register/username";

	public static String URL_SERVER_USERNAME_COMMIT = URL_SERVER + "/service/register/register/username";

	public static String URL_LOCAL_ASSOCIATIVE_UNIVERSITIES = URL_LOCAL + "/service/associative/associative/universities";

	public static String URL_SERVER_ASSOCIATIVE_UNIVERSITIES = URL_SERVER + "/service/associative/associative/universities";

	public static String URL_LOCAL_CAMPUS_LIST_BY_UNIVERSITY_ID = URL_LOCAL + "/service/register/campus/campuslistbyuniversityid";

	public static String URL_SERVER_CAMPUS_LIST_BY_UNIVERSITY_ID = URL_SERVER + "/service/register/campus/campuslistbyuniversityid";

	public static String URL_LOCAL_DORMIBUILDING_LIST = URL_LOCAL + "/service/dormitory/dormitory/dormitoryList";

	public static String URL_SERVER_DORMIBUILDING_LIST = URL_SERVER + "/service/dormitory/dormitory/dormitoryList";

	public static String URL_LOCAL_UPLOAD_EMAIL_TO_SERVER = URL_LOCAL + "/service/findPassword/findPassword/findPassword";

	public static String URL_SERVER_UPLOAD_EMAIL_TO_SERVER = URL_SERVER + "/service/findPassword/findPassword/findPassword";

	public static String URL_LOCAL_UPLOAD_DORMITORY_NUMBER_TO_SERVER = URL_LOCAL + "/service/dormitory/dormitory/uploadDormNumber";

	public static String URL_SERVER_UPLOAD_DORMITORY_NUMBER_TO_SERVER = URL_SERVER + "/service/dormitory/dormitory/uploadDormNumber";

	public static String URL_LOCAL_QIUDAI_DISPLAY_INFO_LIST = URL_LOCAL + "/service/qiudaiinfo/qiudaiinfo/qiudaidisplayinfolist";

	public static String URL_SERVER_QIUDAI_DISPLAY_INFO_LIST = URL_SERVER + "/service/qiudaiinfo/qiudaiinfo/qiudaidisplayinfolist";

	public static String URL_LOCAL_GET_QIUDAI_GOODS_INFO_LIST = URL_LOCAL + "/service/qiudaiinfo/qiudaiinfo/goodsinfolist";

	public static String URL_SERVER_GET_QIUDAI_GOODS_INFO_LIST = URL_SERVER + "/service/qiudaiinfo/qiudaiinfo/goodsinfolist";




	public static String URL_LOCAL_CAMPUS_LIST_HAS_DISCOUNTING_SHOPS_BY_UNIVERSITY_ID = URL_LOCAL
			+ "/service/register/campus/campuslisthasdiscotinshopsbyuniversityid";

	public static String URL_SERVER_CAMPUS_LIST_HAS_DISCOUNTING_SHOPS_BY_UNIVERSITY_ID = URL_SERVER
			+ "/service/register/campus/campuslisthasdiscotinshopsbyuniversityid";

	
	





	public static String URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID = URL_LOCAL + "/service/goodsinfo/goodsinfo/discountinggoodsinfolistbyshopsidandgoodscategoryid";

	public static String URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID = URL_SERVER + "/service/goodsinfo/goodsinfo/discountinggoodsinfolistbyshopsidandgoodscategoryid";

	
	public static String URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID = URL_LOCAL + "/service/goodsinfo/goodsinfo/goodsinfolistbyshopsidandgoodscategoryid";

	public static String URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID = URL_SERVER + "/service/goodsinfo/goodsinfo/goodsinfolistbyshopsidandgoodscategoryid";

	
	
	public static String getUrl() {
		if (IS_SERVER) {
			return URL_SERVER;
		}
		return URL_LOCAL;
	}

	public static String getPublishUrl() {
		if (IS_SERVER) {
			return URL_SERVER_PUBLISH;
		}
		return URL_LOCAL_PUBLISH;
	}

	public static String getLoginUrl() {
		if (IS_SERVER) {
			return URL_SERVER_LOGIN;
		}
		return URL_LOCAL_LOGIN;
	}

	public static String getRegisterUrl() {
		if (IS_SERVER) {
			return URL_SERVER_REGISTER;
		}
		return URL_LOCAL_REGISTER;
	}

	public static String getQiudaiInfoUrl() {
		if (IS_SERVER) {
			return URL_SERVER_GET_QIUDAIINFO;
		}
		return URL_LOCAL_GET_QIUDAIINFO;
	}

	public static String getPhotoUrl() {
		if (IS_SERVER) {
			return URL_SERVER_PHOTO;
		}
		return URL_LOCAL_PHOTO;
	}

	public static String getGoodsUrl() {
		if (IS_SERVER) {
			return URL_SERVER_GOODS_URL;
		}
		return URL_LOCAL_GOODS_URL;
	}

	public static String getUsernameCommitUrl() {
		if (IS_SERVER) {
			return URL_SERVER_USERNAME_COMMIT;
		}
		return URL_LOCAL_USERNAME_COMMIT;
	}

	public static String getAssociativeUniversities() {
		if (IS_SERVER) {
			return URL_SERVER_ASSOCIATIVE_UNIVERSITIES;
		}
		return URL_LOCAL_ASSOCIATIVE_UNIVERSITIES;
	}

	public static String get_CAMPUS_LIST_BY_UNIVERSITY_ID() {
		if (IS_SERVER) {
			return URL_SERVER_CAMPUS_LIST_BY_UNIVERSITY_ID;
		}
		return URL_LOCAL_CAMPUS_LIST_BY_UNIVERSITY_ID;
	}

	public static String getDormiBuildingList() {
		if (IS_SERVER) {
			return URL_SERVER_DORMIBUILDING_LIST;
		}
		return URL_LOCAL_DORMIBUILDING_LIST;
	}

	public static String get_URL_SERVER_UPLOAD_EMAIL_TO_SERVER() {
		if (IS_SERVER) {
			return URL_SERVER_UPLOAD_EMAIL_TO_SERVER;
		}
		return URL_LOCAL_UPLOAD_EMAIL_TO_SERVER;
	}

	public static String get_URL_UPLOAD_DORMITORY_NUMBER_TO_SERVER() {
		if (IS_SERVER) {
			return URL_SERVER_UPLOAD_DORMITORY_NUMBER_TO_SERVER;
		}
		return URL_LOCAL_UPLOAD_DORMITORY_NUMBER_TO_SERVER;
	}

	public static String get_URL_QIUDAI_DISPLAY_INFO_LIST() {
		if (IS_SERVER) {
			return URL_SERVER_QIUDAI_DISPLAY_INFO_LIST;
		}
		return URL_LOCAL_QIUDAI_DISPLAY_INFO_LIST;
	}

	public static String get_URL_GET_QIUDAI_GOODS_INFO_LIST() {
		if (IS_SERVER) {
			return URL_SERVER_GET_QIUDAI_GOODS_INFO_LIST;
		}
		return URL_LOCAL_GET_QIUDAI_GOODS_INFO_LIST;
	}



	

	public static String get_URL_CAMPUS_LIST_HAS_DISCOUNTING_SHOPS_BY_UNIVERSITY_ID() {

		if (IS_SERVER) {
			return URL_SERVER_CAMPUS_LIST_HAS_DISCOUNTING_SHOPS_BY_UNIVERSITY_ID;
		}
		return URL_LOCAL_CAMPUS_LIST_HAS_DISCOUNTING_SHOPS_BY_UNIVERSITY_ID;
	}

	



	
	
	
	public static String get_URL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID(){
		if (IS_SERVER) {
			return URL_SERVER_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID;
		}
		return URL_LOCAL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID;
	}
	
	public static String get_URL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID(){
		if (IS_SERVER) {
			return URL_SERVER_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID;
		}
		return URL_LOCAL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_GOODS_CATEGORY_ID;
	}
}
