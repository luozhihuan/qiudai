package com.luoyu.qiudai.core.data;

import java.util.List;

import com.luoyu.qiudai.core.model.impl.GoodsCategory;
import com.luoyu.qiudai.core.model.impl.GoodsKindCategory;
import com.luoyu.qiudai.core.model.impl.Shops;
import com.luoyu.qiudai.core.model.impl.SpecialOffer;
import com.luoyu.qiudai.core.model.impl.SpecialOfferChoice;
import com.luoyu.qiudai.register.model.Campus;

/**
 * 用于GoodsSelectActivity中筛选区域的数据
 * 
 * @author chuanrong
 * 
 */
public class SelectionCategoryData {

	/***************** 1.商品分类 **************************/
	/** 商品大类分类链表 **/
	public static List<GoodsKindCategory> GOODS_KIND_CATEGORY_LIST;
	
	/** 商品类别链表的List集合 **/
	public static List<List<GoodsCategory>> GOODS_AND_CATEGORY_INFO_LIST_ARRAY_LIST;

	/******************* 2.超市商铺 **************************/
	
	/** 校区链表 **/
	public static List<Campus> CAMPUS_LIST;
	
	/** 商铺小店链表List集合 **/
	public static List<List<Shops>> SHOPS_LIST_ARRAY_LIST;
	
	/******************* 3.打折活动 ***********************/

	public static List<SpecialOffer> SPECIAL_OFFER_KIND_LIST;

	// public static HashMap<Integer, List<SpecialOfferChoice>>
	// SPECIAL_OFFER_CHOICE_LIST_MAP;

	public static List<List<SpecialOfferChoice>> SPECIAL_OFFER_CHOICE_LIST_ARRAY_LIST;
}
