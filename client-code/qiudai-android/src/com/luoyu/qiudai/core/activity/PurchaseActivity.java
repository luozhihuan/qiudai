package com.luoyu.qiudai.core.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luoyu.qiudai.core.controller.PurchaseController;
import com.luoyu.qiudai.core.data.GoodsInfoData;
import com.luoyu.qiudai.core.model.impl.GoodsIdAndBuyNum;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.register.controller.DormitoryNumberUploadController;
import com.luoyu.qiudai.register.model.Dormitory;
import com.luoyu.qiudai.core.variable.PurchaseVariable;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.CommonUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

public class PurchaseActivity extends Activity implements OnClickListener {

	private Button addfee;
	private Button minusfee;
	private Button addArrivalTime;
	private Button minusArrivalTime;
	private TextView feeNum;
	private TextView timeNum;
	private Button btn_publish;
	private TextView txt_supply_content;
	private TextView txt_address;
	private RelativeLayout layout_address;
	
	private PurchaseController purchaseController;

	/** 跑腿费步长默认值 **/
	private final int feeStep = 1;
	/** 跑腿时间步长默认值 **/
	private final int timeStep = 5;
	/** 跑腿费默认值 **/
	private final int MinFeeNum = 1;
	/** 跑腿时间默认值 **/
	private final int MinTimeNum = 20;

	public static String DORMITORY_BUILDING_NAME;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ConnectNetUtil.CONNECT_NET();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase);
		initLayout();
		purchaseController = new PurchaseController(this);

		setOnClickListenerForViews();
	}

	/**
	 * 初始化控件
	 */
	private void setOnClickListenerForViews() {
		/** 为跑腿费用及跑腿时间按钮设置点击监听器 **/
		addfee.setOnClickListener(this);
		minusfee.setOnClickListener(this);
		addArrivalTime.setOnClickListener(this);
		minusArrivalTime.setOnClickListener(this);
		btn_publish.setOnClickListener(this);
		txt_address.setOnClickListener(this);
		layout_address.setOnClickListener(this);		
	}

	/**
	 * 设置监听器
	 */
	private void initLayout() {
		/** 获取跑腿费用及跑腿时间按钮的实例 **/
		addfee = (Button) findViewById(R.id.btn_add_fee);
		minusfee = (Button) findViewById(R.id.btn_minus_fee);
		addArrivalTime = (Button) findViewById(R.id.btn_add_arrival_time);
		minusArrivalTime = (Button) findViewById(R.id.btn_minus_arrival_time);
		feeNum = (TextView) findViewById(R.id.txt_fee_num);
		timeNum = (TextView) findViewById(R.id.txt_time_num);
		btn_publish = (Button) findViewById(R.id.btn_publish);
		txt_supply_content = (TextView) findViewById(R.id.txt_supply_content);
		txt_address = (TextView) findViewById(R.id.txt_address);
		layout_address = (RelativeLayout) findViewById(R.id.layout_address);
	}

	/**
	 * 重写实现自OnClickListener的方法
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		/** 由跑腿费用及跑腿时间按钮触发点击事件 **/
		case R.id.btn_add_fee:
			purchaseChangeStep(feeNum, addfee, feeStep);
			break;
		case R.id.btn_minus_fee:
			purchaseChangeStep(feeNum, minusfee, -feeStep);
			break;
		case R.id.btn_add_arrival_time:
			purchaseChangeStep(timeNum, addArrivalTime, timeStep);
			break;
		case R.id.btn_minus_arrival_time:
			purchaseChangeStep(timeNum, minusArrivalTime, -timeStep);
			break;
		case R.id.btn_publish:
			publishPurchaseQiudaiInfo();
			break;
		case R.id.layout_address:
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(PurchaseActivity.this, PurchaseCampusActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			break;
		default:
			break;
		}
	}

	/**
	 * 发布求带消息
	 */
	public void publishPurchaseQiudaiInfo() {
		String goodsIdAndBuyNumListStr = getGoodsIdAndBuyNumListJson(GoodsInfoData.SHOPPING_TROLLEY_MAP);
		String fee = feeNum.getText().toString().trim();
		String time = timeNum.getText().toString().trim();
		String supply = txt_supply_content.getText().toString().trim();
		purchaseController.publishPurchaseInfoToServerByReflect(goodsIdAndBuyNumListStr, supply, fee, time);

	}

	/**
	 * 根据请求服务器端商品信息链表的返回结果来做相应的逻辑判断
	 * 
	 * @param resultCode
	 *            服务器端返回状态码
	 */
	public void progressDialogProcessing(int resultCode) {
		if (VariableUtil.PURCHASE_VARIABLE.PUBLISH_SUCCUSS == resultCode) {
			Toast.makeText(getApplicationContext(), "发送成功 ", Toast.LENGTH_SHORT).show();
			
			clearTheShoppingTrolleyData();
			
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSideWithFinishCurActivity(this, QiudaiInfoDisplayActivity.class,
					ActivitySkipHelperUtil.RIGHT_TO_LEFT);

		} else if (VariableUtil.PURCHASE_VARIABLE.PUBLISH_FAILED == resultCode) {
			Toast.makeText(getApplicationContext(), "发送失败 ", Toast.LENGTH_SHORT).show();
		}

	}
	
	
	/**
	 * 清空购物车中的数据
	 */
	public void clearTheShoppingTrolleyData(){
		purchaseController.clearTheShoppingTrolleyMap();
	}

	/**
	 * 获取json格式的GoodsIdAndBuyNumList
	 * 
	 * @param shopping_Trolley_Map
	 *            购物车map
	 * @return
	 */
	private String getGoodsIdAndBuyNumListJson(Map<Long, GoodsInfo> shopping_Trolley_Map) {
		Set<Long> goodsIdSet = shopping_Trolley_Map.keySet();
		List<GoodsIdAndBuyNum> goodsIdAndBuyNumList = new ArrayList<GoodsIdAndBuyNum>();
		for (long goodsId : goodsIdSet) {
			GoodsInfo goodsInfo = shopping_Trolley_Map.get(goodsId);
			goodsIdAndBuyNumList.add(new GoodsIdAndBuyNum(goodsInfo));
		}
		String goodsIdAndBuyNumListStr = CommonUtil.getJsonStringByGson(goodsIdAndBuyNumList);
		return goodsIdAndBuyNumListStr;
	}

	/**
	 * 对跑腿费用及跑腿时间按钮进行响应
	 */
	public void purchaseChangeStep(TextView tv, Button btn, int step) {
		int addNum = Integer.valueOf(tv.getText().toString());
		addNum += step;
		tv.setText(addNum + "");
		if ((addNum == MinFeeNum && btn.getId() == R.id.btn_minus_fee) || (addNum == MinTimeNum && btn.getId() == R.id.btn_minus_arrival_time)) {
			btn.setVisibility(View.INVISIBLE);
			return;
		}

		if (btn.getId() == R.id.btn_add_fee && minusfee.getVisibility() == View.INVISIBLE) {
			minusfee.setVisibility(View.VISIBLE);
		} else if (btn.getId() == R.id.btn_add_arrival_time && minusArrivalTime.getVisibility() == View.INVISIBLE) {
			minusArrivalTime.setVisibility(View.VISIBLE);
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
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		txt_address.setText(DORMITORY_BUILDING_NAME);
	}

}
