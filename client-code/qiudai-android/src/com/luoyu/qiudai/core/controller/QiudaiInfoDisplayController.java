package com.luoyu.qiudai.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.core.data.GoodsInfoData;
import com.luoyu.qiudai.core.data.SelectionCategoryData;
import com.luoyu.qiudai.core.model.impl.GoodsCategory;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.core.model.impl.GoodsKindCategory;
import com.luoyu.qiudai.core.model.impl.QiudaiDisplayInfo;
import com.luoyu.qiudai.core.model.impl.Shops;
import com.luoyu.qiudai.register.model.Campus;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;

/**
 * 
 * 
 * @author chuanrong
 * 
 */
public class QiudaiInfoDisplayController extends StandardController {

	private GoodsSelectController goodsSelectController;

	private static Date lastTimeOfIntialedData;

	public QiudaiInfoDisplayController(Context context) {
		super(context);
		goodsSelectController = new GoodsSelectController(context);
	}

	/**
	 * 从服务器上获取求带信息展示链表
	 * 
	 * @return 求带信息展示链表
	 */
	public List<QiudaiDisplayInfo> getQiudaiInfoDisplayList() {
		Network network = Network.NewInstance(PathUtil.get_URL_QIUDAI_DISPLAY_INFO_LIST(), 5);
		// network.addParms(VariableUtil.WORD_FOR_ASSCIATIVE_WORDS, word);
		String result = network.connect();
		if (commonService.isNumber(result)) {
			return new ArrayList<QiudaiDisplayInfo>();
		} else {
			Gson gson = new Gson();
			/** 使用Gson将json格式的字符串转换为对象类型 **/
			List<QiudaiDisplayInfo> qiudaiDisplayInfoList = gson.fromJson(result, new TypeToken<List<QiudaiDisplayInfo>>() {
			}.getType());
			return qiudaiDisplayInfoList;

		}
	}

	/**
	 * 通过本地反射的方式从服务器获取GoodsSelectActivity界面需要使用的商品类别信息以及商品信息
	 */
	public void assembelingGoodsInfoAndCategoryDateByReflect() {

		/** 访问网络等待过程中的进度框 **/
		ProgressDialog xh_ProgressDlg = ProgressDialog.show(context, "Loading...", "Please wait...", true, false);
		/** 使用反射机制进行访问网络 **/
		ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this, "initGoodsInfoAndCategoryDate", new Object[] {}, context,
				"qiudaiInfoOnShowToast", 500);
	}

	/**
	 * 两个日期类型相差小时数，返回值为正数说明date2晚于date1 返回值为负数说明date2早于date1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int theHoursGapOfTwoDates(Date date1, Date date2) {
		int yearOfDate1 = date1.getYear();
		int yearOfDate2 = date2.getYear();
		int monthOfDate1 = date1.getMonth();
		int monthOfDate2 = date2.getMonth();
		int dayOfDate1 = date1.getDay();
		int dayOfDate2 = date2.getDay();
		int hoursOfDate1 = date1.getHours();
		int hoursOfDate2 = date2.getHours();

		hoursOfDate1 += ((yearOfDate1 * 12 + monthOfDate1) * 30 + dayOfDate1) * 24;
		hoursOfDate2 += ((yearOfDate2 * 12 + monthOfDate2) * 30 + dayOfDate2) * 24;
		return hoursOfDate2 - hoursOfDate1;

	}

	/**
	 * 初始化GoodsSelectActivity中的全部数据
	 * 
	 * @return
	 */
	public int initGoodsInfoAndCategoryDate() {
		if (lastTimeOfIntialedData == null) {
			lastTimeOfIntialedData = new Date();
		}
		Date thisTimeOfIntialedData = new Date();
		int gapHours = theHoursGapOfTwoDates(lastTimeOfIntialedData, thisTimeOfIntialedData);
		lastTimeOfIntialedData = thisTimeOfIntialedData;
		if (gapHours > 5) {// 超时则重新获取数据
			return assemblingDataWhileTimeOut();
		} else {
			return assemblingDataWithOutTimeOut();
		}

	}

	/**
	 * 在不超时的情况下初始化GoodsSelectActivity中的全部数据
	 * 
	 * @return
	 */
	private int assemblingDataWithOutTimeOut() {
		if (SelectionCategoryData.GOODS_KIND_CATEGORY_LIST == null ||SelectionCategoryData.GOODS_KIND_CATEGORY_LIST.size() == 0) {
			/** 从服务器获取商品大类分类链表 **/
			String goodsKindCategoryListResult = Network.GetSingletonInstance(PathUtil.CATEGORY_PATH.get_URL_GOODS_KIND_CATEGORY_LIST(), 5).connect();
			boolean isGoodsKindCategoryAssembSuccuss = isGoodsKindCategoryListAssembledSuccuss(goodsKindCategoryListResult, gson);
			if (!isGoodsKindCategoryAssembSuccuss) {
				SelectionCategoryData.GOODS_KIND_CATEGORY_LIST = new ArrayList<GoodsKindCategory>();
			}
		}

		if (SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST == null || SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST .size() == 0) {
			/** 从服务器获取到商品分类ArrayList **/
			String goodsCategoryListArrayListResult = Network
					.GetSingletonInstance(PathUtil.CATEGORY_PATH.get_URL_GOODS_CATEGORY_LIST_ARRAY_LIST(), 5).connect();
			boolean isGoodsCategoryListArrayListAssembSuccuss = isGoodsCategoryListArrayListAssembledSuccess(goodsCategoryListArrayListResult, gson);
			if (!isGoodsCategoryListArrayListAssembSuccuss) {

				SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST = new ArrayList<List<GoodsCategory>>();
			}

		}

		if (SelectionCategoryData.CAMPUS_LIST == null || SelectionCategoryData.CAMPUS_LIST.size() == 0) {
			/** 获取校区链表 **/
			Network newtwork = Network.GetSingletonInstance(PathUtil.get_CAMPUS_LIST_BY_UNIVERSITY_ID(), 5);
			newtwork.addParms(VariableUtil.UNIVERSITY_ID, UserInfoUtil.UNIVERSITY_ID + "");
			String campusListResult = newtwork.connect();
			boolean isCampusListAssembSuccuss = isCampusListAssembledSuccuss(campusListResult, gson);

			if (!isCampusListAssembSuccuss) {
				SelectionCategoryData.CAMPUS_LIST = new ArrayList<Campus>();

			}

		}

		if (SelectionCategoryData.SHOPS_LIST_ARRAY_LIST == null || SelectionCategoryData.SHOPS_LIST_ARRAY_LIST.size() == 0) {
			/** 获取商铺小店链表 **/
			Network newtwork1 = Network.GetSingletonInstance(PathUtil.SHOPS_PATH.get_URL_SHOPS_LIST_BY_UNIVERSITY_ID(), 5);
			newtwork1.addParms(VariableUtil.UNIVERSITY_ID, UserInfoUtil.UNIVERSITY_ID + "");
			String shopsListArrayListResult = newtwork1.connect();
			boolean isShopsListArrayListAssembSuccuss = isShopsListArrayListAssembledSuccuss(shopsListArrayListResult, gson);
			if (!isShopsListArrayListAssembSuccuss) {
				SelectionCategoryData.SHOPS_LIST_ARRAY_LIST = new ArrayList<List<Shops>>();
			}

		}

		boolean isGoodsInfoListAssembSuccuss = true;
		if (GoodsInfoData.GOODS_INFO_LIST == null || GoodsInfoData.GOODS_INFO_LIST.size() == 0) {
			/** 从服务器获取本校商品信息链表 **/
			String goodsInfoListResult = getGoodsInfoListStr();
			isGoodsInfoListAssembSuccuss = isGoodsInfoListAssembledSuccuss(goodsInfoListResult, gson);
		}
		if (!isGoodsInfoListAssembSuccuss) {
			GoodsInfoData.GOODS_INFO_LIST = new ArrayList<GoodsInfo>();
			return VariableUtil.SET_GOODS_AND_CATEGORY_INFO_FAIL;
		} else {
			return VariableUtil.SET_GOODS_AND_CATEGORY_INFO_SUCCESS;
		}
	}

	/**
	 * 在超时的时候来初始化GoodsSelectActivity中的全部数据
	 * 
	 * @return
	 */
	private int assemblingDataWhileTimeOut() {
		/** 从服务器获取商品大类分类链表 **/
		String goodsKindCategoryListResult = Network.GetSingletonInstance(PathUtil.CATEGORY_PATH.get_URL_GOODS_KIND_CATEGORY_LIST(), 5).connect();
		/** 从服务器获取到商品分类ArrayList **/
		String goodsCategoryListArrayListResult = Network.GetSingletonInstance(PathUtil.CATEGORY_PATH.get_URL_GOODS_CATEGORY_LIST_ARRAY_LIST(), 5)
				.connect();
		/** 获取校区链表 **/
		Network newtwork = Network.GetSingletonInstance(PathUtil.get_CAMPUS_LIST_BY_UNIVERSITY_ID(), 5);
		newtwork.addParms(VariableUtil.UNIVERSITY_ID, UserInfoUtil.UNIVERSITY_ID + "");
		String campusListResult = newtwork.connect();

		/** 获取商铺小店链表 **/
		Network newtwork1 = Network.GetSingletonInstance(PathUtil.SHOPS_PATH.get_URL_SHOPS_LIST_BY_UNIVERSITY_ID(), 5);
		newtwork1.addParms(VariableUtil.UNIVERSITY_ID, UserInfoUtil.UNIVERSITY_ID + "");
		String shopsListArrayListResult = newtwork1.connect();

		/** 从服务器获取本校商品信息链表 **/
		String goodsInfoListResult = getGoodsInfoListStr();

		Gson gson = new Gson();

		boolean isGoodsKindCategoryAssembSuccuss = isGoodsKindCategoryListAssembledSuccuss(goodsKindCategoryListResult, gson);
		boolean isGoodsCategoryListArrayListAssembSuccuss = isGoodsCategoryListArrayListAssembledSuccess(goodsCategoryListArrayListResult, gson);
		boolean isCampusListAssembSuccuss = isCampusListAssembledSuccuss(campusListResult, gson);
		boolean isShopsListArrayListAssembSuccuss = isShopsListArrayListAssembledSuccuss(shopsListArrayListResult, gson);
		boolean isGoodsInfoListAssembSuccuss = isGoodsInfoListAssembledSuccuss(goodsInfoListResult, gson);

		if (!isGoodsKindCategoryAssembSuccuss) {
			SelectionCategoryData.GOODS_KIND_CATEGORY_LIST = new ArrayList<GoodsKindCategory>();
		}
		if (!isGoodsCategoryListArrayListAssembSuccuss) {

			SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST = new ArrayList<List<GoodsCategory>>();
		}

		if (!isCampusListAssembSuccuss) {
			SelectionCategoryData.CAMPUS_LIST = new ArrayList<Campus>();

		}
		if (!isShopsListArrayListAssembSuccuss) {
			SelectionCategoryData.SHOPS_LIST_ARRAY_LIST = new ArrayList<List<Shops>>();
		}

		if (!isGoodsInfoListAssembSuccuss) {
			GoodsInfoData.GOODS_INFO_LIST = new ArrayList<GoodsInfo>();
			return VariableUtil.SET_GOODS_AND_CATEGORY_INFO_FAIL;
		} else {
			return VariableUtil.SET_GOODS_AND_CATEGORY_INFO_SUCCESS;
		}
	}

	/**
	 * 获取筛选条件全为“全部选择”以及没有折扣的商品信息链表的json格式
	 * 
	 * @return 商品信息链表的json格式
	 */
	public String getGoodsInfoListStr() {

		Network network = goodsSelectController.getNetworkConnectionBySelectedConditions(-1L, -1L, false);
		return network.connect();
	}

	/**
	 * 超市商铺小店按校区分类的线性链表的集合是否从服务器获取并装配成功
	 * 
	 * @param shopsListArrayListResult
	 * @param gson
	 * @return
	 */
	private boolean isShopsListArrayListAssembledSuccuss(String shopsListArrayListResult, Gson gson) {
		if (!commonService.isNumber(shopsListArrayListResult)) {
			SelectionCategoryData.SHOPS_LIST_ARRAY_LIST = gson.fromJson(shopsListArrayListResult, new TypeToken<ArrayList<List<Shops>>>() {
			}.getType());
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 校区链表是否从服务器获取并装配成功
	 * 
	 * @param campusListResult
	 * @param gson
	 * @return
	 */
	private boolean isCampusListAssembledSuccuss(String campusListResult, Gson gson) {
		if (!commonService.isNumber(campusListResult)) {
			/** 初始化一个高效链表用于先装入"全部选择" **/
			List<Campus> initialCampusList = new ArrayList<Campus>();
			Campus allSelected = new Campus();
			allSelected.setCampusId(-1);
			allSelected.setCampusName("全部选择");
			allSelected.setUniversityId(-1);
			initialCampusList.add(allSelected);
			initialCampusList.addAll((List<Campus>) gson.fromJson(campusListResult, new TypeToken<List<Campus>>() {
			}.getType()));
			SelectionCategoryData.CAMPUS_LIST = initialCampusList;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * goodsInfoLisy是否成功从服务器获取并装配成功
	 * 
	 * @param goodsInfoListResult
	 *            json格式的商品信息链表
	 * @param gson
	 *            json字符串转换为对象的转换器
	 * @return true：装配成功 false：装配不成功
	 */
	private boolean isGoodsInfoListAssembledSuccuss(String goodsInfoListResult, Gson gson) {
		if (!commonService.isNumber(goodsInfoListResult)) {

			List<GoodsInfo> newGoodsInfoList = gson.fromJson(goodsInfoListResult, new TypeToken<List<GoodsInfo>>() {
			}.getType());

			GoodsInfoData.GOODS_INFO_LIST = makeSynchronizationWithShoppingTrolley(newGoodsInfoList);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 将获取的商品信息链表与当前的购物车中的数据保持购买数量的同步
	 * 
	 * @param newGoodsInfoList
	 */
	private List<GoodsInfo> makeSynchronizationWithShoppingTrolley(List<GoodsInfo> newGoodsInfoList) {
		if (GoodsInfoData.SHOPPING_TROLLEY_MAP.size() > 0) {
			for (GoodsInfo goodsInfo : newGoodsInfoList) {
				/** 如果该商品在购物车中已经存在，则在newGoodsInfoList中把该商品对应的实体换成购物车中对应的实体 **/
				GoodsInfo goodsInShoppingTrolley = GoodsInfoData.SHOPPING_TROLLEY_MAP.get(goodsInfo.getGoodsInfoId());
				if (null != goodsInShoppingTrolley) {
					System.out.println(goodsInShoppingTrolley.getGoodsName() + " " + goodsInShoppingTrolley.getGoodsBuyNum());
					goodsInfo.setGoodsBuyNum(goodsInShoppingTrolley.getGoodsBuyNum());
				}
			}
		}
		return newGoodsInfoList;
	}

	/**
	 * GoodsCategoryListMap是否成功从服务器获取并装配成功
	 * 
	 * @param goodsCategoryListResult
	 *            json格式的商品小类分类表map
	 * @param gson
	 *            json字符串转换为对象的转换器
	 * @return true：装配成功 false：装配不成功
	 */
	private boolean isGoodsCategoryListArrayListAssembledSuccess(String goodsCategoryListResult, Gson gson) {
		if (!commonService.isNumber(goodsCategoryListResult)) {
			List<List<GoodsCategory>> goodsAndCategoryInfoListArrayList = gson.fromJson(goodsCategoryListResult,
					new TypeToken<List<List<GoodsCategory>>>() {
					}.getType());

			SelectionCategoryData.GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST = goodsAndCategoryInfoListArrayList;

			return true;
		} else {
			return false;
		}

	}

	/**
	 * GoodsKindCategoryList是否成功从服务器获取并装配成功
	 * 
	 * @param goodsKindCategoryListResult
	 *            json格式的商品大类种类链表
	 * @param gson
	 *            json字符串转换为对象的转换器
	 * @return true：装配成功 false：装配不成功
	 */
	private boolean isGoodsKindCategoryListAssembledSuccuss(String goodsKindCategoryListResult, Gson gson) {
		if (!commonService.isNumber(goodsKindCategoryListResult)) {
			SelectionCategoryData.GOODS_KIND_CATEGORY_LIST = gson.fromJson(goodsKindCategoryListResult, new TypeToken<List<GoodsKindCategory>>() {
			}.getType());
			return true;
		} else {
			return false;
		}

	}

}
