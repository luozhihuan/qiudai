package com.luoyu.qiudai.core.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luoyu.qiudai.core.model.impl.GoodsInfo;

public class GoodsInfoData {
	
	
//	public static final String shoppingTrolleyMap = null;
	/**
	 * 购物车Map，记录了当前用户所选择的商品，key是商品id，value是该商品实体
	 */
	public static Map<Long, GoodsInfo> SHOPPING_TROLLEY_MAP = new HashMap<Long, GoodsInfo>();
	
	/** 商品信息链表 **/
	public static List<GoodsInfo> GOODS_INFO_LIST;

}
