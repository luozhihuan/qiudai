package com.luoyu.qiudai.core.controller;

import java.util.HashMap;
import java.util.Set;

import android.app.ProgressDialog;
import android.content.Context;

import com.luoyu.net.Network;
import com.luoyu.qiudai.core.data.GoodsInfoData;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.UserInfoUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class PurchaseController extends StandardController {

	public PurchaseController(Context context) {
		super(context);
	}

	/**
	 * 通过反射发布求带信息到服务器
	 * 
	 * @param goodsIdAndBuyNumListStr
	 *            商品id以及对应购买数量的链表json格式
	 * @param supplyInfo
	 *            补充信息
	 * @param fee
	 *            跑路费
	 * @param time
	 *            送货时长
	 */
	public void publishPurchaseInfoToServerByReflect(String goodsIdAndBuyNumListStr, String supplyInfo, String fee, String time) {
		/** 访问网络等待过程中的进度框 **/
		ProgressDialog xh_ProgressDlg = ProgressDialog.show(context, "Loading...", "Please wait...", true, false);
		/** 使用反射机制进行访问网络 **/
		ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this, "publishPurchaseInfoToServer", new Object[] { goodsIdAndBuyNumListStr,
				supplyInfo, fee, time }, context, "progressDialogProcessing", 350);
	}

	/**
	 * 发布求带信息到服务器
	 * 
	 * @param goodsIdAndBuyNumListStr
	 *            商品id以及对应购买数量的链表json格式
	 * @param supplyInfo
	 *            补充信息
	 * @param fee
	 *            跑路费
	 * @param time
	 *            送货时长
	 */
	public int publishPurchaseInfoToServer(String goodsIdAndBuyNumListStr, String supplyInfo, String fee, String time) {

		Network network = Network.GetSingletonInstance(PathUtil.PURCHASE_PATH.GET_URL_PURCHASE_QIUDAI(), 5);
		/** 用户id **/
		network.addParms(VariableUtil.USERID, UserInfoUtil.USER_ID + "");
		/** 商品id以及购买数量链表 **/
		network.addParms(VariableUtil.PURCHASE_VARIABLE.GOODS_ID_AND_BUY_NUM_LIST, goodsIdAndBuyNumListStr);
		/** 补充信息 **/
		network.addParms(VariableUtil.PURCHASE_VARIABLE.SUPPLY_INFO, supplyInfo);
		/** 跑路费 **/
		network.addParms(VariableUtil.PURCHASE_VARIABLE.FEE, fee);
		/** 送到时间 **/
		network.addParms(VariableUtil.PURCHASE_VARIABLE.TIME, time);
		/** 宿舍id **/
		network.addParms(VariableUtil.DORMITORY_ID, UserInfoUtil.DORMITORY_ID + "");
		String result = network.connect();
		if (result.equals(VariableUtil.PURCHASE_VARIABLE.PUBLISH_SUCCUSS + "")) {
			return VariableUtil.PURCHASE_VARIABLE.PUBLISH_SUCCUSS;
		} else if (result.equals(VariableUtil.PURCHASE_VARIABLE.PUBLISH_FAILED + "")) {
			return VariableUtil.PURCHASE_VARIABLE.PUBLISH_FAILED;
		} else {
			return VariableUtil.CONNECT_FAILED;
		}
	}

	/**
	 * 先将购物车中的商品的购物数量设置为0，再将购物车清空，清空购物车中的数据
	 */
	public void clearTheShoppingTrolleyMap() {

		Set<Long> keySet = GoodsInfoData.SHOPPING_TROLLEY_MAP.keySet();
		for (Long goodsInfoId : keySet) {
			GoodsInfoData.SHOPPING_TROLLEY_MAP.get(goodsInfoId).setGoodsBuyNum(0);
		}

		GoodsInfoData.SHOPPING_TROLLEY_MAP = new HashMap<Long, GoodsInfo>();

	}

}
