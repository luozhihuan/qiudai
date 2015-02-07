package com.luoyu.qiudai.register.controller;

import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.register.model.Dormitory;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class DormiBuildingController extends StandardController{

	

	public DormiBuildingController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<Dormitory> getDormiBuildingList(int campusId) {
		if(campusId == -1){
			return null;
		}else{
			Network network = Network.NewInstance(PathUtil.getDormiBuildingList(), 5);
			network.addParms(VariableUtil.CAMPUS_ID, campusId + "");
			String result = network.connect();
			if(result == null){
				return null;
			}
			Gson gson = new Gson();
			List<Dormitory> dormitoyBuildingList = gson.fromJson(result,new TypeToken<List<Dormitory>>() {}.getType());
			
			
			return dormitoyBuildingList;
		}
	
	}

}
