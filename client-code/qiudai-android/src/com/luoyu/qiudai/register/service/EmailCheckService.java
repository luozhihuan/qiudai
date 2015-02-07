package com.luoyu.qiudai.register.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检测邮箱名称服务类
 * @author chuanrong
 *
 */
public class EmailCheckService {
	
	/**
	 * 检测邮箱的合法性
	 * @param emailAddress 邮箱地址
	 * @return 邮箱符合格式要求返回true，否则提示邮箱格式不正确
	 */
	public boolean checkEmail(String emailAddress)
	{
		 boolean tag = true; 
         String pattern1 = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b"; 
         Pattern pattern = Pattern.compile(pattern1); 
         Matcher mat = pattern.matcher(emailAddress); 
        if (!mat.find()) { 
            tag = false; 
        } 
        return tag; 
	} 
	
}
