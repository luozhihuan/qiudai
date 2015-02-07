package com.luoyu.qiudai.core.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.luoyu.qiudai.core.adapter.CampusAdapter;
import com.luoyu.qiudai.core.adapter.GoodsInfoAdapter;
import com.luoyu.qiudai.core.adapter.KindCategoryAdapter;
import com.luoyu.qiudai.core.adapter.SelectGoodsCategoryAdapter;
import com.luoyu.qiudai.core.adapter.SelectionAdapter;
import com.luoyu.qiudai.core.adapter.ShopsAdapter;
import com.luoyu.qiudai.core.adapter.SpecialOfferChoiceAdapter;
import com.luoyu.qiudai.core.adapter.SpecialOfferKindAdapter;
import com.luoyu.qiudai.core.controller.GoodsSelectController;
import com.luoyu.qiudai.core.data.GoodsInfoData;
import com.luoyu.qiudai.core.data.SelectionCategoryData;
import com.luoyu.qiudai.core.model.SelectionModel;
import com.luoyu.qiudai.core.model.impl.GoodsCategory;
import com.luoyu.qiudai.core.model.impl.Shops;
import com.luoyu.qiudai.core.model.impl.SpecialOffer;
import com.luoyu.qiudai.core.model.impl.SpecialOfferChoice;
import com.luoyu.qiudai.core.service.PopupWindowSettingService;
import com.luoyu.qiudai.standard.adapter.StandardBaseAdapter;
import com.luoyu.qiudai.util.ActivitySkipHelperUtil;
import com.luoyu.qiudai.util.ConnectNetUtil;
import com.luoyu.qiudai.util.VariableUtil;
import com.luoyu.qiudai_android.R;

public class GoodsSelectActivity extends Activity implements OnClickListener {

	private GoodsSelectController goodsSelectController;

	private LinearLayout layout_goods_info_select_finish;

	/** 结账按钮 **/
	private Button btn_goods_checkout;

	/** 商品类别控件 **/
	private TextView txt_goods_category_selection;
	/** 商铺类别控件 **/
	private TextView txt_shops_category_selection;
	/** 折扣促销活动类别控件 **/
	private TextView txt_cheap_discount_category_selection;

	/** 商品展示列表 **/
	private PullToRefreshListView pull_refresh_listview_goods;

	/** 变暗层，用于将背景变暗 **/
	private ImageView img_dark;

	private GoodsInfoAdapter goodsInfoAdapter;

	// private LayoutInflater inflater;

	private TranslateAnimation animation;

	PopupWindow mPopupWindow = null;

	private PopupWindowSettingService popupWindowSettingService;

	private TextView txt_search_for_your_want;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ConnectNetUtil.CONNECT_NET();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_select);

		init();
		setGoodsInfoListViewContentForDisplay();

		setOnClickListenerForViews();

	}

	/**
	 * 为控件设置点击监听器
	 */
	private void setOnClickListenerForViews() {
		txt_goods_category_selection.setOnClickListener(this);
		txt_shops_category_selection.setOnClickListener(this);
		txt_cheap_discount_category_selection.setOnClickListener(this);
		btn_goods_checkout.setOnClickListener(this);
	}

	/**
	 * 为选择列表设置展开时的动画效果
	 */
	private void setSelectonListViewAnimation() {
		popupWindowSettingService.setDisplayMetric(this);
		animation = popupWindowSettingService.getTranslateAnimation(0, 0, -700, 0, 250);
	}

	/**
	 * 设置商品列表的内容,并进行展示
	 */
	private void setGoodsInfoListViewContentForDisplay() {
		goodsInfoAdapter = new GoodsInfoAdapter(this, GoodsInfoData.GOODS_INFO_LIST);
		pull_refresh_listview_goods.setAdapter(goodsInfoAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		goodsSelectController = new GoodsSelectController(this);
		popupWindowSettingService = new PopupWindowSettingService();
		SelectionCategoryData.SPECIAL_OFFER_KIND_LIST = getSingletonSpecialOfferList();
		SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST = getSingletonSpecialOfferChoiceListArrayList();
		initSelectedRecord();
		initLayout();
		initSelectionAdapter();
		/**
		 * 初始化布局扩展器，用来获得布局文件 （布局xml文件）对象
		 **/
		popupWindowSettingService.getLayoutInflater(this);
		setSelectonListViewAnimation();
	}

	/**
	 * 设置选择记录
	 */
	private void initSelectedRecord() {

		int goodsCategoryNum = SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST.size();
		GOODS_CATEGORY_SELECTED_RECORD = new int[goodsCategoryNum];

		setTheFirstElmentZeroTheOthersToNegativeOne(GOODS_CATEGORY_SELECTED_RECORD);
		lastSelectedPosistionOfGoodsCategory = 0;
		/****************************************************************************/
		int numOfCampusHasShops = SelectionCategoryData.SHOPS_LIST_ARRAY_LIST.size();
		CAMPUS_SELECTED_RECORD = new int[numOfCampusHasShops];

		setTheFirstElmentZeroTheOthersToNegativeOne(CAMPUS_SELECTED_RECORD);
		lastSelectedPositionOfCampus = 0;
		/****************************************************************************/
		int numOfSpecialOffer = SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST.size();
		SPECIAL_OFFER_SELECTED_RECORD = new int[numOfSpecialOffer];

		setTheFirstElmentZeroTheOthersToNegativeOne(SPECIAL_OFFER_SELECTED_RECORD);
		lastSelectedPositionOfSpecialOffer = 0;
	}

	/**
	 * 将数组中第一个元素的值设置为0其他的值设置为-1
	 * 
	 * @param records
	 */
	public void setTheFirstElmentZeroTheOthersToNegativeOne(int[] records) {
		if (records.length > 0) {
			records[0] = 0;

		}
		if (records.length > 1) {
			for (int i = 1; i < records.length; i++) {
				records[i] = -1;
			}
		}

	}

	/**
	 * 使用单例返回SPECIAL_OFFER_LIST_MAP的数据
	 * 
	 * @return SPECIAL_OFFER_LIST_MAP
	 */
	public static List<List<SpecialOfferChoice>> getSingletonSpecialOfferChoiceListArrayList() {
		if (SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST == null) {
			SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST = new ArrayList<List<SpecialOfferChoice>>();
			SpecialOfferChoice specialOffer = new SpecialOfferChoice(1, "取消选中", false);
			SpecialOfferChoice specialOffer1 = new SpecialOfferChoice(2, "选中", true);
			List<SpecialOfferChoice> specialOfferChoicelist = new ArrayList<SpecialOfferChoice>();

			specialOfferChoicelist.add(specialOffer);
			specialOfferChoicelist.add(specialOffer1);

			SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST.add(specialOfferChoicelist);

		}
		return SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST;
	}

	/**
	 * 使用单例返回SPECIAL_OFFER_LIST的数据
	 * 
	 * @return SPECIAL_OFFER_LIST
	 */
	public static List<SpecialOffer> getSingletonSpecialOfferList() {
		if (SelectionCategoryData.SPECIAL_OFFER_KIND_LIST == null) {
			SelectionCategoryData.SPECIAL_OFFER_KIND_LIST = new ArrayList<SpecialOffer>();
			SpecialOffer specialOffer = new SpecialOffer();
			specialOffer.setSpecialOfferName("折扣活动");
			SelectionCategoryData.SPECIAL_OFFER_KIND_LIST.add(specialOffer);
			return SelectionCategoryData.SPECIAL_OFFER_KIND_LIST;

		} else {
			return SelectionCategoryData.SPECIAL_OFFER_KIND_LIST;
		}
	}

	/**
	 * 初始化控件
	 */
	private void initLayout() {
		/** 界面的三个下来选择（商品分类，超市小店，智能排序）的控件初始化 **/
		txt_goods_category_selection = (TextView) findViewById(R.id.txt_goods_category_selection);
		txt_shops_category_selection = (TextView) findViewById(R.id.txt_shops_category_selection);
		txt_cheap_discount_category_selection = (TextView) findViewById(R.id.txt_cheap_discount_category_selection);
		txt_search_for_your_want = (TextView) findViewById(R.id.txt_search_for_your_want);
		layout_goods_info_select_finish = (LinearLayout) findViewById(R.id.layout_goods_checkout_finish);
		pull_refresh_listview_goods = (PullToRefreshListView) findViewById(R.id.pull_refresh_listview_goods);
		img_dark = (ImageView) findViewById(R.id.img_dark);
		btn_goods_checkout = (Button) findViewById(R.id.btn_goods_checkout);
	}

	/**
	 * 初始化选择区域的下来链表适配器
	 */
	private void initSelectionAdapter() {
		/** 商品种类下拉链表适配器 **/
		categoryAdapter = new SelectGoodsCategoryAdapter(this);
		kindCategoryAdapter = new KindCategoryAdapter(this);

		/** 超市商铺类下拉链表适配器 **/
		campusForShopsAdapter = new CampusAdapter(this);
		shopsForShopsAdapter = new ShopsAdapter(this);

		/** 特价折扣商铺下拉链表适配器 **/
		specialOfferKindAdapter = new SpecialOfferKindAdapter(this);
		specialOfferChoiceAdapter = new SpecialOfferChoiceAdapter(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_goods_category_selection:
			showSelectionListView(txt_goods_category_selection, v.getId());
			break;
		case R.id.txt_shops_category_selection:
			showSelectionListView(txt_shops_category_selection, v.getId());
			break;
		case R.id.txt_cheap_discount_category_selection:
			showSelectionListView(txt_cheap_discount_category_selection, v.getId());
			break;
		case R.id.btn_goods_checkout:
			/** 如果购物车为空则不能进行结账 **/
			if (GoodsInfoData.SHOPPING_TROLLEY_MAP == null || GoodsInfoData.SHOPPING_TROLLEY_MAP.size() == 0) {
				Toast.makeText(getApplicationContext(), "请选择购买物品 ", Toast.LENGTH_SHORT).show();
			} else {
				ActivitySkipHelperUtil.skipCurActivityToNewActivitySideToSide(this, PurchaseActivity.class, ActivitySkipHelperUtil.LEFT_TO_RIGHT);
			}

			break;
		default:
			break;
		}
	}

	/** 左边下拉框 **/
	ListView listViewForPullDownSelectionLeft;
	/** 左边下拉框监听器 **/
	ListViewLeftOnItemClickListener listViewLeftOnItemClickListener;
	/** 右边下拉框 **/
	ListView listViewForPullDownSelectionRight;
	/** 右边下拉框监听器 **/
	ListViewRightOnItemClickListener listViewRightOnItemClickListener;

	/** 承装两个筛选下拉listview的布局控件 **/
	View viewForTwoSelectionPullDownListView = null; // 选择区域的view

	SelectGoodsCategoryAdapter categoryAdapter;
	KindCategoryAdapter kindCategoryAdapter;
	public int[] GOODS_CATEGORY_SELECTED_RECORD;
	/** 上一次具体的商品大类被选择的position值 **/
	public int lastSelectedPosistionOfGoodsCategory = -1;

	CampusAdapter campusForShopsAdapter;
	ShopsAdapter shopsForShopsAdapter;
	public int[] CAMPUS_SELECTED_RECORD;
	/** 上一次校区被选择的position值 **/
	public int lastSelectedPositionOfCampus = -1;

	SpecialOfferKindAdapter specialOfferKindAdapter;
	SpecialOfferChoiceAdapter specialOfferChoiceAdapter;
	public int[] SPECIAL_OFFER_SELECTED_RECORD;
	/** 上一次折扣被选择的position值 **/
	public int lastSelectedPositionOfSpecialOffer = -1;

	public static final int NO_SELECTED = -1;
	public long SELECTED_CATEGORY_ID = NO_SELECTED;
	public long SELECTED_SHOPS_ID = NO_SELECTED;
	public boolean IS_SEPCIAL_OFFER = false;

	/**
	 * 展示下拉链表
	 * 
	 * @param txt_selection
	 *            下拉链表会在该组件下方处展现
	 * @param text_selection_id
	 *            当前选择展示区textview组件的id
	 */
	public void showSelectionListView(TextView txt_selection, final int text_selection_id) {
		mPopupWindow = setPopupWindowEntity(mPopupWindow);
		listViewForPullDownSelectionLeft = initalPullDownListView(listViewForPullDownSelectionLeft, R.id.listview_for_pulldown_selection_left);
		listViewForPullDownSelectionRight = initalPullDownListView(listViewForPullDownSelectionRight, R.id.listview_for_pulldown_selection_right);
		setTxtSelectionIdOfLeftAndRightListViewOnItemClickListener(text_selection_id);
		setSelectionListViewContent(text_selection_id);
		popupWindowSettingService.setViewAppearAnimation(viewForTwoSelectionPullDownListView, animation);
		popupWindowSettingService.showAsDropDown(mPopupWindow, txt_selection, 0, 2);
		setImageViewDVisible();
	}

	/**
	 * 初始化下拉筛选listview的控件，如果传入的pullDownListView控件为空则进行初始化，
	 * 如果不为空则直接返回传入的控件实体pullDownListView
	 * 
	 * @param pullDownListView
	 *            传入的下拉筛选listView
	 * @param pullDownListViewId
	 *            需要初始化的下拉筛选listView控件的id，
	 *            可以根据该id来判断是初始化左边下来筛选listView还是右边的下来筛选listView
	 * @return 下来筛选listView控件实体
	 */
	public ListView initalPullDownListView(ListView pullDownListView, int pullDownListViewId) {
		if (pullDownListView == null) {
			/** 初始化pullDownListView控件 **/
			pullDownListView = (ListView) viewForTwoSelectionPullDownListView.findViewById(pullDownListViewId);
			if (pullDownListViewId == R.id.listview_for_pulldown_selection_left) {
				listViewLeftOnItemClickListener = new ListViewLeftOnItemClickListener();
				pullDownListView.setOnItemClickListener(listViewLeftOnItemClickListener);
			} else if (pullDownListViewId == R.id.listview_for_pulldown_selection_right) {
				listViewRightOnItemClickListener = new ListViewRightOnItemClickListener();
				pullDownListView.setOnItemClickListener(listViewRightOnItemClickListener);
			}
		}
		return pullDownListView;

	}

	/**
	 * 设置传入的mPopupWindow对象实体，如果mPopupWindow对象为空则进行初始化实体对象，
	 * 初始化完毕后返回，如果mPopupWindow对象不为空则直接返回
	 */
	private PopupWindow setPopupWindowEntity(PopupWindow mPopupWindow) {
		if (mPopupWindow == null) {
			mPopupWindow = initPopupWindowEntity();
			/** 为该mPopupWindow（悬浮窗口）设置一个消失的监听器 **/
			mPopupWindow.setOnDismissListener(new PopupWindowDismissListener());
		}
		return mPopupWindow;
	}

	/**
	 * 初始化一个PopupWindow实体，并返回
	 * 
	 * @return PopupWindow实体
	 */
	private PopupWindow initPopupWindowEntity() {
		PopupWindow mPopupWindow;
		viewForTwoSelectionPullDownListView = popupWindowSettingService.generateLayoutViewDirectly(GoodsSelectActivity.this,
				R.layout.layout_listview_goods_category);
		/** 根据传入的view获取一个PopupWindow对象 **/
		mPopupWindow = popupWindowSettingService.getInitedPopuWindow(viewForTwoSelectionPullDownListView, 100, 30, 100);
		return mPopupWindow;
	}

	/**
	 * 获取符合筛选条件的商品信息链表
	 * 
	 * @param categoryId
	 *            商品种类id
	 * @param shopsId
	 *            商铺id
	 * @param isSpecialOffer
	 *            是否有折扣
	 */
	public void assemblingSelectionGoodsInfoList(long categoryId, long shopsId, boolean isSpecialOffer) {
		goodsSelectController.getGoodsInfoListByReflect(categoryId, shopsId, isSpecialOffer);
	}

	/**
	 * 根据请求服务器端商品信息链表的返回结果来做相应的逻辑判断
	 * 
	 * @param resultCode
	 *            服务器端返回状态码
	 */
	public void progressDialogProcessing(int resultCode) {
		switch (resultCode) {
		case VariableUtil.GOODS_INFO_LIST_SUCCESS:

			goodsInfoAdapter.setGoodsInfoList(GoodsInfoData.GOODS_INFO_LIST);
			goodsInfoAdapter.notifyDataSetChanged();
			break;
		case VariableUtil.CONNECT_FAILED:
			Toast.makeText(getApplicationContext(), "获取失败 ", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

	}

	/**
	 * 筛选下拉ListView左半边的监听器
	 * 
	 * @author chuanrong
	 * 
	 */
	class ListViewLeftOnItemClickListener implements OnItemClickListener {
		int text_selection_id;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			switch (text_selection_id) {
			case R.id.txt_goods_category_selection:
				changeDataOfRightListView(kindCategoryAdapter, categoryAdapter, GOODS_CATEGORY_SELECTED_RECORD, position,
						SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST.get(position));

				break;
			case R.id.txt_shops_category_selection:
				changeDataOfRightListView(campusForShopsAdapter, shopsForShopsAdapter, CAMPUS_SELECTED_RECORD, position,
						SelectionCategoryData.SHOPS_LIST_ARRAY_LIST.get(position));

				break;
			case R.id.txt_cheap_discount_category_selection:
				changeDataOfRightListView(specialOfferKindAdapter, specialOfferChoiceAdapter, SPECIAL_OFFER_SELECTED_RECORD, position,
						SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST.get(position));
				break;

			default:
				break;
			}
		}

		/**
		 * 根据用户改变左边下拉列表中的选中内容，来改变右边下拉列表中的内容
		 * 
		 * @param leftAdapter
		 *            左边下拉列表的适配器
		 * @param rightAdapter
		 *            右边下拉列表的适配器
		 * @param selectedRecord
		 *            左边下拉列表中内容的选中记录数组
		 * @param position
		 *            当前用户在左边下拉列表中选择的选项所对应的位置
		 * @param list
		 *            需要在右边下拉列表中展示的数据已链表形式存储
		 */
		public void changeDataOfRightListView(StandardBaseAdapter leftAdapter, SelectionAdapter rightAdapter, int[] selectedRecord, int position,
				List list) {
			setAdapterPositionAndNotifyDataSetChanged(leftAdapter, rightAdapter, selectedRecord, position);
			rightAdapter.setList(list);
			rightAdapter.notifyDataSetInvalidated();
		}

		/**
		 * 设置两个下来listview的适配器选择position，并刷新左边下拉listview中的值
		 * 
		 * @param adapterLeft
		 *            左边下拉listview的适配器
		 * @param adapterRight
		 *            右边下来listview的适配器
		 * @param selectedRecord
		 *            左边下拉listview的选中记录
		 * @param position
		 *            当前点击的position
		 */
		private void setAdapterPositionAndNotifyDataSetChanged(StandardBaseAdapter adapterLeft, StandardBaseAdapter adapterRight,
				int[] selectedRecord, int position) {
			adapterLeft.setSelectedPosition(position);
			adapterLeft.setSelectingPosition(position);
			adapterLeft.notifyDataSetChanged();
			adapterRight.setSelectedPosition(selectedRecord[position]);
		}

		/**
		 * 获取当前选中的筛选款
		 * 
		 * @return
		 */
		public int getText_selection_id() {
			return text_selection_id;
		}

		/**
		 * 设置当前选中的筛选框（三类：商品种类；超市小店；打折活动）
		 * 
		 * @param text_selection_id
		 */
		public void setText_selection_id(int text_selection_id) {
			this.text_selection_id = text_selection_id;
		}

	}

	/**
	 * 筛选下拉ListView右半边的监听器
	 * 
	 * @author chuanrong
	 * 
	 */
	class ListViewRightOnItemClickListener implements OnItemClickListener {
		int text_selection_id;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			mPopupWindow.dismiss();
			switch (text_selection_id) {
			case R.id.txt_goods_category_selection:
				makingRecordAndSetRightListViewSelectedColor(position, txt_goods_category_selection);
				break;
			case R.id.txt_shops_category_selection:
				makingRecordAndSetRightListViewSelectedColor(position, txt_shops_category_selection);
				break;
			case R.id.txt_cheap_discount_category_selection:
				makingRecordAndSetRightListViewSelectedColor(position, txt_cheap_discount_category_selection);
				break;
			default:
				break;
			}
		}

		/**
		 * 将本次在右边下拉列表选中的内容进行记录，并将当前选中的内容颜色设置为选中状态的颜色
		 * 
		 * @param textSelectionId
		 * @param position
		 *            点击的右边下拉列表的位置
		 * @param txt_selection
		 *            三个筛选框中的一个（商品，店铺，打折信息）
		 */
		public void makingRecordAndSetRightListViewSelectedColor(int position, TextView txt_selection) {
			SelectionModel selectionModel = makingRecord(position, txt_selection);
			assemblingSelectionGoodsInfoList(SELECTED_CATEGORY_ID, SELECTED_SHOPS_ID, IS_SEPCIAL_OFFER);
			setRightListViewSelectedColor(txt_selection, selectionModel);
		}

		/**
		 * 将当前选中的内容颜色设置为选中状态的颜色
		 * 
		 * @param txt_selection
		 * @param selectionModel
		 */
		private void setRightListViewSelectedColor(TextView txt_selection, SelectionModel selectionModel) {
			if (selectionModel != null) {
				if (txt_selection.getId() == R.id.txt_cheap_discount_category_selection) {
					if (IS_SEPCIAL_OFFER) {
						txt_selection.setText(selectionModel.getSelectedName());
					} else {
						txt_selection.setText(selectionModel.getDefaultName());
					}
				} else {
					if (selectionModel.getselectedId() == -1) {
						txt_selection.setText(selectionModel.getDefaultName());
					} else {
						txt_selection.setText(selectionModel.getSelectedName());
					}
				}
			} else {
				System.out.println("为空，有问题，此处需要处理代码");
			}
		}

		/**
		 * 将本次在右边下拉列表选中的内容进行记录
		 * 
		 * @param position
		 * @param txt_selection
		 * @param selectionModel
		 * @return
		 */
		private SelectionModel makingRecord(int position, TextView txt_selection) {
			SelectionModel selectionModel = null;
			switch (txt_selection.getId()) {
			case R.id.txt_goods_category_selection:
				lastSelectedPosistionOfGoodsCategory = getTheCurrentSelctedPosition(lastSelectedPosistionOfGoodsCategory,
						GOODS_CATEGORY_SELECTED_RECORD, kindCategoryAdapter, position);
				selectionModel = SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST.get(lastSelectedPosistionOfGoodsCategory)
						.get(position);
				SELECTED_CATEGORY_ID = selectionModel.getselectedId();
				break;
			case R.id.txt_shops_category_selection:
				lastSelectedPositionOfCampus = getTheCurrentSelctedPosition(lastSelectedPositionOfCampus, CAMPUS_SELECTED_RECORD,
						campusForShopsAdapter, position);
				selectionModel = SelectionCategoryData.SHOPS_LIST_ARRAY_LIST.get(lastSelectedPositionOfCampus).get(position);
				SELECTED_SHOPS_ID = selectionModel.getselectedId();
				break;
			case R.id.txt_cheap_discount_category_selection:
				lastSelectedPositionOfSpecialOffer = getTheCurrentSelctedPosition(lastSelectedPositionOfSpecialOffer, SPECIAL_OFFER_SELECTED_RECORD,
						specialOfferKindAdapter, position);
				selectionModel = SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST.get(lastSelectedPositionOfSpecialOffer).get(position);
				if (selectionModel.getselectedId() == selectionModel.NUMBER_IS_CHOICE) {
					IS_SEPCIAL_OFFER = true;
				} else if (selectionModel.getselectedId() == selectionModel.NUMBER_IS_NOT_CHOICE) {
					IS_SEPCIAL_OFFER = false;
				}
				break;
			}
			return selectionModel;
		}

		/**
		 * 设置好左下来listView的适配器中的选择顺序，并返回这一次被选择的值
		 * 
		 * @param lastSelectedIndex
		 *            上一次被选中的
		 * @param selectedRecord
		 * @param adapterLeft
		 * @param position
		 * @return
		 */
		private int getTheCurrentSelctedPosition(int lastSelectedIndex, int[] selectedRecord, StandardBaseAdapter adapterLeft, int position) {
			if (lastSelectedIndex != -1) {
				selectedRecord[lastSelectedIndex] = -1;
			}
			int leftSelectedId = adapterLeft.getSelectedPosition();
			selectedRecord[leftSelectedId] = position;

			adapterLeft.setSelectedPosition(leftSelectedId);
			adapterLeft.setLastSelectedPosition(leftSelectedId);
			return leftSelectedId;
		}

		public int getText_selection_id() {
			return text_selection_id;
		}

		/**
		 * 设置当前选中的筛选框（三类：商品种类；超市小店；打折活动）
		 * 
		 * @param text_selection_id
		 */
		public void setText_selection_id(int text_selection_id) {
			this.text_selection_id = text_selection_id;
		}

	}

	/**
	 * 填充选择下拉链表中的数据，即设置对应适配器到选择listview中
	 * 
	 * @param text_selection_id
	 *            当前选择展示区textview组件的id
	 */
	private void setSelectionListViewContent(final int text_selection_id) {

		switch (text_selection_id) {
		case R.id.txt_goods_category_selection:
			kindCategoryAdapter.setgoodsKindCategoryList(SelectionCategoryData.GOODS_KIND_CATEGORY_LIST);

			List<GoodsCategory> goodsCategoryList = null;

			if (SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST.size() != 0) {
				goodsCategoryList = SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST.get(kindCategoryAdapter.getLastSelectedPosition());
			}
			categoryAdapter.setgoodsCategoryList(goodsCategoryList);

			setSelectionListViewContent(kindCategoryAdapter, categoryAdapter, GOODS_CATEGORY_SELECTED_RECORD);
			break;
		case R.id.txt_shops_category_selection:
			campusForShopsAdapter.setCampusList(SelectionCategoryData.CAMPUS_LIST);
			List<Shops> shopsList = null;
			if (SelectionCategoryData.SHOPS_LIST_ARRAY_LIST.size() != 0) {
				shopsList = SelectionCategoryData.SHOPS_LIST_ARRAY_LIST.get(campusForShopsAdapter.getLastSelectedPosition());
			}
			shopsForShopsAdapter.setShopsList(shopsList);
			setSelectionListViewContent(campusForShopsAdapter, shopsForShopsAdapter, CAMPUS_SELECTED_RECORD);
			// 如果上述的封装不对请解开下面的代码，下面的逻辑是正确的
			// campusForShopsAdapter.setCampusList(CAMPUS_LIST);
			// campusForShopsAdapter.setSelectedPosition(campusForShopsAdapter.getLastSelectedPosition());
			// shopsForShopsAdapter.setSelectedPosition(CAMPUS_SELECTED_RECORD[campusForShopsAdapter.selectedPosition]);
			// shopsForShopsAdapter.setShopsList(SHOPS_LIST_ARRAY_LIST.get(campusForShopsAdapter.getSelectedPosition()));
			// listViewForPullDownSelectionLeft.setAdapter(campusForShopsAdapter);
			// listViewForPullDownSelectionRight.setAdapter(shopsForShopsAdapter);
			break;
		case R.id.txt_cheap_discount_category_selection:
			specialOfferKindAdapter.setSpecialOfferKindList(SelectionCategoryData.SPECIAL_OFFER_KIND_LIST);

			List<SpecialOfferChoice> specialOfferChoiceList = null;
			if (SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST.size() != 0) {
				specialOfferChoiceList = SelectionCategoryData.SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST.get(specialOfferKindAdapter
						.getLastSelectedPosition());
			}

			specialOfferChoiceAdapter.setSpecialOfferChoiceList(specialOfferChoiceList);
			setSelectionListViewContent(specialOfferKindAdapter, specialOfferChoiceAdapter, SPECIAL_OFFER_SELECTED_RECORD);
			break;

		default:
			break;
		}
	}

	/**
	 * 填充选择下拉链表中的数据，即设置对应适配器到选择listview中
	 * 
	 * @param adapterLeft
	 * @param adapterRight
	 * @param selectedRecord
	 */
	public void setSelectionListViewContent(StandardBaseAdapter adapterLeft, StandardBaseAdapter adapterRight, int[] selectedRecord) {
		adapterLeft.setSelectedPosition(adapterLeft.getLastSelectedPosition());
		if (selectedRecord.length != 0) {
			adapterRight.setSelectedPosition(selectedRecord[adapterLeft.selectedPosition]);
		}
		listViewForPullDownSelectionLeft.setAdapter(adapterLeft);
		listViewForPullDownSelectionRight.setAdapter(adapterRight);
	}

	/**
	 * 为左右两个下拉选择listView的监听器设置当前选中的筛选框控件id
	 * 
	 * @param text_selection_id
	 *            筛选框控件id
	 */
	private void setTxtSelectionIdOfLeftAndRightListViewOnItemClickListener(final int text_selection_id) {
		listViewLeftOnItemClickListener.setText_selection_id(text_selection_id);
		listViewRightOnItemClickListener.setText_selection_id(text_selection_id);
	}

	/**
	 * 将暗背景设置为可见，呈现的效果是，呼出下拉选择菜单时，商品下来刷新listview和最下方的button 由亮变暗
	 */
	public void setImageViewDVisible() {
		img_dark.setVisibility(View.VISIBLE);
	}

	/**
	 * 将暗背景设置为不可见，呈现的效果是，收回下拉选择菜单时，商品下来刷新listview和最下方的button 由暗变亮
	 */
	public void setImageViewInvisible() {
		img_dark.setVisibility(View.INVISIBLE);
	}

	/**
	 * PopupWindow消失触发的监听器
	 * 
	 * @author chuanrong
	 * 
	 */
	class PopupWindowDismissListener implements OnDismissListener {

		@Override
		public void onDismiss() {
			setImageViewInvisible();
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

}
