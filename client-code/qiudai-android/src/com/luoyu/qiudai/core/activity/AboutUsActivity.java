package com.luoyu.qiudai.core.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai_android.R;

public class AboutUsActivity extends Activity implements OnClickListener {

	private ImageButton img_btn_back = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);

		init();
		
		setListener();

	}
	
	
	/**
	 *设置监听器事件 
	 */
	private void setListener() {
		img_btn_back.setOnClickListener(this);
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		img_btn_back = (ImageButton) findViewById(R.id.img_btn_back);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_btn_back:

			clickBackImageButton();

			break;
		default:
			break;
		}

	}

	/**
	 * 点击界面左上的返回按钮时执行该部分代码
	 */
	private void clickBackImageButton() {
		ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(AboutUsActivity.this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
	}

}
