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

import com.luoyu.qiudai.register.adapter.DormiBuildingSelectAdapter;
import com.luoyu.qiudai.register.controller.DormiBuildingController;
import com.luoyu.qiudai.register.model.Dormitory;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai_android.R;

/**
 * 用于展现dormitorybuilding的界面
 * 
 * @author wangyang
 * 
 */

public class PurchaseDormiBuildingActivity extends Activity implements OnClickListener {

	private ListView listview_dormibuilding_select;

	private RelativeLayout layout_dormibuildingNull;

	private ImageView img_back;

	private TextView txt_back;

	private DormiBuildingController dormibuildingController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dormibuilding);

		init();

		List<Dormitory> dormibuildingList = dormibuildingController.getDormiBuildingList(UserInfoUtil.CAMPUS_ID);

		setTheIsVisibleSwitchOfDormitoryNull(dormibuildingList);

		DormiBuildingSelectAdapter adapter = new DormiBuildingSelectAdapter(this, dormibuildingList);

		listview_dormibuilding_select.setAdapter(adapter);

		img_back.setOnClickListener(this);

		listview_dormibuilding_select.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
System.out.println("宿舍position:"+position);				
				/** 获取点击的当前Dormitory对象 **/
				Dormitory d = (Dormitory) listview_dormibuilding_select.getAdapter().getItem(position);
				/** 设置用户选择的宿舍id和宿舍名称 **/
				UserInfoUtil.DORMITORY_ID = d.getDormibuildingid();
				UserInfoUtil.DORMITORY_NAME = d.getDormibuildingname();
				PurchaseActivity.DORMITORY_BUILDING_NAME = UserInfoUtil.DORMITORY_NAME;
				/** 跳转Activity **/
				PurchaseCampusActivity.PURCHASE_CAMPUS_ACTIVITY.finish();
				ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(PurchaseDormiBuildingActivity.this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
			}

		});

//		resetDormitoryId();
	}

	/**
	 * 根据dormibuildingList内是元素是否个数为0或者CAMPUS_ID是否为-1
	 * 来设置layout_dormibuildingNull是否可见
	 * 
	 * @param dormibuildingList
	 */
	private void setTheIsVisibleSwitchOfDormitoryNull(List<Dormitory> dormibuildingList) {
		if (UserInfoUtil.CAMPUS_ID == -1 || dormibuildingList == null || dormibuildingList.size() == 0) {
			listview_dormibuilding_select.setVisibility(View.INVISIBLE);
			layout_dormibuildingNull.setVisibility(View.VISIBLE);
			// setBackVisible();
		} else {
			// setBackInvisible();
			layout_dormibuildingNull.setVisibility(View.INVISIBLE);
			listview_dormibuilding_select.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 初始化对象
	 */
	private void init() {
		listview_dormibuilding_select = (ListView) findViewById(R.id.listview_dormibuilding_select);
		layout_dormibuildingNull = (RelativeLayout) findViewById(R.id.layout_dormibuildingNull);
		img_back = (ImageView) findViewById(R.id.img_back);
		txt_back = (TextView) findViewById(R.id.txt_back);
		dormibuildingController = new DormiBuildingController(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.img_back:

//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, CampusActivity.class);
			
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
			break;

		default:
			break;
		}

	}

	/**
	 * 将宿舍楼id设置为初始值
	 */
	public void resetDormitoryId() {
		UserInfoUtil.DORMITORY_ID = -1;
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
			
//			CommonUtil.intentStartActivityRightToLeftWithFinish(this, CampusActivity.class);
			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}
}
