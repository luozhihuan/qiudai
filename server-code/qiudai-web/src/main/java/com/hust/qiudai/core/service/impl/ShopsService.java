package com.hust.qiudai.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.Shops;
import com.hust.qiudai.core.db.dao.IShopsDao;
import com.hust.qiudai.core.service.IShopsService;
import com.hust.qiudai.web.db.Campus;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class ShopsService implements IShopsService {

	@Inject
	private IShopsDao iShopsDao;

	public String getShopsListGroupByCampusByUniversityId() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();
			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);
			int universityId = Integer.parseInt(universityIdStr);
			List<Shops> shopsList = iShopsDao.findShopsListByUniversityId(universityId);

			List<List<Shops>> shopsListArryList = getShopsArrayListGroupByCampusId(shopsList);

			String result = CommonUtil.getJsonStringByGson(shopsListArryList);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			System.out.println("报错执行" + e);
			return VariableUtil.CONNECT_FAILED + "";
		}

	}

	/**
	 * 将一个学校里的全部商铺小店链表，以所属campusid来进行分类，
	 * 同属一个campusid的商铺小店封装在一个List中，对应的key是从0开始递增到campusid的数量减1
	 * 
	 * @param shopsList
	 *            一个学校里的全部商铺小店
	 * @return 封装好的商铺小店链表的线性链表集合
	 */
	private List<List<Shops>> getShopsArrayListGroupByCampusId(List<Shops> shopsList) {
//		HashMap<Integer, List<Shops>> shopsListMap = new HashMap<Integer, List<Shops>>();
		
		List<List<Shops>> shopsListArrayList = new ArrayList<List<Shops>>();
		List<Shops> allSelectedList = new ArrayList<Shops>();
		Shops allSelectedShops = new Shops();
		allSelectedShops.setCampusId(-1);
		allSelectedShops.setShopsId(-1);
		allSelectedShops.setShopsName("选中");
		allSelectedList.add(allSelectedShops);
		shopsListArrayList.add(allSelectedList);
		
		int flag = Integer.MIN_VALUE;
		Integer shopsListMapKey = 0;
		for (Shops shops : shopsList) {
			if (shops.getCampusId() != flag) {
				shopsListMapKey++;
				List<Shops> list = new ArrayList<Shops>();
				list.add(shops);
				shopsListArrayList.add(list);
				flag = shops.getCampusId();
			} else {
				shopsListArrayList.get(shopsListMapKey).add(shops);
			}

		}
		return shopsListArrayList;
	}


	public String getGoodsInfoListByShopsId() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();
			String shopsIdStr = request.getParameter(VariableUtil.SHOPS_ID);
			long shopsid = Long.parseLong(shopsIdStr);
			List<GoodsInfo> shopsList = iShopsDao.findGoodsInfoListByShopsId(shopsid);
			String result = CommonUtil.getJsonStringByGson(shopsList);
			return result;
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}

	}

	public String getDiscountingGoodsInfoListByShopsId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();
			String shopsIdStr = request.getParameter(VariableUtil.SHOPS_ID);
			long shopsid = Long.parseLong(shopsIdStr);
			List<GoodsInfo> shopsList = iShopsDao.findDiscountingGoodsInfoListByShopsId(shopsid);
			String result = CommonUtil.getJsonStringByGson(shopsList);
			return result;
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

	
	
}
