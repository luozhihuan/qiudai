package com.luoyu.qiudai.register.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.luoyu.listener.AssociativeWordTextChangeListener;
import com.luoyu.listener.ResetTextOnClickListener;
import com.luoyu.qiudai.register.adapter.AssociativeWordsAdapter;
import com.luoyu.qiudai.register.controller.AssociativeWordController;
import com.luoyu.qiudai.register.model.University;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai_android.R;

public class LocationActivity extends Activity implements OnClickListener {

	private EditText edt_school;

	private ListView associativeWordListView;

	public static List<University> UNIVERSITIES_LIST;

	private ImageView img_school_close;

	private AssociativeWordController associativeWordController;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ConnectNetUtil.CONNECT_NET();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		init();

		
		
		setAssociativeUniversityNameDefault();

		setListener();
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		edt_school
				.addTextChangedListener(new AssociativeWordTextChangeListener(
						this, associativeWordListView,
						associativeWordController, img_school_close,UNIVERSITIES_LIST));
		
		img_school_close.setOnClickListener(new ResetTextOnClickListener(
				edt_school));
		
		
		associativeWordListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				
				University u = (University)associativeWordListView.getAdapter().getItem(position);
				
				UserInfoUtil.UNIVERSITY_ID = u.getUniversityId();
				UserInfoUtil.UNIVERSITY_NAME = u.getUniversityName();
//				CommonUtil.intentStartActivityLeftToRightWithFinish(LocationActivity.this, CampusActivity.class);
				ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(LocationActivity.this, CampusActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			}
			
		});
	}

	/**
	 * 将默认的高校名称列表装配如适配器并进行前端显示
	 */
	private void setAssociativeUniversityNameDefault() {
		if (UNIVERSITIES_LIST != null) {
			AssociativeWordsAdapter adapter = new AssociativeWordsAdapter(
					LocationActivity.this, UNIVERSITIES_LIST);
			associativeWordListView.setAdapter(adapter);
			associativeWordListView.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 初始化
	 */
	private void init() {
		associativeWordListView = (ListView) findViewById(R.id.associativeWordListView);
		edt_school = (EditText) findViewById(R.id.edt_school);
		img_school_close = (ImageView) findViewById(R.id.img_school_close);
		associativeWordController = new AssociativeWordController(this);
	}

	@Override
	public void onClick(View arg0) {
		// location();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 点击返回按钮 **/
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, InputUserNameActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}

}
