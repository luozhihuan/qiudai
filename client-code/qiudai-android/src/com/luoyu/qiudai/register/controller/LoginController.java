package com.luoyu.qiudai.register.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.luoyu.net.Network;
import com.luoyu.qiudai.register.model.User;
import com.luoyu.qiudai.register.service.UserLocalDBOperService;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class LoginController extends StandardController{


	public LoginController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<User> userList;
	

	/**
	 * 检测邮箱的合法性
	 * 
	 * @param emailAddress
	 *            邮箱地址
	 * @return 邮箱符合格式要求返回true，否则提示邮箱格式不正确
	 */
	public boolean emailCheck(String emailAddress) {
		return commonService.emailCheck(emailAddress);
	}

	/**
	 * 检测密码是否过于简单
	 * 
	 * @param password
	 *            密码
	 * @return 简单则返回false，提示密码过于简单
	 */
	public boolean passwordCheck(String password) {
		return commonService.passwordCheck(password);
	}

	/**
	 * 
	 * 登录判断
	 * 
	 * @param emailAddress
	 *            邮箱
	 * @param password
	 *            密码
	 * @return 返回0：登录成功，1：邮箱找不到，2：密码错误,3:邮箱信息不合法，4：密码过于简单
	 */
	public int login(String emailAddress, String password) {
		int resCode = Integer.MIN_VALUE;

		if (!checkNetworkState()) {
			return VariableUtil.WIFI_OR_NET_NOT_OPEN;
		}

		if (emailAddress.length() == 0) {
			return VariableUtil.EMAIL_IS_NULL;
		}
		if (password.length() == 0) {
			return VariableUtil.PASSWORD_IS_NULL;
		}

		if (!emailCheck(emailAddress)) {
			resCode = VariableUtil.EMAIL_CHECK_FAIL;
		} else if (!passwordCheck(password)) {
			resCode = VariableUtil.PASSWORD_CHECK_FAIL;
		} else {
			/** 网络连接，邮箱格式，密码格式一切检测成功后开始访问网络进行登陆检测 **/

			/** 访问网络等待过程中的进度框 **/
			ProgressDialog xh_ProgressDlg = ProgressDialog.show(context,
					"Loading...", "Please wait...", true, false);
			/** 使用反射机制进行访问网络 **/
			ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this,
					"sendEmailAndPasswordToServer", new Object[] {
							emailAddress, password }, context,
					"longinOnShowToast",2);
			

		}

		return resCode;
	}

	/**
	 * 将邮箱密码传递给服务器端
	 * 
	 * @param emailAddress
	 *            邮箱
	 * @param password
	 *            密码
	 * @return 返回0：登录成功，1：邮箱找不到，2：密码错误
	 */
	public int sendEmailAndPasswordToServer(String emailAddress, String password) {
		Network network = Network.GetSingletonInstance(PathUtil.getLoginUrl(), 5);
		network.addParms(VariableUtil.EMAIL_PARAMETER_NAME, emailAddress);
		network.addParms(VariableUtil.PASSWORD_PARAMETER_NAME, password);
		String result = network.connect();
		
		/** 服务器返回对象字符串，表示登陆成功 **/
		if (!commonService.isNumber(result)) {
			Gson gson = new Gson();
			User user = gson.fromJson(result, User.class);
			UserInfoUtil.USER_ID = user.getUserId();
			UserInfoUtil.USER_NAME = user.getEmail();

			result = VariableUtil.LOGIN_SUCCESSFUL + "";
		}


		return Integer.parseInt(result);
	}

	/**
	 * 检测网络是否连接
	 * 
	 * @return false表示未连接网络，true表示连接上网络
	 */
	private boolean checkNetworkState() {
		return commonService.checkNetWorkState(context);
	}
	
	/**
	 * 从本地数据库获取用户信息
	 * @param userLocalDBOper
	 * @return
	 * @throws JSONException
	 */
	public List<User> GetUserInfoFromLocal(UserLocalDBOperService userLocalDBOper) throws JSONException
	{
		List<User> userInfoList = new ArrayList<User>();
		
		JSONObject userInfoOfJson = userLocalDBOper.GetUserDataOfJson();
		
		Iterator<?> it = userInfoOfJson.keys();
		while(it.hasNext())
		{
			User user = new User();
			String key = (String)it.next();
			JSONObject element = (JSONObject)userInfoOfJson.get(key);
			user.setEmail(element.getString("email")) ;
			user.setPassword(element.getString("password"));
			userInfoList.add(user);
		}
		
		return userInfoList;
	}

	public boolean HasUserInfoInLocal()
	{
		return userList.size()>0?true:false;
	}
	
	public boolean ExistUserInfoInLocal(String email)
	{
		boolean isTrue = false;
		for (User user : userList)
		{
			if (user.getEmail().trim() == email.trim()) 
			{
				isTrue = true;
				break;
			}
		}
		return isTrue;
	}
	
	/**
	 * 将用户数据存储到本地数据库
	 * @param userLocalDBOper
	 * @param email
	 * @param password
	 */
	public void StoreUserInLocal(UserLocalDBOperService userLocalDBOper,String email,String password)
	{
		if (!ExistUserInfoInLocal(email))
		{
			Date now = new Date();
			userLocalDBOper.Insert(email, password, now.toString());
		}
	}
}
