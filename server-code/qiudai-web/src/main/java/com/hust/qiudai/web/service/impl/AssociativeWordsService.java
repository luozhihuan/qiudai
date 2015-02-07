package com.hust.qiudai.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.google.gson.Gson;
import com.hust.qiudai.web.db.University;
import com.hust.qiudai.web.db.dao.IAssociativeDao;
import com.hust.qiudai.web.service.IAssociativeWordsService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class AssociativeWordsService implements IAssociativeWordsService {

	@Inject
	private IAssociativeDao iAssociativeDao;

	public String getAssociativeUniversities() {
		HttpServletRequest request = CommonUtil.getHttpServletRequest();

		try{
		String word = request.getParameter(VariableUtil.WORD_FOR_ASSCIATIVE_WORDS);
System.out.println(word);		
		if (word == null) {
			return VariableUtil.ASSOCIATIVE_UNIVERSITIES_FAIL + "";
		} else {
			List<University> list = iAssociativeDao.findAssociativeUniversities(word);
			
			Gson gson = new Gson();
			String returnValue = gson.toJson(list);
System.out.println(returnValue);			
			return returnValue;	
		}
		}catch(Exception e){
			return VariableUtil.ASSOCIATIVE_UNIVERSITIES_FAIL + "";
		}
	}
}
