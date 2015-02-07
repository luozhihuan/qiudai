package com.luoyu.qiudai.register.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luoyu.qiudai.core.activity.QiudaiInfoDisplayActivity;
import com.luoyu.qiudai.register.controller.DormitoryNumberUploadController;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;
/**
 * 用户输入宿舍房间号
 * @author chuanrong
 *
 */
public class InputDormitoryNumberActivity extends Activity implements OnClickListener{

	
	EditText edt_input_dormitory_number;
	
	Button btn_commit;
	
	private DormitoryNumberUploadController dormiNumberUploadController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_dormitory_number);
		
		edt_input_dormitory_number = (EditText)findViewById(R.id.edt_input_dormitory_number);
		btn_commit = (Button)findViewById(R.id.btn_commit);
		
		
		btn_commit.setOnClickListener(this);
		dormiNumberUploadController = new DormitoryNumberUploadController(this);
		
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		/**点击btn_commit(提交)按钮时触发的事件**/
		case R.id.btn_commit:
			/**获取用户输入的宿舍号**/
			String dormitoryNumber = edt_input_dormitory_number.getText().toString().trim();
			/**将宿舍号上传至服务器**/
			uploadTheDormitoryNumberToServer(dormitoryNumber);
			
			break;
		default:
			break;
		}
		
	}
	
	
	/**
	 * 将宿舍号上传至服务器
	 * @param dormitoryNumber
	 */
	public void uploadTheDormitoryNumberToServer(String dormitoryNumber){
		int resultCode = dormiNumberUploadController.sendTheDormitoryNumberToServerByReflect(dormitoryNumber);
		sendDormitoryNumberOnShowToast(resultCode);
	}

	
	/**
	 * 点击提交按键返回的结果码，展现对应的Toast
	 * 
	 * @param resultCode
	 */
	public void sendDormitoryNumberOnShowToast(int resultCode) {
		switch (resultCode) {
		case VariableUtil.SEND_DORMITORY_NUMBER_SUCCESS:
			/** 宿舍号已成功发送至服务器 **/
			Toast.makeText(getApplicationContext(),
					"宿舍号已成功发送至服务器", Toast.LENGTH_SHORT)
					.show();
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, LoginActivity.class);
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSideWithFinishCurActivity(this, QiudaiInfoDisplayActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			
			break;
		case VariableUtil.CONNECT_FAILED:
			/** 密码错误 **/
			Toast.makeText(getApplicationContext(), "网络连接失败", Toast.LENGTH_SHORT)
					.show();
			break;
		
		case VariableUtil.CONNECT_TIME_OUT:
			/** 密码错误 **/
			Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT)
					.show();
			break;	
			
		case VariableUtil.WIFI_OR_NET_NOT_OPEN:
			/** 网络未打开 **/
			Toast.makeText(getApplicationContext(), "网络未打开", Toast.LENGTH_SHORT)
					.show();
			break;	
			
		case VariableUtil.DORMITORY_NUMBER_IS_NULL:
			/** 宿舍号为空 **/
			Toast.makeText(getApplicationContext(), "请填写宿舍号", Toast.LENGTH_SHORT)
					.show();
			break;	
			
		default:
			/** 其它 **/
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 点击返回按钮 **/
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, DormiBuildingActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}

}
