package com.hust.qiudai.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hust.qiudai.web.db.Campus;
import com.hust.qiudai.web.db.Dormitory;
import com.hust.qiudai.web.db.dao.IDormitoryDao;
import com.hust.qiudai.web.service.IDormitoryService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class DormitoryService implements IDormitoryService {

	@Inject
	private IDormitoryDao iDormitoryNumberDao;

	private static final Log logger = LogFactory.getLog(DormitoryService.class);
	
	public String saveDormitoryNumberInUserInfo() {
		HttpServletRequest request = CommonUtil.getHttpServletRequest();

		/** 获取宿舍号 **/
		String dormitoryNumber = request
				.getParameter(VariableUtil.DORMITORY_NUMBER);
		/** 获取用户id **/
		String userIdStr = request.getParameter(VariableUtil.USERID);
		
		
		logger.info("获取到的宿舍号是"+dormitoryNumber+"  用户id是:"+userIdStr);
		if(userIdStr != null){
			/**将字符串转换为long类型**/
			long userid = Long.parseLong(userIdStr);
			iDormitoryNumberDao.saveTheDormitoryNumberDaoToUser(dormitoryNumber, userid);
			return VariableUtil.SEND_DORMITORY_NUMBER_SUCCESS + "";
		}
		
		return VariableUtil.CONNECT_FAILED + "";
		
	}
	
	
	
	public String getDorimitoryList() {
		HttpServletRequest request = CommonUtil.getHttpServletRequest();

		String campusIdStr = request.getParameter(VariableUtil.CAMPUS_ID);
		logger.info("获取到的校区id是:"+campusIdStr);
		int campusId = -1;
		if (campusIdStr != null) {
			campusId = Integer.parseInt(campusIdStr);
		}

		if (campusId != -1) {
			List<Dormitory> dormitoryList = iDormitoryNumberDao.getDormitoryListByCampusId(campusId);

			return CommonUtil.getJsonStringByGson(dormitoryList);
		} else {
			return null;
		}
	}
}
