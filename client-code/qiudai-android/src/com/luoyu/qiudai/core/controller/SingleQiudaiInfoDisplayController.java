package com.luoyu.qiudai.core.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.luoyu.net.Network;
import com.luoyu.qiudai.core.model.impl.GoodsInfo;
import com.luoyu.qiudai.standard.controller.StandardController;
import com.luoyu.qiudai.util.PathUtil;
import com.luoyu.qiudai.util.ThreadProgressDialogUtil;
import com.luoyu.qiudai.util.VariableUtil;

public class SingleQiudaiInfoDisplayController extends StandardController {

	public SingleQiudaiInfoDisplayController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 从服务器上获取该求带信息id对应的求带商品列表
	 * 
	 * @param qiudaiInfoId
	 * @return 求带商品链表
	 */
	public List<GoodsInfo> getGoodsInfoListFromServer(long qiudaiInfoId) {

		Network network = Network.NewInstance(PathUtil.get_URL_GET_QIUDAI_GOODS_INFO_LIST(), 5);
		network.addParms(VariableUtil.QIUDAI_INFO_ID, qiudaiInfoId + "");
		String result = network.connect();
		System.out.println("返回结果" + result);
		if (commonService.isNumber(result)) {
			/** 返回值检测为数字说明并未返回联想词,翻译前端一个空的list即可 **/
			return new ArrayList<GoodsInfo>();
		} else {
			// Gson gson = new Gson();
			/** 使用Gson将json格式的字符串转换为对象类型 **/
			List<GoodsInfo> qiudaiDisplayInfoList = gson.fromJson(result, new TypeToken<List<GoodsInfo>>() {
			}.getType());

			for (GoodsInfo info : qiudaiDisplayInfoList) {
				System.out.println(info.getGoodsName());
			}
			return qiudaiDisplayInfoList;

		}

	}

	/**
	 * 通过本地反射的方式从服务器获取GoodsSelectActivity界面需要使用的商品类别信息以及商品信息
	 */
	public void takeTheQiudaiInfoOrderByRelect(long receiverId, long qiudaiInfoId) {

		/** 访问网络等待过程中的进度框 **/
		ProgressDialog xh_ProgressDlg = ProgressDialog.show(context, "Loading...", "Please wait...", true, false);
		/** 使用反射机制进行访问网络 **/
		ThreadProgressDialogUtil.progressDialog(xh_ProgressDlg, this, "sendingTheQiudaiInfoOrderToServer", new Object[] { receiverId, qiudaiInfoId },
				context, "resultCodeOnShowToast", 500);
	}

	// TODO 接续修改
	public int sendingTheQiudaiInfoOrderToServer(Long receiverId, Long qiudaiInfoId) {

		Network network = Network.GetSingletonInstance(PathUtil.QIUDAI_ORDER_PATH.GET_QIUDAI_ORDER_RECIVING(), 5);
		network.addParms(VariableUtil.USERID, receiverId + "");
		network.addParms(VariableUtil.QIUDAI_INFO_ID, qiudaiInfoId + "");

		String result = network.connect();

		if (result.equals(VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_SUCCESS + "")) {
			return VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_SUCCESS;
		} else if (result.equals(VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_FAIL + "")) {
			return VariableUtil.ORDER_VARIABLE.ORDER_RECEIVING_FAIL;
		} else {
			return VariableUtil.CONNECT_FAILED;
		}

	}
}
