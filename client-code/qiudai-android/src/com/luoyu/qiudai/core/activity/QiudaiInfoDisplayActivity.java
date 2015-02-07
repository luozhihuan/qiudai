package com.luoyu.qiudai.core.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.luoyu.qiudai.core.adapter.QiudaiInfoAdapter;
import com.luoyu.qiudai.core.controller.QiudaiInfoDisplayController;
import com.luoyu.qiudai.core.model.impl.QiudaiDisplayInfo;
import com.luoyu.qiudai.register.activity.LoginActivity;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

/**
 * 用于展现求带信息链表的界面
 * 
 * @author chuanrong
 * 
 */
public class QiudaiInfoDisplayActivity extends Activity implements OnClickListener {

	private QiudaiInfoDisplayController qiudaiDisplayInfoController;

	private PullToRefreshListView pullToRefreshForGoodsInfoListView;
	private QiudaiInfoAdapter qiudaiInfoAdapter;

	private int SET_REFRESH_IMAGE_VISIBLE = 1;
	private int SET_QIUDAI_DISPLAY_INFO_LIST_VISIBLE = 0;

	/** 进入商品选择界面的按钮 **/
	private Button btn_goods_info_select_finish;

	private ImageView img_refresh;

	/** 求带信息链表 **/
	private List<QiudaiDisplayInfo> qiudaiDisplayInfoList;

	private ImageButton img_btn_more;

	private SlidingMenu slidingMenu = null;

	private RelativeLayout layout_qiudai_record;

	private RelativeLayout layout_user_info_modify;

	private RelativeLayout layout_setting;

	private RelativeLayout layout_about_us;

	private RelativeLayout layout_quite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ConnectNetUtil.CONNECT_NET();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qiudai_info_display);

		init();

		qiudaiDisplayInfoList = getQiudaiInfoDisplayList();

		setQiudaiDisplayData(qiudaiDisplayInfoList);

		setListener();

	}

	/**
	 * 设置监听器
	 */
	private void setListener() {

		btn_goods_info_select_finish.setOnClickListener(this);
		img_btn_more.setOnClickListener(this);
		layout_qiudai_record.setOnClickListener(this);
		layout_user_info_modify.setOnClickListener(this);
		layout_quite.setOnClickListener(this);
		layout_about_us.setOnClickListener(this);
		layout_setting.setOnClickListener(this);

		pullToRefreshForGoodsInfoListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 此处很奇怪为什么需要减一
				int positionInDisplayInfoList = position - 1;
				SingleQiudaiInfoDisplayActivity.QIUDAI_INFO = qiudaiInfoAdapter.getItem(positionInDisplayInfoList);

				ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(QiudaiInfoDisplayActivity.this, SingleQiudaiInfoDisplayActivity.class,
						ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_goods_checkout:

			initGoodsInfoDataOfGoodsSelectActivity();

			break;
		case R.id.img_btn_more:
			/** 点击“更多”标记，侧滑获取更多信息 **/
			slidingMenuGetMore();
			break;

		case R.id.layout_qiudai_record:
			/** 点击求带订单按钮 **/
			clickTheQiudaiRecordLayout();
			break;

		case R.id.layout_user_info_modify:
			/** 点击修改用户信息按钮 **/
			clickTheQiudaiRecordLayout();
			break;
		case R.id.layout_quite:
			/** 点击退出按钮 **/
			clickTheQuiteLayout();
			break;
		case R.id.layout_about_us:
			/** 点击修改用户信息按钮 **/
			clickTheAboutUsLayout();
			break;
		case R.id.layout_setting:
			/** 点击修改用户信息按钮 **/
			clickTheQiudaiRecordLayout();
			break;
		default:
			break;
		}

	}

	/**
	 * 在侧滑栏点击关于我们按钮
	 */
	private void clickTheAboutUsLayout() {
		ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(QiudaiInfoDisplayActivity.this, AboutUsActivity.class,
				ActivitySkipHelperUtil.LEFT_TO_RIGHT);
	}

	/**
	 * 在侧滑栏点击退出按钮
	 */
	private void clickTheQuiteLayout() {
		ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(QiudaiInfoDisplayActivity.this, LoginActivity.class,
				ActivitySkipHelperUtil.LEFT_TO_RIGHT);

	}

	/**
	 * 在侧滑栏点击求带订单按钮
	 */
	private void clickTheQiudaiRecordLayout() {
		System.out.println("点击成功求带订单展现");

	}

	/**
	 * 点击“更多”标记，侧滑获取更多信息
	 */
	public void slidingMenuGetMore() {

		slidingMenu.showMenu();
		// slidingMenu.showSecondaryMenu();

	}

	/**
	 * 为GoodsSelectActivity界面（该页面点击按钮后跳转的界面）装配需要使用的商品类别信息以及商品信息
	 */
	public void initGoodsInfoDataOfGoodsSelectActivity() {
		qiudaiDisplayInfoController.assembelingGoodsInfoAndCategoryDateByReflect();
	}

	/**
	 * 根据对GoodsSelectActivity中四个list的装配情况进行toast的展现
	 * 
	 * @param resultCode
	 */
	public void qiudaiInfoOnShowToast(int resultCode) {

		switch (resultCode) {
		case VariableUtil.SET_GOODS_AND_CATEGORY_INFO_SUCCESS:

			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, GoodsSelectActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			Toast.makeText(getApplicationContext(), "成功成功 ", Toast.LENGTH_SHORT).show();
			break;
		case VariableUtil.SET_GOODS_AND_CATEGORY_INFO_FAIL:

			ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, GoodsSelectActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			Toast.makeText(getApplicationContext(), "失败失败 ", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

	/**
	 * 将获取的求带展现信息链表装配入下来 刷新listview中（pullToRefreshListView），如果
	 * 求带展现信息链表的内容为空，则把界面中下来刷新listview 的布局设置为一个刷新图标
	 * 
	 * @param qiudaiDisplayInfoList
	 *            求带展现信息链表
	 */
	private void setQiudaiDisplayData(List<QiudaiDisplayInfo> qiudaiDisplayInfoList) {
		/** 判断链表中是否有数据 **/
		if (isTheListHasData(qiudaiDisplayInfoList)) {
			/** 初始化求带信息展示内容的适配器 **/
			qiudaiInfoAdapter = new QiudaiInfoAdapter(this, qiudaiDisplayInfoList);
			/** 设置适配器 **/
			pullToRefreshForGoodsInfoListView.setAdapter(qiudaiInfoAdapter);
			switchTheImgAndListVisible(SET_QIUDAI_DISPLAY_INFO_LIST_VISIBLE);
		} else {
			switchTheImgAndListVisible(SET_REFRESH_IMAGE_VISIBLE);
		}
	}

	/**
	 * 通过传递的参数whichOneOpen来设置pullToRefreshListView和 img_refresh这两个组件谁可见谁不可见
	 * 
	 * @param whichOneOpen
	 *            ：当参数为SET_REFRESH_IMAGE_VISIBLE时，
	 *            img_refresh可见，pullToRefreshListView不可见
	 *            当参数为SET_QIUDAI_DISPLAY_INFO_LIST_VISIBLE时，img_refresh不可见
	 *            pullToRefreshListView可见
	 */
	public void switchTheImgAndListVisible(int whichOneOpen) {
		if (whichOneOpen == SET_REFRESH_IMAGE_VISIBLE) {
			pullToRefreshForGoodsInfoListView.setVisibility(View.INVISIBLE);
			img_refresh.setVisibility(View.VISIBLE);
		} else if (whichOneOpen == SET_QIUDAI_DISPLAY_INFO_LIST_VISIBLE) {
			img_refresh.setVisibility(View.INVISIBLE);
			pullToRefreshForGoodsInfoListView.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 链表是否有数据
	 * 
	 * @param list
	 * @return true：有数据 ;false：没数据
	 */
	public boolean isTheListHasData(List list) {
		if (list != null && list.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 初始化
	 */
	private void init() {

		qiudaiDisplayInfoController = new QiudaiInfoDisplayController(this);
		/** 获取下来刷新listview的对象 **/
		pullToRefreshForGoodsInfoListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_listview_goods);
		img_refresh = (ImageView) findViewById(R.id.img_refresh);

		btn_goods_info_select_finish = (Button) findViewById(R.id.btn_goods_checkout);
		img_btn_more = (ImageButton) findViewById(R.id.img_btn_more);

		setSlidingMenuSetting();

	}

	/**
	 * 设置侧滑选项配置
	 */
	private void setSlidingMenuSetting() {
		// 设置抽屉菜单
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		// 触摸边界拖出菜单
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setMenu(R.layout.slidingmenu_more);
		slidingMenu.setSecondaryMenu(R.layout.slidingmenu_right);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setFadeEnabled(true);
		// 将抽屉菜单与主页面关联起来
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		/** 侧滑后按钮 **/
		layout_qiudai_record = (RelativeLayout) findViewById(R.id.layout_qiudai_record);
		layout_user_info_modify = (RelativeLayout) findViewById(R.id.layout_user_info_modify);
		initSlidingMenuLayout();
	}

	/**
	 * 初始化侧滑栏上的组件，初始化工作一定要在设置侧滑选项配置后来进行
	 */
	private void initSlidingMenuLayout() {
		/** 侧滑后按钮 **/
		layout_qiudai_record = (RelativeLayout) findViewById(R.id.layout_qiudai_record);
		layout_user_info_modify = (RelativeLayout) findViewById(R.id.layout_user_info_modify);
		layout_setting = (RelativeLayout) findViewById(R.id.layout_setting);
		layout_about_us = (RelativeLayout) findViewById(R.id.layout_about_us);
		layout_quite = (RelativeLayout) findViewById(R.id.layout_quite);
	}

	/**
	 * 获取求带信息展示链表
	 * 
	 * @return 求带信息展示链表
	 */
	public List<QiudaiDisplayInfo> getQiudaiInfoDisplayList() {

		return getQiudaiInfoDisplayListFromServer();

	}

	/**
	 * 从服务器端获取求带信息展示链表的数据
	 * 
	 * @return 求带信息展示链表
	 */
	public List<QiudaiDisplayInfo> getQiudaiInfoDisplayListFromServer() {
		return qiudaiDisplayInfoController.getQiudaiInfoDisplayList();
	}

	/**
	 * 本地生产求带信息展示链表的测试数据
	 * 
	 * @param num
	 *            求带信息展示链表中数据的条数
	 * @return 求带信息展示链表
	 */
	public List<QiudaiDisplayInfo> generateQiudaiInfoDisplayInfoForTest(int num) {
		List<QiudaiDisplayInfo> list = new ArrayList<QiudaiDisplayInfo>();
		QiudaiDisplayInfo qiudaiDisplayInfo = new QiudaiDisplayInfo();
		qiudaiDisplayInfo.setDiliveryLimitedTime(1);
		qiudaiDisplayInfo.setFee(2.0);
		qiudaiDisplayInfo.setPublisherAddress("华中科技大学");
		qiudaiDisplayInfo.setPublisherHeadImgUrl("http://d.hiphotos.baidu.com/zhidao/pic/item/5d6034a85edf8db1a84072cf0923dd54564e742a.jpg");

		for (int i = 0; i < num; i++) {
			list.add(qiudaiDisplayInfo);
		}
		return list;
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
