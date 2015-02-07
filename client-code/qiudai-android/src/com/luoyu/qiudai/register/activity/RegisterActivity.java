package com.luoyu.qiudai.register.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.luoyu.listener.ResetTextOnClickListener;
import com.luoyu.listener.TextChangeListener;
import com.luoyu.qiudai.register.controller.RegisterController;
import com.luoyu.qiudai.register.service.LocationService;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.CommonUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

public class RegisterActivity extends Activity implements OnClickListener {

	TextView txt_Email;

	TextView txt_Pass;

	ImageView img_EmailTips;
	ImageView img_PassTips;

	Button btn_register;

	RegisterController registerController;

	private LocationClient mLocClient;
	
	private LocationService locationService = new LocationService();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		init();
		
		setOnListener();

		locationService.start(this);

	}

	

	/**
	 * 设置监听器
	 */
	private void setOnListener() {
		/** 为邮箱输入框设置输入框内容变动监听器 **/
		txt_Email.addTextChangedListener(new TextChangeListener(img_EmailTips));
		/** 为邮密码入框设置输入框内容变动监听器 **/
		txt_Pass.addTextChangedListener(new TextChangeListener(img_PassTips));

		/** 设置邮箱内容置空功能的监听器 **/
		img_EmailTips
				.setOnClickListener(new ResetTextOnClickListener(txt_Email));
		/** 设置密码内容置空功能的监听器 **/
		img_PassTips.setOnClickListener(new ResetTextOnClickListener(txt_Pass));

		/** 为注册按钮注册监听器 **/
		btn_register.setOnClickListener(this);
	}

	/**
	 * 初始化控件
	 */
	public void init() {
		txt_Email = (TextView) findViewById(R.id.email_edt);
		txt_Pass = (TextView) findViewById(R.id.password_edt);

		img_EmailTips = (ImageView) findViewById(R.id.email_Tips);
		img_PassTips = (ImageView) findViewById(R.id.password_Tips);

		btn_register = (Button) findViewById(R.id.register_btn);

		registerController = new RegisterController(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_btn:
			onRegisterButtonClick();
			break;

		default:
			break;
		}

	}

	/**
	 * 点击注册按键返回的结果码，展现对应的Toast
	 * 
	 * @param resultCode
	 */
	public void registerOnShowToast(int resultCode) {

		switch (resultCode) {
		case VariableUtil.REGISTER_SUCCESSFUL:
			/** 注册成功 **/
			Toast.makeText(getApplicationContext(),
					"注册成功，用户ID： " + UserInfoUtil.USER_ID, Toast.LENGTH_SHORT)
					.show();
			
//			CommonUtil.intentStartActivityLeftToRightWithFinish(this, InputUserNameActivity.class);
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, InputUserNameActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			
			break;
		case VariableUtil.REGISTER_FAILED_EMAIL_EXSIST:
			/** 注册失败,邮箱已被注册 **/
			Toast.makeText(getApplicationContext(),
					"注册失败,该用户已经被注册 " + UserInfoUtil.TOSTRING(),
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.EMAIL_CHECK_FAIL:
			/** 邮箱格式不正确 **/
			Toast.makeText(getApplicationContext(), "邮箱格式不正确",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.PASSWORD_CHECK_FAIL:
			/** 密码格式不正确 **/
			Toast.makeText(getApplicationContext(), "密码格式不正确",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.EMAIL_IS_NULL:
			/** 邮箱为空 **/
			Toast.makeText(getApplicationContext(), "邮箱不能为空",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.PASSWORD_IS_NULL:
			/** 密码为空 **/
			Toast.makeText(getApplicationContext(), "密码不能为空",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.WIFI_OR_NET_NOT_OPEN:
			/** 网络未开 **/
			Toast.makeText(getApplicationContext(), "网络未开启", Toast.LENGTH_SHORT)
					.show();
			break;
		case VariableUtil.CONNECT_TIME_OUT:
			/** 网络连接超时 **/
			Toast.makeText(getApplicationContext(), "网络连接超时，请稍后再试",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.REGISTER_PROCESS_EXCEPTION:
			/** 服务器端异常 **/
			Toast.makeText(getApplicationContext(), "我们的服务器暂时出现问题，无法正常连接，抱歉",
					Toast.LENGTH_SHORT).show();
			break;
		default:
			/** 其它 **/
			break;
		}
	}

	/**
	 * 点击注册按钮
	 */
	public void onRegisterButtonClick() {
		
		String email = txt_Email.getText().toString().trim();
		
		String password = txt_Pass.getText().toString().trim();
		
		UserInfoUtil.setUserEmailAndPassword(email, password);
		
		int resultCode = registerController.register(email, password);
		
		registerOnShowToast(resultCode);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		CommonUtil.clickOtherHideTheKeyboard(this, event);
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 点击返回按钮 **/
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, LoginActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}
}
