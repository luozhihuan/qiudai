package com.hust.qiudai.core.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hust.qiudai.core.db.GoodsCategory;
import com.hust.qiudai.core.db.GoodsInfo;
import com.hust.qiudai.core.db.GoodsKindCategory;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public interface IGoodsCategoryService {

	/**
	 * 获取求带信息展示链表
	 */
//	@POST
	@POST
	@Path("/goodscategorylist")
	public String getGoodsCategoryListArrayList();
	
	
	/**
	 * 根据商品类别id获取该类别下的全部商品的链表
	 */
	@POST
	@Path("/goodsinfolistbycategoryid")
	public String getGoodsInfoListByCategoryId();
	
	
	/**
	 * 全部商品的链表
	 */
	
	@POST
	@Path("/goodsinfolist")
	public String getGoodsInfoList();
	
	/**
	 * 获取商品大类分类链表
	 */
	@POST
	@Path("/goodskindcategorylist")
	public String getGoodsKindCategoryList();
	
	
	

}
