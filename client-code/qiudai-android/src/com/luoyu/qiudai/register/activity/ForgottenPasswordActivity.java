package com.luoyu.qiudai.register.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.luoyu.listener.ResetTextOnClickListener;
import com.luoyu.listener.TextChangeListener;
import com.luoyu.qiudai.register.controller.FindPasswordController;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

public class ForgottenPasswordActivity extends Activity implements OnClickListener {

	private EditText txt_input_your_email;
	private Button btn_commit;
	
	private FindPasswordController findPasswordController;
	
	private ImageView img_PasswordTips;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		ConnectNetUtil.CONNECT_NET();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotten_password);
		/**获取编辑框的实例**/
		txt_input_your_email = (EditText)findViewById(R.id.txt_input_your_email);
		/**获取按钮的实例**/
		btn_commit = (Button)findViewById(R.id.btn_commit);
		/**获取忘记密码"X"的实例**/
		img_PasswordTips = (ImageView) findViewById(R.id.password_Tips);
		/**获取查找密码控制器的实例**/
		findPasswordController = new FindPasswordController(this);
		/**为按钮设置点击监听器**/
		btn_commit.setOnClickListener(this);
		/**清空txt_input_your_email**/
		txt_input_your_email.addTextChangedListener(new TextChangeListener(img_PasswordTips));
		img_PasswordTips.setOnClickListener(new ResetTextOnClickListener(txt_input_your_email));
	}

	
	/**
	 *重写实现自OnClickListener的方法
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		/**由btn_commit按钮触发点击事件**/
		case R.id.btn_commit:
			/**获取当前txt_input_your_email编辑框的内容**/
			String email = txt_input_your_email.getText().toString();
			int resultCode = sendTheEmailToServer(email);
			fogorttenPasswordOnShowToast(resultCode);
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * 将该email上传至服务器端，根据服务器的相应，返回相应状态码
	 * @param email
	 * @return 状态码
	 */
	public int sendTheEmailToServer(String email){
		return findPasswordController.sendTheEmailToServerByReflect(email);
		
	}
	/**
	 * 点击登录按键返回的结果码，展现对应的Toast
	 * 
	 * @param resultCode
	 */
	public void fogorttenPasswordOnShowToast(int resultCode) {
		switch (resultCode) {
		case VariableUtil.NEW_PASSWORD_SEND_SUCCESS:
			/** 密码已发送至邮件 **/
			Toast.makeText(getApplicationContext(),
					"密码已发送至邮件", Toast.LENGTH_SHORT)
					.show();
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, LoginActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
			break;
		case VariableUtil.EMAIL_IS_NOT_REGISTERED:
			/** 该邮箱未注册 **/
			Toast.makeText(getApplicationContext(), "该邮箱未注册", Toast.LENGTH_SHORT)
					.show();
			break;
		case VariableUtil.CONNECT_FAILED:
			/** 网络连接失败 **/
			Toast.makeText(getApplicationContext(), "网络连接失败", Toast.LENGTH_SHORT)
					.show();
			break;
		
		case VariableUtil.CONNECT_TIME_OUT:
			/** 网络连接超时 **/
			Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT)
					.show();
			break;	
			
		case VariableUtil.WIFI_OR_NET_NOT_OPEN:
			/** 网络未打开 **/
			Toast.makeText(getApplicationContext(), "网络未打开", Toast.LENGTH_SHORT)
					.show();
			break;	
			
		case VariableUtil.EMAIL_IS_NULL:
			/** 邮箱为空 **/
			Toast.makeText(getApplicationContext(), "邮箱为空", Toast.LENGTH_SHORT)
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
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
}
