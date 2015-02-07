package com.hust.qiudai.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.dao.IGoodsInfoDao;
import com.hust.qiudai.core.service.IGoodsInfoService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class GoodsInfoService implements IGoodsInfoService {
	@Inject
	private IGoodsInfoDao iGoodsInfoDao;

	public String getDiscountingGoodsInfoListByUniversityId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);

			int universityId = Integer.parseInt(universityIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findDiscountingGoodsInfoListByUniversityId(universityId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getDiscountingGoodsInfoListByShopsId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String shopsIdStr = request.getParameter(VariableUtil.SHOPS_ID);

			long shopsId = Long.parseLong(shopsIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findDiscountingGoodsInfoListByShopsId(shopsId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getDiscountingGoodsInfoListByUniversityIdAndCategoryId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);
			String goodsCategoryIdStr = request.getParameter(VariableUtil.GOODS_CATEGORY_ID);

			int universityId = Integer.parseInt(universityIdStr);
			int goodsCategoryId = Integer.parseInt(goodsCategoryIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findDiscountingGoodsInfoListByUniversityIdGoodsCategoryId(universityId, goodsCategoryId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getDiscountingGoodsInfoListByShopsIdAndCategoryId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String shopsIdStr = request.getParameter(VariableUtil.SHOPS_ID);
			String goodsCategoryIdStr = request.getParameter(VariableUtil.GOODS_CATEGORY_ID);

			long shopsId = Long.parseLong(shopsIdStr);
			int goodsCategoryId = Integer.parseInt(goodsCategoryIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findDiscountingGoodsInfoListByShopsIdGoodsCategoryId(shopsId, goodsCategoryId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	/*************************** 不打折 ****************************/

	public String getGoodsInfoListByUniversityId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);

			int universityId = Integer.parseInt(universityIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findGoodsInfoListByUniversityId(universityId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getGoodsInfoListByShopsId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String shopsIdStr = request.getParameter(VariableUtil.SHOPS_ID);

			int shopsId = Integer.parseInt(shopsIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findGoodsInfoListByShopsId(shopsId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getGoodsInfoListByUniversityIdAndCategoryId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);
			String goodsCategoryIdStr = request.getParameter(VariableUtil.GOODS_CATEGORY_ID);

System.out.println("高校id" + universityIdStr);
System.out.println("商品种类id" + goodsCategoryIdStr);

			long universityId = Long.parseLong(universityIdStr);
			int goodsCategoryId = Integer.parseInt(goodsCategoryIdStr);
			
System.out.println("高校id" + universityId);
System.out.println("商品种类id" + goodsCategoryId);
			
			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findGoodsInfoListByUniversityIdGoodsCategoryId(universityId, goodsCategoryId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	public String getGoodsInfoListByShopIdAndCategoryId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String shopsIdStr = request.getParameter(VariableUtil.SHOPS_ID);
			String goodsCategoryIdStr = request.getParameter(VariableUtil.GOODS_CATEGORY_ID);

			long shopsId = Long.parseLong(shopsIdStr);
			int goodsCategoryId = Integer.parseInt(goodsCategoryIdStr);

			List<GoodsInfo> goodsInfoList = iGoodsInfoDao.findGoodsInfoListByShopsIdGoodsCategoryId(shopsId, goodsCategoryId);

			return CommonUtil.getJsonStringByGson(goodsInfoList);
		} catch (Exception e) {
			System.out.println("报错信息1" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

}
