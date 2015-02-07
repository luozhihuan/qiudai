package com.luoyu.qiudai.register.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckByRegularExpressionService {
	/**
	 * 正则表达式匹配
	 * @param str_Content 被匹配字符串
	 * @param str_Pattern 正则
	 * @return 返回值，true：匹配; false：不匹配
	 */
	public boolean checkByRegular(String str_Content, String str_Pattern) {
		boolean tag = true;
		Pattern pattern = Pattern.compile(str_Pattern);
		Matcher mat = pattern.matcher(str_Content);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/** 正则表达式变量 **/
	public interface RegularExpression {
		/** 邮箱 **/
		public static final String EMAIL_EXPRESSION = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		/** 密码 **/
		public static final String PASSWORD_1_EXPRESSION = "^[a-zA-Z]\\w{5,17}$";
		/**正整数，负整数，小数**/
		public static final String NUMBER_EXPRESSION = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
	}
}
