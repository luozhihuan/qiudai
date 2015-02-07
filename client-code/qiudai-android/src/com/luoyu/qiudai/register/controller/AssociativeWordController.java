package com.luoyu.qiudai.register.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.register.model.University;
import com.luoyu.qiudai.register.service.CommonService;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.VariableUtil;

/**
 * 用于获取联想词
 * 
 * @author chuanrong
 * 
 */
public class AssociativeWordController extends StandardController{

	

	public AssociativeWordController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<University> getAssociativeUniversitiesBySendTextingWord(String word) {

		Network network = Network.NewInstance(PathUtil.getAssociativeUniversities(), 5);
		network.addParms(VariableUtil.WORD_FOR_ASSCIATIVE_WORDS, word);
		String result = network.connect();
//System.out.println("Controller"+result);
		if (commonService.isNumber(result)) {
			/** 返回值检测为数字说明并未返回联想词,翻译前端一个空的list即可 **/
			return new ArrayList<University>();
		} else {
			Gson gson = new Gson();
			List<University> list = gson.fromJson(result, new TypeToken<List<University>>() {}.getType());
			return list;

		}
	}

}
