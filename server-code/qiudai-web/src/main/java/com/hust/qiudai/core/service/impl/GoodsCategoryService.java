package com.hust.qiudai.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.hust.qiudai.core.db.GoodsCategory;
import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.GoodsKindCategory;
import com.hust.qiudai.core.db.dao.IGoodsCategoryDao;
import com.hust.qiudai.core.service.IGoodsCategoryService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class GoodsCategoryService implements IGoodsCategoryService {

	@Inject
	private IGoodsCategoryDao iGoodsCategoryDao;

	public String getGoodsCategoryListArrayList() {
		try {
			List<List<GoodsCategory>> goodsCategoryListArray = getGoodsCategoryListArray();
			return CommonUtil.getJsonStringByGson(goodsCategoryListArray);
//			return goodsCategoryListArray;

		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
//			return null;
		}
	}

	public String getGoodsInfoListByCategoryId() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String goodsCategoryIdStr = request.getParameter(VariableUtil.GOODS_CATEGORY_ID);
			System.out.println(goodsCategoryIdStr);
			int goodsCategoryId = Integer.parseInt(goodsCategoryIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsCategoryDao.findGoodsInfoListByGoodsCategoryId(goodsCategoryId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getGoodsInfoList() {

		try {
System.out.println("不可思议");
			List<GoodsInfo> goodsInfoList = iGoodsCategoryDao.findGoodsInfoList();

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	/**
	 * 获取饮料饮品种类链表，小吃零食种类链表，生活日用种类链表
	 * 
	 * @return 三个种类链表合成的List
	 */
	private List<List<GoodsCategory>> getGoodsCategoryListArray() {
		
		List<GoodsCategory> allSelectedCategoryList = new ArrayList<GoodsCategory>();
		GoodsCategory goodsCategory = new GoodsCategory();
		
		
		
	//TODO	
		goodsCategory.setCategoryId(-1);
		goodsCategory.setCategoryName("全部选中");
		goodsCategory.setKindCategoryId(-1);
		
		
		
		
		
		
		
		
		
		
		
		allSelectedCategoryList.add(goodsCategory);
		
		List<GoodsCategory> drinkingCategoryList = iGoodsCategoryDao.getGoodsCategoryList(VariableUtil.DRINKING_CATEGORY_LIST_FLAG);

		List<GoodsCategory> snackCategoryList = iGoodsCategoryDao.getGoodsCategoryList(VariableUtil.SNACK_CATEGORY_LIST_FLAG);

		List<GoodsCategory> lifeusedCategoryList = iGoodsCategoryDao.getGoodsCategoryList(VariableUtil.LIFEUSED_CATEGORY_LIST_FLAG);

		/**********************************************************/
		
//		HashMap<Integer, List<GoodsCategory>> goodsCategoryListMap = new HashMap<Integer, List<GoodsCategory>>();
//		goodsCategoryListMap.put(VariableUtil.ALL_CATEGORY_LIST_FLAG, allSelectedCategoryList);
//		
//		goodsCategoryListMap.put(VariableUtil.DRINKING_CATEGORY_LIST_FLAG, drinkingCategoryList);
//
//		goodsCategoryListMap.put(VariableUtil.SNACK_CATEGORY_LIST_FLAG, snackCategoryList);
//
//		goodsCategoryListMap.put(VariableUtil.LIFEUSED_CATEGORY_LIST_FLAG, lifeusedCategoryList);
		
		List<List<GoodsCategory>> goodsCategoryListArray = new ArrayList<List<GoodsCategory>>();
		goodsCategoryListArray.add(allSelectedCategoryList);
		goodsCategoryListArray.add(drinkingCategoryList);
		goodsCategoryListArray.add(snackCategoryList);
		goodsCategoryListArray.add(lifeusedCategoryList);
		
		return goodsCategoryListArray;
	}

	public String getGoodsKindCategoryList() {

		try {
			List<GoodsKindCategory> goodsKindCategoryList = iGoodsCategoryDao.findGoodsKindCategoryList();
			return CommonUtil.getJsonStringByGson(goodsKindCategoryList);
//			return goodsKindCategoryList;
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
//			return null;
		}

	}

}
