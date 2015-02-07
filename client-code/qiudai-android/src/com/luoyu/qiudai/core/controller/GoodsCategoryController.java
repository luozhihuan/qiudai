package com.luoyu.qiudai.core.controller;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.core.model.impl.GoodsCategory;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class GoodsCategoryController extends StandardController {

	public GoodsCategoryController(Context context) {
		super(context);
	}

	/**
	 * 获取饮料，零食，日用的类别链表
	 * @return
	 */
	public HashMap<Integer, List<GoodsCategory>> getGoodsCategoryListMap() {
		HashMap<Integer, List<GoodsCategory>> goodsCategoryListMap;

		Network network = Network.GetSingletonInstance(PathUtil.CATEGORY_PATH.get_URL_GOODS_CATEGORY_LIST_ARRAY_LIST(), 5);
		// network.addParms(VariableUtil.WORD_FOR_ASSCIATIVE_WORDS, word);
		String result = network.connect();
		if (commonService.isNumber(result)) {
			return null;
		} else {
			Gson gson = new Gson();
			goodsCategoryListMap = gson.fromJson(result, new TypeToken<HashMap<Integer, List<GoodsCategory>>>() {
			}.getType());
			return goodsCategoryListMap;

		}
	}

	/**
	 * 根据商品类别id获取该类别的全部商品信息
	 * @param categoryId
	 * @return
	 */
	public List<GoodsInfo> getGoodsInfoList(int categoryId) {
		List<GoodsInfo> goodsInfoList;
		Network network = Network.GetSingletonInstance(PathUtil.CATEGORY_PATH.get_URL_GET_GOODS_INFO_LIST_BY_CATEGORY_ID(), 5);
		network.addParms(VariableUtil.GOODS_CATEGORY_ID, categoryId + "");
		String result = network.connect();
		if (commonService.isNumber(result)) {
			return null;
		} else {
			Gson gson = new Gson();
			goodsInfoList = gson.fromJson(result, new TypeToken<List<GoodsInfo>>() {
			}.getType());
			return goodsInfoList;

		}
	}
}
