package com.hust.qiudai.web.util;

import com.hust.qiudai.core.variable.OrderVariable;
import com.hust.qiudai.core.variable.PurchaseVariable;


public interface VariableUtil {
	
	
	public static PurchaseVariable PURCHASE_VARIABLE = new PurchaseVariable();
	
	public static OrderVariable ORDER_VARIABLE = new OrderVariable();
	
	/** 登陆成功: 0 **/
	public static int LOGIN_SUCCESSFUL = 0;

	/** 邮箱未找到: -1 **/
	public static int EMAIL_NOT_FOUND = -1;

	/** 密码错误: -2 **/
	public static int PASSWORD_ERROR = -2;

	/** 邮箱名检测失败: -3 **/
	public static int EMAIL_CHECK_FAIL = -3;

	/** 密码检测失败: -4 **/
	public static int PASSWORD_CHECK_FAIL = -4;

	/** 邮箱为空: -5 **/
	public static int EMAIL_IS_NULL = -5;

	/** 密码为空: -6 **/
	public static int PASSWORD_IS_NULL = -6;

	/** 网络连接失败: -7 **/
	public static int CONNECT_FAILED = -7;

	/** 本地网络未打开: -8 **/
	public static int WIFI_OR_NET_NOT_OPEN = -8;

	/** 网络连接超时: -9 **/
	public static int CONNECT_TIME_OUT = -9;

	/** 注册成功: 0 **/
	public static int REGISTER_SUCCESSFUL = LOGIN_SUCCESSFUL;

	/** 注册失败: -1 **/
	public static int REGISTER_FAILED_EMAIL_EXSIST = EMAIL_NOT_FOUND;

	/** 服务器端注册异常: -2 **/
	public static int REGISTER_PROCESS_EXCEPTION = PASSWORD_ERROR;

	/** 登陆email传递参数名 **/
	public static String EMAIL_PARAMETER_NAME = "EMAIL_PARAMETER_NAME_4_LoginController";

	/** 登陆password传递参数名 **/
	public static String PASSWORD_PARAMETER_NAME = "PASSWORD_PARAMETER_NAME_4_LoginController";

	/** 纬度 **/
	public static String LATITUDE = "RegisterController_LATITUDE";

	/** 经度 **/
	public static String LONGITUDE = "RegisterController_LONGITUDE";

	/** 用户名为空: -1 **/
	public static int USERNAME_IS_NULL = -1;

	/** 用户名长度为0: -2 **/
	public static int USERNAME_LENGTH_IS_ZERO = -2;

	/** 用户名长度过长: -3 **/
	public static int USERNAME_LENGTH_IS_TOO_LONG = -3;

	/** 用户名提交失败: -4 **/
	public static int USERNAME_COMMIT_FAIL = -4;

	/** 用户名 **/
	public static String USERNAME = "UserNameCommitController_USERNAME";

	/** 用户ID **/
	public static String USERID = "UserNameCommitController_USER_ID";
	
	
	

	/** 用户名提交成功: 0 **/
	public static int USERNAME_COMMIT_SUCCESSFUL = 0;

	/** 用于获取联想词的单词 **/
	public static String WORD_FOR_ASSCIATIVE_WORDS = "AssociativeWordController_WORD_FOR_ASSCIATIVE_WORDS";

	/** 获取校名联想词失败: -1 **/
	public static int ASSOCIATIVE_UNIVERSITIES_FAIL = -1;

	/** 本地数据库的名字 ***/
	public static String DataBaseNAME = "QiuDai_Local";

	/** 大学ID **/
	public static String UNIVERSITY_ID = "CampusController_UNIVERSITY_ID";
	
	

	/**宿舍id**/
	public static String DORMITORY_ID = "PurchaseControllerDORMITORY_ID";

	/** 成功获取新的密码： 0 **/
	public static int NEW_PASSWORD_SEND_SUCCESS = 0;

	/** 该邮箱未注册： -1 **/
	public static int EMAIL_IS_NOT_REGISTERED = REGISTER_FAILED_EMAIL_EXSIST;

	/** 宿舍号是空： -5 **/
	public static int DORMITORY_NUMBER_IS_NULL = EMAIL_IS_NULL;

	/** 宿舍号 **/
	public static String DORMITORY_NUMBER = "DormitoryNumberUploadController_DORMITORY_NUMBER";

	/** 成功发送宿舍号 状态码： 0 **/
	public static int SEND_DORMITORY_NUMBER_SUCCESS = 0;

	/** 校区id **/
	public static String CAMPUS_ID = "CAMPUS_ID";

	/** 求带信息展示链表为空：-1 **/
	public static int QIUDAI_DISPLAY_INFO_LIST_IS_NULL = ASSOCIATIVE_UNIVERSITIES_FAIL;

	/** 求带信息id **/
	public static String QIUDAI_INFO_ID = "QiudaiInfoDisplayController_QIUDAI_INFO_ID";

	/** 求带商品链表为空：-1 **/
	public static int QIUDAI_GOODS_INFO_LIST_IS_NULL = ASSOCIATIVE_UNIVERSITIES_FAIL;

	
	/** 全部商品种类选中标识符:0 **/
	public static Integer ALL_CATEGORY_LIST_FLAG = 0;
	
	/** 饮料饮品种类链表标识:1 **/
	public static Integer DRINKING_CATEGORY_LIST_FLAG = 1;

	/** 小吃零食饮品种类链表标识 :2**/
	public static Integer SNACK_CATEGORY_LIST_FLAG = 2;
	
	/** 生活日用种类链表标识:3 **/
	public static Integer LIFEUSED_CATEGORY_LIST_FLAG = 3;
	
	/**商品链表标识:4**/
	public static Integer GOODS_INFO_LIST_FLAG = 4;
	
	/**商品类别id**/
	public static String GOODS_CATEGORY_ID = "GoodsCategoryController_goods_category_id";
	
	/**设置商品信息和商品分类成功：0**/
	public static int SET_GOODS_AND_CATEGORY_INFO_SUCCESS = LOGIN_SUCCESSFUL;
	
	/**设置商品信息和商品分类失败：-2**/
	public static int SET_GOODS_AND_CATEGORY_INFO_FAIL = PASSWORD_ERROR;
	
	/**获取信息链表成功：0**/
	public static int GOODS_INFO_LIST_SUCCESS = SEND_DORMITORY_NUMBER_SUCCESS;
	
	public static String SHOPS_ID = "GoodsSelectController_SHOPS_ID";

}
