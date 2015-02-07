package com.luoyu.qiudai.register.controller;

import android.app.ProgressDialog;
import android.content.Context;

import com.luoyu.net.Network;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;
/**
 * 用于控制本地上传宿舍号至服务器的流程
 * @author chuanrong
 *
 */
public class DormitoryNumberUploadController extends StandardController{
	


	public DormitoryNumberUploadController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 将该宿舍号上传至服务器端，根据服务器的响应，返回相应状态码（通过反射实现）
	 * @param dormitoryNumber：宿舍号
	 * @return 状态码
	 */
	public int sendTheDormitoryNumberToServerByReflect(String dormitoryNumber){
		if(!commonService.checkNetWorkState(context)){
			return VariableUtil.WIFI_OR_NET_NOT_OPEN;
		}
		
		/**宿舍号内容长度为0**/
		if (dormitoryNumber.length() == 0) {
			return VariableUtil.DORMITORY_NUMBER_IS_NULL;
		}
		
		/** 访问网络等待过程中的进度框 **/
		ProgressDialog xh_ProgressDlg = ProgressDialog.show(context,
				"Loading...", "Please wait...", true, false);
		/** 使用反射机制进行访问网络 **/
		ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this,
				"sendTheEmailToServerAndReturnResultCode", new Object[] {
				dormitoryNumber}, context,
				"sendDormitoryNumberOnShowToast",2);
		
		return Integer.MIN_VALUE;
		
	}
	
	/**
	 * 将宿舍号和用户id上传至服务器，并添插入到服务器端数据库的个人信息表里的宿舍号字段里
	 * @param dormitoryNum：宿舍号
	 * @return 状态码
	 */
	public int sendTheEmailToServerAndReturnResultCode(String dormitoryNumber) {
		Network network = Network.GetSingletonInstance(PathUtil.get_URL_UPLOAD_DORMITORY_NUMBER_TO_SERVER(), 5);
		network.addParms(VariableUtil.DORMITORY_NUMBER, dormitoryNumber);
		network.addParms(VariableUtil.USERID, UserInfoUtil.USER_ID + "");
		String resultCodeStr = network.connect();
System.out.println("网络访问结果码："+resultCodeStr);
		return Integer.parseInt(resultCodeStr);
	}
}
