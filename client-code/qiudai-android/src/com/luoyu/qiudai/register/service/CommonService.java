package com.luoyu.qiudai.register.service;
import android.content.Context;
public class CommonService {
	
	/**检测网络是否打开的服务类**/
	private CheckNetWorkStateService checkNetWorkStateService;
	/**正则表达式**/
	private CheckByRegularExpressionService checkByRegularExpressionService;
	
	
	
	
	public static CommonService commonService;
	
	public CommonService(){
		this.checkNetWorkStateService = new CheckNetWorkStateService();
		this.checkByRegularExpressionService = new CheckByRegularExpressionService();
	}
	
	/**
	 *	用单例模式返回一个CommenService对象
	 * @return
	 */
	public static CommonService getSingletonCommonService(){
		if(commonService == null){
			return new CommonService();
		}else{
			return commonService;
		}
	}
	
	public boolean checkNetWorkState(Context context){
		return checkNetWorkStateService.checkNetworkState(context);
	}
	
	public boolean emailCheck(String emailAddress){
		return checkByRegularExpressionService.checkByRegular(emailAddress, CheckByRegularExpressionService.RegularExpression.EMAIL_EXPRESSION);
	}
	
	public boolean passwordCheck(String password){
//		return checkByRegularExpressionService.checkByRegular(password, CheckByRegularExpressionService.RegularExpression.PASSWORD_1_EXPRESSION);
		return password.length()>0?true:false;
	}
	
	/**是否是正整数，负数，和小数**/
	public boolean isNumber(String str){
		return checkByRegularExpressionService.checkByRegular(str, CheckByRegularExpressionService.RegularExpression.NUMBER_EXPRESSION);
	}
	
}
