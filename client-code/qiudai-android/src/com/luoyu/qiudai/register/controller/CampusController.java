package com.luoyu.qiudai.register.controller;

import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.register.model.Campus;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class CampusController extends StandardController{

	

	public CampusController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过传入的universtyid从服务器获取该学校的校区列表
	 * @param universityId
	 * @return 校区列表
	 */
	public List<Campus> getCampusListFromServer(int universityId) {
		if(universityId == -1){
			return null;
		}else{
			Network network = Network.NewInstance(PathUtil.get_CAMPUS_LIST_BY_UNIVERSITY_ID(), 5);
			network.addParms(VariableUtil.UNIVERSITY_ID, universityId + "");
			String result = network.connect();
			if(result == null){
				return null;
			}
			Gson gson = new Gson();
			List<Campus> campusList = gson.fromJson(result,new TypeToken<List<Campus>>() {}.getType());
			
			return campusList;
		}
	
	}

	
}
