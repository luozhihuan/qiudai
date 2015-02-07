package com.hust.qiudai.core.service.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.hust.qiudai.core.db.QiudaiOrder;
import com.hust.qiudai.core.db.dao.IOrderReceivingDao;
import com.hust.qiudai.core.service.IOrderReceivingService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class OrderReceivingService implements IOrderReceivingService {

	@Inject
	private IOrderReceivingDao iOrderRecivingDao;

	
	/**
	 * 
	 */
	public synchronized String orderReceiving() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();
			String orderReciverIdStr = request.getParameter(VariableUtil.USERID);
			String qiudaiInfoIdStr = request.getParameter(VariableUtil.QIUDAI_INFO_ID);

			long orderReciverId = Long.parseLong(orderReciverIdStr);
			long qiudaiInfoId = Long.parseLong(qiudaiInfoIdStr);

			QiudaiOrder qiudaiOrder = new QiudaiOrder();
			qiudaiOrder.setOrderReceiverId(orderReciverId);
			qiudaiOrder.setQiudaiInfoId(qiudaiInfoId);
			qiudaiOrder.setOrderReceivingTime(new Date());
			
			iOrderRecivingDao.addQiudaiOrder(qiudaiOrder);
			iOrderRecivingDao.updateTheQiudaiInfoToBeReceivied(qiudaiInfoId);
			
			return VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_SUCCESS + "";

		} catch (Exception E) {
			return VariableUtil.CONNECT_FAILED + "";
		}

	}

}
