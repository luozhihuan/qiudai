package com.luoyu.qiudai.core.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luoyu.qiudai.register.adapter.CampusSelectAdapter;
import com.luoyu.qiudai.register.controller.CampusController;
import com.luoyu.qiudai.register.model.Campus;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai_android.R;

/**
 * 用于展现campus的界面
 * 
 * @author wangyang
 * 
 */

public class PurchaseCampusActivity extends Activity implements OnClickListener {
	


	private ListView listview_campus_select;

	private RelativeLayout layout_campusNull;

	private ImageView img_back;

	private TextView txt_back;

	private CampusController campusController;

	
	private CampusSelectAdapter adapter;
	
	/** 用于停止当前activity **/
	public static Activity PURCHASE_CAMPUS_ACTIVITY;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campus);
		
		PURCHASE_CAMPUS_ACTIVITY = this;
		init();
		/** 通过传入的universtyid从服务器获取该学校的校区列表 **/
		List<Campus> campusList = campusController
				.getCampusListFromServer(UserInfoUtil.UNIVERSITY_ID);
	    //List<Campus> campusList = campusController.getCampusListFromServerTest();
		
		setTheIsVisibleSwitchOfCampusNull(campusList);

		img_back.setOnClickListener(this);

		adapter = new CampusSelectAdapter(this, campusList);

		listview_campus_select.setAdapter(adapter);

		setListener();

//		resetUniversityId();
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		img_back.setOnClickListener(this);
		listview_campus_select.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
						/**获取点击的当前Campus对象**/
						Campus c = (Campus) listview_campus_select.getAdapter().getItem(position);
						/**设置DormiBuildingActivity的id和校区名称的值**/
						UserInfoUtil.CAMPUS_ID = c.getCampusId();
						UserInfoUtil.CAMPUS_NAME = c.getCampusName();
						/**跳转Activity**/
//						CommonUtil.intentStartActivityLeftToRightWithFinish(CampusActivity.this, DormiBuildingActivity.class);
						
						ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(PurchaseCampusActivity.this, PurchaseDormiBuildingActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
					}

				});
	}

	/**
	 * 根据campusList内是元素是否个数为0或者UNIVERSITY_ID是否为-1 来设置layout_campusNull是否可见
	 * 
	 * @param campusList
	 */
	private void setTheIsVisibleSwitchOfCampusNull(List<Campus> campusList) {
		/** 未能获取到校区列表的数据,将为空的布局显示为可见 **/
		if (UserInfoUtil.UNIVERSITY_ID == -1 || campusList == null || campusList.size() == 0) {
			listview_campus_select.setVisibility(View.INVISIBLE);
			layout_campusNull.setVisibility(View.VISIBLE);
			// setBackVisible();
		} else {
			/** 能获取到校区列表的数据,将为空的布局显示为不可见 **/
			// setBackInvisible();
			layout_campusNull.setVisibility(View.INVISIBLE);
			listview_campus_select.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 初始化界面控件
	 */
	private void init() {
		listview_campus_select = (ListView) findViewById(R.id.listview_campus_select);
		layout_campusNull = (RelativeLayout) findViewById(R.id.layout_campusNull);
		img_back = (ImageView) findViewById(R.id.img_back);
		txt_back = (TextView) findViewById(R.id.txt_back);
		campusController = new CampusController(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.img_back:

//			CommonUtil.intentStartActivityRightToLeftWithFinish(this,LocationActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
			break;

		default:
			break;
		}

	}

	/**
	 * 将学校id设置为初始值
	 */
	public void resetUniversityId() {
		UserInfoUtil.UNIVERSITY_ID = -1;
	}

	public void setBackInvisible() {
		img_back.setVisibility(View.INVISIBLE);
		txt_back.setVisibility(View.INVISIBLE);
	}

	public void setBackVisible() {
		img_back.setVisibility(View.VISIBLE);
		txt_back.setVisibility(View.VISIBLE);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 点击返回按钮 **/
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, LocationActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}
}
