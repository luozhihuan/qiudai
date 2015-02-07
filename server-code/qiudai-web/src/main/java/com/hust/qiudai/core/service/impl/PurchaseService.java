package com.hust.qiudai.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.reflect.TypeToken;
import com.hust.qiudai.core.db.GoodsIdAndBuyNum;
import com.hust.qiudai.core.db.QiudaiInfo;
import com.hust.qiudai.core.db.dao.IPurchaseDao;
import com.hust.qiudai.core.service.IPurchaseService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class PurchaseService implements IPurchaseService {

	@Inject
	private IPurchaseDao iPurchaseDao;

	public String purchaseqiudai() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String userIdStr = request.getParameter(VariableUtil.USERID);
			String goodsIdAndBuyNumListStr = request.getParameter(VariableUtil.PURCHASE_VARIABLE.GOODS_ID_AND_BUY_NUM_LIST);
			String supplyContent = request.getParameter(VariableUtil.PURCHASE_VARIABLE.SUPPLY_INFO);
			String feeStr = request.getParameter(VariableUtil.PURCHASE_VARIABLE.FEE);
			String timeStr = request.getParameter(VariableUtil.PURCHASE_VARIABLE.TIME);
			String dormitoryIdStr = request.getParameter(VariableUtil.DORMITORY_ID);

			long userId = Long.parseLong(userIdStr);
			double fee = Double.parseDouble(feeStr);
			int time = Integer.parseInt(timeStr);
			int dormitoryId = Integer.parseInt(dormitoryIdStr);

			QiudaiInfo qiudaiInfo = new QiudaiInfo();
			qiudaiInfo.setDeliveryLimitedTime(time);
			qiudaiInfo.setFee(fee);
			qiudaiInfo.setPublisherDorimitoryBuildingId(dormitoryId);
			qiudaiInfo.setPublisherId(userId);
			qiudaiInfo.setPublishTime(new Date());
			qiudaiInfo.setSupply(supplyContent);
			iPurchaseDao.addQiudaiInfo(qiudaiInfo);
			

			List<GoodsIdAndBuyNum> goodsIdAndBuyNumList = CommonUtil.fromJson(goodsIdAndBuyNumListStr, new TypeToken<List<GoodsIdAndBuyNum>>() {
			}.getType());
			

			for(GoodsIdAndBuyNum goodsIdAndBuyNum : goodsIdAndBuyNumList){
				goodsIdAndBuyNum.setQiudaiInfoId(qiudaiInfo.getQiudaiInfoId());
				iPurchaseDao.addQiudaiGoodsBuyInfo(goodsIdAndBuyNum);
			}
			
			return VariableUtil.PURCHASE_VARIABLE.PUBLISH_SUCCUSS + "";
		} catch (Exception e) {
			return VariableUtil.PURCHASE_VARIABLE.PUBLISH_FAILED + "";
		}
	}
}
