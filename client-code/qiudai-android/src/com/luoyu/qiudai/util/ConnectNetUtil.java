package com.luoyu.qiudai.util;

import android.os.StrictMode;
/**
 * 链接网络的辅助类
 * @author chuanrong
 *
 */
public class ConnectNetUtil {
	/**
	 * 链接网络方法
	 */
	public static void CONNECT_NET(){
		// /在Android2.2以后必须添加以下代码
		// 本应用采用的Android4.0
		// 设置线程的策略
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		// 设置虚拟机的策略
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				// .detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
	}

}
