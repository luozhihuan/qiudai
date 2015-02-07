package com.luoyu.qiudai.register.controller;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.register.activity.LocationActivity;
import com.luoyu.qiudai.register.model.University;
import com.luoyu.qiudai.register.service.LocationService;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class UserNameCommitController extends StandardController{


	public UserNameCommitController(Context context) {
		super(context);
	}

	public boolean checkUserNameIsNull(String username) {
		if (username == null) {
			return true;
		}
		return false;
	}

	public boolean checUserNameLengthIsZero(String username) {
		if (username.length() == 0) {
			return true;
		}
		return false;
	}

	public boolean checUserNameLengthIsTooLong(String username) {
		if (username.length() > 10) {
			return true;
		}
		return false;
	}

	public int commit(String username) {
		int resCode = Integer.MIN_VALUE;

		if(!checkNetworkState()){
			return VariableUtil.WIFI_OR_NET_NOT_OPEN;
		}else if (checkUserNameIsNull(username)) {
			return VariableUtil.USERNAME_IS_NULL;
		} else if (checUserNameLengthIsZero(username)) {
			return VariableUtil.USERNAME_LENGTH_IS_ZERO;
		} else if (checUserNameLengthIsTooLong(username)) {
			return VariableUtil.USERNAME_LENGTH_IS_TOO_LONG;
		} else {


			/** 访问网络等待过程中的进度框 **/
			ProgressDialog xh_ProgressDlg = ProgressDialog.show(context,
					"Loading...", "Please wait...", true, false);
			/** 使用反射机制进行访问网络 **/
			ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this,
					"sendUserNameToServer", new Object[] {username},
					context, "usernameCommitOnShowToast",2);
		}
		
		return resCode;

	}
	
	public int sendUserNameToServer(String username){
		Network network = Network.GetSingletonInstance(PathUtil.getUsernameCommitUrl(),5);
		/**设置用户名和用户ID参数**/
		network.addParms(VariableUtil.USERNAME,username);
		network.addParms(VariableUtil.USERID,UserInfoUtil.USER_ID + "");
		/**设置经纬度**/
		network.addParms(VariableUtil.LATITUDE, LocationService.getLatitude() + "");
		network.addParms(VariableUtil.LONGITUDE, LocationService.getLongitude() + "");
		
		String result = network.connect();
		
		if (commonService.isNumber(result)) {/**注册失败**/
			int resultNumber = Integer.parseInt(result);
			return resultNumber;
			
		}else{
			Gson gson = new Gson();
			LocationActivity.UNIVERSITIES_LIST = gson.fromJson(result, new TypeToken<List<University>>() {}.getType());
			return VariableUtil.USERNAME_COMMIT_SUCCESSFUL;
			
		}
		
	}
	
	
	
	
	
	
	
	/** 
     * 检测网络是否连接 
     * @return false表示未连接网络，true表示连接上网络
     */  
    private boolean checkNetworkState() {  
        return commonService.checkNetWorkState(context);  
    } 

}
