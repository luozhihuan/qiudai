package com.luoyu.qiudai.core.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luoyu.qiudai.core.adapter.SingleQiudaiInfoAdapter;
import com.luoyu.qiudai.core.controller.SingleQiudaiInfoDisplayController;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.core.model.impl.QiudaiDisplayInfo;
import com.luoyu.qiudai.core.service.LimitedTimeForDisplayService;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.ImageLoaderUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 用于展示单个求带订单的界面
 * 
 * @author chuanrong
 * 
 */
public class SingleQiudaiInfoDisplayActivity extends Activity implements OnClickListener {

	private ListView listview_publisher_qiudai_goods_info;

	// public static long QIUDAI_INFO_ID;

	public static QiudaiDisplayInfo QIUDAI_INFO;

	private SingleQiudaiInfoDisplayController singleQiudaiInfoDisplayController;

	private ImageView img_publisher_head_url;
	private TextView txt_publisher_nickname;
	private TextView txt_publisher_phone_number;
	private TextView txt_publisher_address;
	private TextView txt_fee;
	private TextView txt_delivery_limited_time;

	private Button btn_goods_checkout;

	private ImageLoader imageLoader;

	private DisplayImageOptions options;

	private LimitedTimeForDisplayService limitedTimeForDisplayService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ConnectNetUtil.CONNECT_NET();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_qiudai_info_display);
		init();

		List<GoodsInfo> qiudaiGoodsInfoList = getPublisherQiudaiInfo(QIUDAI_INFO.getQiudaiDisplayInfoId());

		SingleQiudaiInfoAdapter singleQiudaiInfoAdapter = new SingleQiudaiInfoAdapter(this, qiudaiGoodsInfoList);
		listview_publisher_qiudai_goods_info.setAdapter(singleQiudaiInfoAdapter);

		imageLoader.displayImage(QIUDAI_INFO.getPublisherHeadImgUrl(), img_publisher_head_url, options);
		txt_publisher_nickname.setText("用户：" + QIUDAI_INFO.getPublisherName());
		txt_publisher_phone_number.setText("电话：" + QIUDAI_INFO.getPublisherPhoneNumber());
		txt_publisher_address.setText("求带地址：" + QIUDAI_INFO.getPublisherAddress());
		txt_fee.setText("跑路费：" + QIUDAI_INFO.getFee() + "元");

		String deliveryLimitedTime = limitedTimeForDisplayService.getLimitedTimeForDisplay(QIUDAI_INFO.getPublishTime(),
				QIUDAI_INFO.getDiliveryLimitedTime());

		txt_delivery_limited_time.setText(deliveryLimitedTime + "前送到");
		btn_goods_checkout.setOnClickListener(this);

	}

	/**
	 * 初始化控件和一些变量
	 */
	private void init() {
		imageLoader = ImageLoaderUtil.initImaeLoaderAndConfigured(this);
		options = ImageLoaderUtil.BuilderDisplayImageOptions();

		img_publisher_head_url = (ImageView) findViewById(R.id.img_publisher_head_url);
		txt_publisher_nickname = (TextView) findViewById(R.id.txt_publisher_nickname);
		txt_publisher_phone_number = (TextView) findViewById(R.id.txt_publisher_phone_number);
		txt_publisher_address = (TextView) findViewById(R.id.txt_publisher_address);
		txt_fee = (TextView) findViewById(R.id.txt_fee);
		txt_delivery_limited_time = (TextView) findViewById(R.id.txt_delivery_limited_time);
		btn_goods_checkout = (Button) findViewById(R.id.btn_goods_checkout);

		listview_publisher_qiudai_goods_info = (ListView) findViewById(R.id.listview_publisher_qiudai_goods_info);
		limitedTimeForDisplayService = LimitedTimeForDisplayService.getSingletonStance();
		singleQiudaiInfoDisplayController = new SingleQiudaiInfoDisplayController(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_goods_checkout:

			long receiverId = UserInfoUtil.USER_ID;
			long qiudaiInfoId = QIUDAI_INFO.getQiudaiDisplayInfoId();

			takeTheQiudaiInfoOrder(receiverId, qiudaiInfoId);

			break;

		default:
			break;
		}

	}

	/**
	 * 进行接单
	 * 
	 * @param receiverId
	 *            接单者id
	 * @param qiudaiInfoId
	 *            订单id
	 */
	public void takeTheQiudaiInfoOrder(long receiverId, long qiudaiInfoId) {
		singleQiudaiInfoDisplayController.takeTheQiudaiInfoOrderByRelect(receiverId, qiudaiInfoId);
	}

	/**
	 * 根据访问网络后返回的状态码进行相应的操作
	 * 
	 * @param resultCode
	 *            状态码
	 */
	public void resultCodeOnShowToast(int resultCode) {

		if (resultCode == VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_SUCCESS) {

			// TODO 此处应该跳转到接单成功后的商品表里
			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSideWithFinishCurActivity(this, QiudaiInfoDisplayActivity.class,
					ActivitySkipHelperUtil.RIGHT_TO_LEFT);
			Toast.makeText(getApplicationContext(), "接单成功 ", Toast.LENGTH_SHORT).show();
		} else if (resultCode == VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_FAIL) {
			Toast.makeText(getApplicationContext(), "接单失败 ", Toast.LENGTH_SHORT).show();
		} else if (resultCode == VariableUtil.CONNECT_FAILED) {
			Toast.makeText(getApplicationContext(), "网络连接失败 ", Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * 获取一个求带信息
	 * 
	 * @param qiudaiInfoId
	 */
	public List<GoodsInfo> getPublisherQiudaiInfo(long qiudaiInfoId) {
		return singleQiudaiInfoDisplayController.getGoodsInfoListFromServer(qiudaiInfoId);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 点击返回按钮 **/
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// CommonUtil.intentStartActivityRightToLeftWithOutFinish(SingleQiudaiInfoDisplayActivity.this,
			// QiudaiInfoDisplayActivity.class);

			ActivitySkipHelperUtil.finishCurActivityBackToPrevActivitySideToSide(this, ActivitySkipHelperUtil.RIGHT_TO_LEFT);
		}
		return super.onKeyDown(keyCode, event);
	}

}
