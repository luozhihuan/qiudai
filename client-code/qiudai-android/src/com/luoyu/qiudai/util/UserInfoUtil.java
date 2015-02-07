package com.luoyu.qiudai.util;

public class UserInfoUtil {
	
	public static long USER_ID = 16;
	
	public static String USER_NAME;
	
	/**用户注册邮箱**/
	public static String USER_EMAIL;
	
	/**用户密码**/
	public static String USER_PASSWORD;
	
	
	/**大学id,该值正常应该设置为-1，此时用于开发阶段的测试暂时设置为2（2为华中科技大学）**/
	public static int UNIVERSITY_ID = 2;

	/**大学名称**/
	public static String UNIVERSITY_NAME;
	
	/**校区id**/
	public static int CAMPUS_ID = -1;

	/**校区名称**/
	public static String CAMPUS_NAME;
	
	/**宿舍id**/
	public static int DORMITORY_ID = 1;
	
	/**宿舍名称**/
	public static String DORMITORY_NAME;
	
	
	
	public static String TOSTRING(){
		return "userid:"+USER_ID+" username:"+USER_NAME;
	}
	/**
	 * 赋值邮箱密码
	 * @param email邮箱
	 * @param password密码
	 */
	public static void setUserEmailAndPassword(String email,String password){
		UserInfoUtil.USER_EMAIL = email;
		UserInfoUtil.USER_PASSWORD = password;
	}
	
	public static void clearUserEmailAndPassword(){
		UserInfoUtil.USER_EMAIL = null;
		UserInfoUtil.USER_PASSWORD = null;
	}
	

}
