package com.hust.qiudai.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.google.gson.Gson;
import com.hust.qiudai.web.db.Campus;
import com.hust.qiudai.web.db.dao.IRegisterDao;
import com.hust.qiudai.web.service.ICampusService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class CampusService implements ICampusService {

	@Inject
	private IRegisterDao iRegisterDao;

	public String getCampusListByUniversityId() {

		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);

			int universityId = -1;
			if (universityIdStr != null) {
				universityId = Integer.parseInt(universityIdStr);
			}

			if (universityId != -1) {
				List<Campus> campusList = iRegisterDao.getCampusListByUniversityId(universityId);

				return CommonUtil.getJsonStringByGson(campusList);
			} else {
				return VariableUtil.CONNECT_FAILED + "";
			}
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}

	}

	public String getCampusListHasDiscotinShopsByUniversityId() {
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			String universityIdStr = request.getParameter(VariableUtil.UNIVERSITY_ID);

			int universityId = -1;
			if (universityIdStr != null) {
				universityId = Integer.parseInt(universityIdStr);
			}

			if (universityId != -1) {
				List<Campus> campusList = iRegisterDao.getCampusListHasDiscountinShopsByUniversityId(universityId);

				return CommonUtil.getJsonStringByGson(campusList);
			} else {
				return VariableUtil.CONNECT_FAILED + "";
			}
		} catch (Exception e) {
			return VariableUtil.CONNECT_FAILED + "";
		}
	}

}
