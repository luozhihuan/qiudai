package com.hust.qiudai.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.QiudaiDisplayInfo;
import com.hust.qiudai.core.db.dao.IQiudaiDisplayInfoDao;
import com.hust.qiudai.core.service.IQiudaiDisplayInfoService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class QiudaiDisplayInfoService implements IQiudaiDisplayInfoService {

	@Inject
	private IQiudaiDisplayInfoDao iQiudaiDisplayInfoDao;

	public String getQiudaiDisplayInfoList() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			List<QiudaiDisplayInfo> qiudaiDisplayInfoList = iQiudaiDisplayInfoDao.getQiudaiDisplayInfoList();

			if (isTheListHasData(qiudaiDisplayInfoList)) {
				return CommonUtil.getJsonStringByGson(qiudaiDisplayInfoList);
			} else {
				return VariableUtil.QIUDAI_DISPLAY_INFO_LIST_IS_NULL + "";
			}

		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}

	}
	
	
	
	public String getQiudaiGoodsInfoList() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String qiudaiInfoIdStr = request.getParameter(VariableUtil.QIUDAI_INFO_ID);

			long qiudaiInfoId = Long.parseLong(qiudaiInfoIdStr);

			List<GoodsInfo> goodsInfoList = iQiudaiDisplayInfoDao.getQiudaiGoodsInfoList(qiudaiInfoId);

			if (isTheListHasData(goodsInfoList)) {
				return CommonUtil.getJsonStringByGson(goodsInfoList);
			} else {
				return VariableUtil.QIUDAI_DISPLAY_INFO_LIST_IS_NULL + "";
			}

		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}

	}

	/**
	 * 链表list中是否含有数据
	 * 
	 * @param list
	 * @return true：有数据 false：没有数据或链表list为空
	 */
	public boolean isTheListHasData(List list) {
		if (list != null && list.size() != 0) {
			return true;
		} else {
			return false;
		}
	}
}
