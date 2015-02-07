package com.luoyu.qiudai.register.controller;


import android.app.ProgressDialog;
import android.content.Context;

import com.luoyu.net.Network;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class RegisterController extends StandardController{

	
	
	
	public RegisterController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	/**
	 * 
	 * 注册
	 * @param emailAddress 邮箱
	 * @param password 密码
	 * @return 返回
	 */
	public int register(String emailAddress,String password){
		int resCode = Integer.MIN_VALUE;
		
		
		if(!checkNetworkState()){
			return VariableUtil.WIFI_OR_NET_NOT_OPEN;
		}
		
		if(emailAddress.length() == 0){
			return VariableUtil.EMAIL_IS_NULL;
		}
		if(password.length() == 0){
			return VariableUtil.PASSWORD_IS_NULL;
		}
		
		
		
		if (!emailCheck(emailAddress))
		{
			resCode = VariableUtil.EMAIL_CHECK_FAIL;
		}
		else if (!passwordCheck(password))
		{
			resCode = VariableUtil.PASSWORD_CHECK_FAIL;
		}
		else
		{
			/**网络连接，邮箱格式，密码格式一切检测成功后开始访问网络进行登陆检测**/
			
			/**访问网络等待过程中的进度框**/
			ProgressDialog xh_ProgressDlg = ProgressDialog.show(context, "Loading...",
					"Please wait...", true, false);
			/**使用反射机制进行访问网络**/
//			ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this,
//					"sendEmailAndPasswordToServer", new Object[] {emailAddress,password},(LoginActivity)context,"longinOnShowToast");
			
			
			ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this,
					"sendEmailAndPasswordToServer", new Object[] {emailAddress,password},context,"registerOnShowToast",2);
		}	
		
		return resCode;
		
	}
	
	
	
	/**
	 * 将邮箱密码传递给服务器端
	 * @param emailAddress 邮箱
	 * @param password 密码
	 * @return 返回
	 */
	public int sendEmailAndPasswordToServer(String emailAddress,String password){
		Network network = Network.GetSingletonInstance(PathUtil.getRegisterUrl(),5);
		/**设置邮箱和密码参数**/
		network.addParms(VariableUtil.EMAIL_PARAMETER_NAME,emailAddress);
		network.addParms(VariableUtil.PASSWORD_PARAMETER_NAME, password);
		
		
		String result = network.connect();
		
		
		
		if (commonService.isNumber(result)) {
			int resultNumber = Integer.parseInt(result);
			/**注册失败**/
			if(resultNumber < 0){
				return resultNumber;
			}else{/**注册成功**/
				UserInfoUtil.USER_ID = Integer.parseInt(result);
				result = VariableUtil.REGISTER_SUCCESSFUL + "";
			}
		}
		
		
		return Integer.parseInt(result);
	}
	
	
	/**
	 * 检测邮箱的合法性
	 * @param emailAddress 邮箱地址
	 * @return 邮箱符合格式要求返回true，否则提示邮箱格式不正确
	 */
	public boolean emailCheck(String emailAddress)
	{
        return commonService.emailCheck(emailAddress); 
	} 
	
	/**
	 *检测密码是否过于简单 
	 * @param password 密码
	 * @return 简单则返回false，提示密码过于简单
	 */
	public boolean passwordCheck(String password)
	{
		return commonService.passwordCheck(password);
	}
	
	/** 
     * 检测网络是否连接 
     * @return false表示未连接网络，true表示连接上网络
     */  
    private boolean checkNetworkState() {  
        return commonService.checkNetWorkState(context);  
    }  
}
