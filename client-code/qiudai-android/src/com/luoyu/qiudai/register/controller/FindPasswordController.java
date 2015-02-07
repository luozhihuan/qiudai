package com.luoyu.qiudai.register.controller;

import android.app.ProgressDialog;
import android.content.Context;

import com.luoyu.net.Network;
import com.luoyu.qiudai.register.service.CommonService;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class FindPasswordController extends StandardController{


	
	@Deprecated
	private String newPassword = null;

	public FindPasswordController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 上传该email至服务器，通过检测该email是否注册， 如果注册，则返回密码，如果没注册
	 * 则返回没注册的结果码，如果网络访问错误则返回对应的错误码
	 * @param email
	 * @return 如果该邮箱已注册：新密码  未注册or网络异常：失败码
	 */
	@Deprecated
	public String uploadTheEmailAndReturenResultInfo(String email) {
		int resultCode = sendTheEmailToServerAndReturnResultCode(email);
		String newPasswordOrFailedCode;

		switch (resultCode) {
		case VariableUtil.NEW_PASSWORD_SEND_SUCCESS:
			newPasswordOrFailedCode = getNewPassword();
			break;
		default:
			newPasswordOrFailedCode = resultCode + "";
			break;
		}
		
		return newPasswordOrFailedCode;
	}

	/**
	 * 检测该邮箱是否已被注册
	 * 
	 * @param email
	 * @return true：表示已被注册；false表示未被注册
	 */
	public boolean isTheEmailRegistered(String email) {

		return true;
	}
	
	/**
	 * 将该email上传至服务器端，根据服务器的响应，返回相应状态码（通过反射实现）
	 * @param email
	 * @return 状态码
	 */
	public int sendTheEmailToServerByReflect(String email){
		/**wifi或网络未开通**/
		if (!checkNetworkState()) {
			return VariableUtil.WIFI_OR_NET_NOT_OPEN;
		}
		/**email内容长度为0**/
		if (email.length() == 0) {
			return VariableUtil.EMAIL_IS_NULL;
		}
		/** 访问网络等待过程中的进度框 **/
		ProgressDialog xh_ProgressDlg = ProgressDialog.show(context,
				"Loading...", "Please wait...", true, false);
		/** 使用反射机制进行访问网络 **/
		ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this,
				"sendTheEmailToServerAndReturnResultCode", new Object[] {
						email}, context,
				"fogorttenPasswordOnShowToast",2);
		
		return Integer.MIN_VALUE;
	}

	/**
	 * 将email上传至服务器，并进行检测该email是否已经被注册，如果未注册，返回该用户“未注册”
	 * 状态码，如果已注册，则将服务器为该email新设置的密码赋值给newPassword，并返回“成功获取密码”的 状态码
	 * 
	 * @param email
	 * @return 状态码
	 */
	public int sendTheEmailToServerAndReturnResultCode(String email) {
		Network network = Network.GetSingletonInstance(
				PathUtil.get_URL_SERVER_UPLOAD_EMAIL_TO_SERVER(), 5);
		network.addParms(VariableUtil.EMAIL_PARAMETER_NAME, email);
		String resultCodeStr = network.connect();
System.out.println("网络访问结果码："+resultCodeStr);
		return Integer.parseInt(resultCodeStr);
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
	 * 返回newPassword的值后，将该值设置为空
	 * 
	 * @return newPassword
	 */
	@Deprecated
	public String getNewPassword() {
		String password = newPassword;
		newPassword = null;
		return password;
	}
}
