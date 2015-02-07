package com.luoyu.qiudai.register.activity;

import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luoyu.listener.ResetTextOnClickListener;
import com.luoyu.listener.TextChangeListener;
import com.luoyu.qiudai.core.activity.QiudaiInfoDisplayActivity;
import com.luoyu.qiudai.register.controller.LoginController;
import com.luoyu.qiudai.register.model.User;
import com.luoyu.qiudai.register.service.UserLocalDBOperService;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.CommonUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.DatabaseHelper;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

public class LoginActivity extends Activity implements OnClickListener {

	TextView txt_Email;

	TextView txt_Pass;

	Button btn_Login;

	ImageView img_EmailTips;
	ImageView img_PassTips;

	TextView txt_Register;
	
	TextView txt_forget_pass;
	LoginController loginController;
	ProgressDialog xh_ProgressDlg;
	DatabaseHelper dbHelper;
	UserLocalDBOperService userLocalDBOper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		ConnectNetUtil.CONNECT_NET();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		init();

		setListener();
		
		
		
		dbHelper = new DatabaseHelper(this);
		dbHelper.openOrCreateDataBase(this);
		
		/**构造对象的过程中完成了表的建立**/
		userLocalDBOper = new UserLocalDBOperService(dbHelper);
		
		try {
			loginController.userList = loginController.GetUserInfoFromLocal(userLocalDBOper);
			if (loginController.HasUserInfoInLocal())
			{
				SetLocalUser(loginController.userList);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("初始化完毕");
		
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		txt_Email.addTextChangedListener(new TextChangeListener(img_EmailTips));
		txt_Pass.addTextChangedListener(new TextChangeListener(img_PassTips));

		img_EmailTips
				.setOnClickListener(new ResetTextOnClickListener(txt_Email));
		img_PassTips.setOnClickListener(new ResetTextOnClickListener(txt_Pass));

		btn_Login.setOnClickListener(this);
		txt_Register.setOnClickListener(this);
		
		txt_forget_pass.setOnClickListener(this);
	}

	/**
	 * 从本地数据库中获取到本地用户的登录信息
	 * @param dbHelper
	 */
	public void SetLocalUser(List<User> userList) 
	{
		txt_Email.setText(userList.get(0).getEmail());
		txt_Pass.setText(userList.get(0).getPassword());
	}
	
	/**
	 * 初始化控件
	 */
	public void init() {
		txt_Email = (TextView) findViewById(R.id.email);
		txt_Pass = (TextView) findViewById(R.id.password);
		btn_Login = (Button) findViewById(R.id.login_btn);
		img_EmailTips = (ImageView) findViewById(R.id.email_Tips);
		img_PassTips = (ImageView) findViewById(R.id.password_Tips);
		txt_Register = (TextView) findViewById(R.id.register_txt);
		txt_forget_pass = (TextView)findViewById(R.id.txt_forget_pass);
		loginController = new LoginController(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			onLoginButtonClick();
			break;
		case R.id.register_txt:
//			CommonUtil.intentStartActivityLeftToRightWithFinish(this,RegisterActivity.class);
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, RegisterActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			break;
		case R.id.txt_forget_pass:
//			CommonUtil.intentStartActivityLeftToRightWithFinish(this,ForgottenPasswordActivity.class);
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, ForgottenPasswordActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			break;
		default:
			break;
		}
	}

	/**
	 * 点击登录按钮后触发的事件
	 */
	public void onLoginButtonClick() {

		int resultCode = loginController.login(txt_Email.getText().toString()
				.trim(), txt_Pass.getText().toString().trim());
		
		longinOnShowToast(resultCode);

	}

	/**
	 * 点击登录按键返回的结果码，展现对应的Toast
	 * 
	 * @param resultCode
	 */
	public void longinOnShowToast(int resultCode) {

		switch (resultCode) {
		case VariableUtil.LOGIN_SUCCESSFUL:
			/** 登陆成功 **/
			loginController.StoreUserInLocal(userLocalDBOper, txt_Email.getText().toString().trim(), txt_Pass.getText().toString().trim());
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSideWithFinishCurActivity(this, QiudaiInfoDisplayActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			Toast.makeText(getApplicationContext(),
					"登陆成功 " + UserInfoUtil.TOSTRING(), Toast.LENGTH_SHORT)
					.show();
			
			break;
		case VariableUtil.EMAIL_NOT_FOUND:
			/** 邮箱未找到 **/
			Toast.makeText(getApplicationContext(), "该邮箱不存在", Toast.LENGTH_SHORT)
					.show();
			break;
		case VariableUtil.PASSWORD_ERROR:
			/** 密码错误 **/
			Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT)
					.show();
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
			Toast.makeText(getApplicationContext(), "邮箱不能为空", Toast.LENGTH_SHORT)
					.show();
			break;
		case VariableUtil.PASSWORD_IS_NULL:
			/** 密码为空 **/
			Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT)
					.show();
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
		default:
			/** 其它 **/
			break;
		}
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
			ActivitySkipHelperUtil.skipToLauncherDeskTop(this);
		}
		return super.onKeyDown(keyCode, event);
	}
}
