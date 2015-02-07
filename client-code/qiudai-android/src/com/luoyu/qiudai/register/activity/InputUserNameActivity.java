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

import com.luoyu.listener.TextChangeListener;
import com.luoyu.qiudai.register.controller.UserNameCommitController;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

public class InputUserNameActivity extends Activity implements OnClickListener{

	
	private EditText edt_username;
	
	private ImageView img_username_close;
	
	private Button next_btn;
	
	
	private UserNameCommitController nameCommitController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConnectNetUtil.CONNECT_NET();
		setContentView(R.layout.activity_input_user_name);
		
		init();
		
		
		setListener();
		
	}
	
	
	
	/**
	 * 设置监听器
	 */
	private void setListener() {
		edt_username.addTextChangedListener(new TextChangeListener(img_username_close));
		
		next_btn.setOnClickListener(this);
	}
	/**
	 * 初始化组件对象
	 */
	private void init() {
		edt_username = (EditText)findViewById(R.id.edt_username);
		
		img_username_close = (ImageView)findViewById(R.id.img_username_close);
		
		next_btn = (Button)findViewById(R.id.next_btn);
		
		nameCommitController = new UserNameCommitController(this);
	}
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.next_btn:
			nextButtonClick();
			break;

		default:
			break;
		}
	}
	
	public void nextButtonClick(){
		String username = edt_username.getText().toString();
		UserInfoUtil.USER_NAME = username;
		int resultCode = nameCommitController.commit(username);
		usernameCommitOnShowToast(resultCode);
	}
	
	
	/**
	 * 点击注册按键返回的结果码，展现对应的Toast
	 * 
	 * @param resultCode
	 */
	public void usernameCommitOnShowToast(int resultCode) {

		switch (resultCode) {
		case VariableUtil.USERNAME_COMMIT_SUCCESSFUL:
			/** 提交成功 **/
			Toast.makeText(getApplicationContext(),
					"用户名提交成功" + UserInfoUtil.USER_ID, Toast.LENGTH_SHORT)
					.show();
			
//			CommonUtil.intentStartActivityLeftToRightWithFinish(this, LocationActivity.class);
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, LocationActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			break;
		case VariableUtil.USERNAME_IS_NULL:
			/** 用户名为空 **/
			Toast.makeText(getApplicationContext(),
					"用户名不能为空" + UserInfoUtil.TOSTRING(),
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.USERNAME_LENGTH_IS_ZERO:
			/** 用户名长度为0 **/
			Toast.makeText(getApplicationContext(), "用户名不能为空",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.USERNAME_LENGTH_IS_TOO_LONG:
			/** 用户名长度过长 **/
			Toast.makeText(getApplicationContext(), "用户名长度过长",
					Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.USERNAME_COMMIT_FAIL:
			/** 用户名提交失败 **/
			Toast.makeText(getApplicationContext(), "用户名提交失败",
					Toast.LENGTH_SHORT).show();
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
			
			
			/**此处正确逻辑应该弹出对话框询问是否放弃注册，等待添加该功能**/
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}

}
