package com.luoyu.qiudai.core.controller;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.core.activity.GoodsSelectActivity;
import com.luoyu.qiudai.core.adapter.GoodsInfoAdapter;
import com.luoyu.qiudai.core.data.GoodsInfoData;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class GoodsSelectController extends StandardController {

	public GoodsSelectController(Context context) {
		super(context);
	}

	/**
	 * 通过反射机制获取符合筛选条件的商品信息链表
	 * 
	 * @param goodsCategoryId
	 *            商品种类id
	 * @param shopsId
	 *            商铺id
	 * @param isSpecialOffer
	 *            是否有折扣
	 */
	public void getGoodsInfoListByReflect(long goodsCategoryId, long shopsId, boolean isSpecialOffer) {
		/** 访问网络等待过程中的进度框 **/
		ProgressDialog xh_ProgressDlg = ProgressDialog.show(context, "Loading...", "Please wait...", true, false);
		/** 使用反射机制进行访问网络 **/
		ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this, "getGoodsInfoList", new Object[] { goodsCategoryId, shopsId, isSpecialOffer },
				context, "progressDialogProcessing", 350);
	}

	/**
	 * 从服务器端获取符合筛选条件的商品信息链表
	 * 
	 * @param goodsCategoryId
	 *            商品种类id
	 * @param shopsId
	 *            商铺id
	 * @param isSpecialOffer
	 *            是否有折扣
	 * @return 状态码
	 */
	public int getGoodsInfoList(Long goodsCategoryId, Long shopsId, Boolean isSpecialOffer) {
		System.out.println(goodsCategoryId + " " + shopsId + " " + isSpecialOffer);
		Network network = getNetworkConnectionBySelectedConditions(goodsCategoryId, shopsId, isSpecialOffer);
		String goodsInfoListResult = network.connect();
		if (!commonService.isNumber(goodsInfoListResult)) {
			List<GoodsInfo> newGoodsInfoList = gson.fromJson(goodsInfoListResult, new TypeToken<List<GoodsInfo>>() {
			}.getType());

			GoodsInfoData.GOODS_INFO_LIST = makeSynchronizationWithShoppingTrolley(newGoodsInfoList);
			;
			return VariableUtil.GOODS_INFO_LIST_SUCCESS;
		} else {
			return VariableUtil.CONNECT_FAILED;
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
					goodsInfo.setGoodsBuyNum(goodsInShoppingTrolley.getGoodsBuyNum());
				}
			}
		}

		return newGoodsInfoList;
	}

	/**
	 * 根据筛选条件，获取一个链接网络的Network对象实体
	 * 
	 * @param goodsCategoryId
	 *            商品种类id
	 * @param shopsId
	 *            商铺id
	 * @param isSpecialOffer
	 *            是否折扣
	 * @return 网络连接对象实体
	 */
	public Network getNetworkConnectionBySelectedConditions(Long goodsCategoryId, Long shopsId, Boolean isSpecialOffer) {
		/** 获取网络连接实体 **/
		Network network = Network.GetSingletonInstance(null, 5);
		/** 设置网络连接实体的访问url路径 **/
		String url = getUrlBySelectedConditions(goodsCategoryId, shopsId, isSpecialOffer);
		network.setUrl(url);
		/** 设置网络连接实体的参数 **/
		network = setParamsForNetworkBySelectedConditions(goodsCategoryId, shopsId, isSpecialOffer, network);
		return network;
	}

	/**
	 * 根据筛选条件获取访问的url
	 * 
	 * @param goodsCategoryId
	 *            商品种类id
	 * @param shopsId
	 *            商铺id
	 * @param isSpecialOffer
	 *            是否折扣
	 * @return url
	 */
	private String getUrlBySelectedConditions(Long goodsCategoryId, Long shopsId, Boolean isSpecialOffer) {
		if (goodsCategoryId == GoodsSelectActivity.NO_SELECTED && shopsId == GoodsSelectActivity.NO_SELECTED) {

			return isSpecialOffer ? PathUtil.GOODS_INFO_PATH.GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID() : PathUtil.GOODS_INFO_PATH
					.GET_URL_GOODS_INFO_LIST_BY_UNIVERSITY_ID();

		} else if (goodsCategoryId != GoodsSelectActivity.NO_SELECTED && shopsId == GoodsSelectActivity.NO_SELECTED) {

			return isSpecialOffer ? PathUtil.GOODS_INFO_PATH.GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID()
					: PathUtil.GOODS_INFO_PATH.GET_URL_GOODS_INFO_LIST_BY_UNIVERSITY_ID_AND_CATEGORY_ID();

		} else if (goodsCategoryId == GoodsSelectActivity.NO_SELECTED && shopsId != GoodsSelectActivity.NO_SELECTED) {
			return isSpecialOffer ? PathUtil.GOODS_INFO_PATH.GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID() : PathUtil.GOODS_INFO_PATH
					.GET_URL_GOODS_INFO_LIST_BY_SHOPS_ID();

		} else if (goodsCategoryId != GoodsSelectActivity.NO_SELECTED && shopsId != GoodsSelectActivity.NO_SELECTED) {

			return isSpecialOffer ? PathUtil.GOODS_INFO_PATH.GET_URL_DISCOUNTING_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID()
					: PathUtil.GOODS_INFO_PATH.GET_URL_GOODS_INFO_LIST_BY_SHOPS_ID_AND_CATEGORY_ID();

		} else {
			return null;
		}

	}

	/**
	 * 为传入的网络连接实体network根据筛选条件设置参数
	 * 
	 * @param goodsCategoryId
	 *            商品种类id
	 * @param shopsId
	 *            商铺id
	 * @param isSpecialOffer
	 *            是否折扣
	 * @param network
	 *            网络连接实体
	 */
	private Network setParamsForNetworkBySelectedConditions(Long goodsCategoryId, Long shopsId, Boolean isSpecialOffer, Network network) {
		if (goodsCategoryId != null && shopsId != null && isSpecialOffer != null && network != null) {
			if (goodsCategoryId == GoodsSelectActivity.NO_SELECTED && shopsId == GoodsSelectActivity.NO_SELECTED) {
				network.addParms(VariableUtil.UNIVERSITY_ID, UserInfoUtil.UNIVERSITY_ID + "");
			} else if (goodsCategoryId != GoodsSelectActivity.NO_SELECTED && shopsId == GoodsSelectActivity.NO_SELECTED) {
				network.addParms(VariableUtil.GOODS_CATEGORY_ID, goodsCategoryId + "");
				network.addParms(VariableUtil.UNIVERSITY_ID, UserInfoUtil.UNIVERSITY_ID + "");
			} else if (goodsCategoryId == GoodsSelectActivity.NO_SELECTED && shopsId != GoodsSelectActivity.NO_SELECTED) {
				network.addParms(VariableUtil.SHOPS_ID, shopsId + "");
			} else if (goodsCategoryId != GoodsSelectActivity.NO_SELECTED && shopsId != GoodsSelectActivity.NO_SELECTED) {
				network.addParms(VariableUtil.GOODS_CATEGORY_ID, goodsCategoryId + "");
				network.addParms(VariableUtil.SHOPS_ID, shopsId + "");
			}

			return network;
		}
		return network;
	}
}
