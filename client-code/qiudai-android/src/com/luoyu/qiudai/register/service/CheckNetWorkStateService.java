package com.luoyu.qiudai.register.service;

import android.content.Context;
import android.net.ConnectivityManager;

public class CheckNetWorkStateService {

	

	/** 
     * 检测网络是否连接 
     * @return false表示未连接网络，true表示连接上网络
     */  
    public boolean checkNetworkState(Context context) {  
        boolean flag = false;  
        //得到网络连接信息  
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        //去进行判断网络是否连接  
        if (manager.getActiveNetworkInfo() != null) {  
            flag = manager.getActiveNetworkInfo().isAvailable();  
        }  
        return flag;  
    }  
}
