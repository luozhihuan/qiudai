package com.hust.qiudai.core.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/goodsinfo")
@Produces(MediaType.TEXT_PLAIN)
public interface IGoodsInfoService {

	/****************************打折***********************/
	/**
	 * 根据传入的高校id，获取该校全部打折的商品
	 */
	@POST
	@Path("/getdiscountinggoodsinfolistbyuniversityid")
	public String getDiscountingGoodsInfoListByUniversityId();
	
	
	/**
	 * 根据传入的商铺id，获取该商铺全部打折的商品
	 */
	@POST
	@Path("/getdiscountinggoodsinfolistbyshopsid")
	public String getDiscountingGoodsInfoListByShopsId();
	
	/**
	 * 根据传入的高校id和商品种类id，获取该高校中该商品种类的全部打折的商品
	 */
	@POST
	@Path("/getdiscountinggoodsinfolistbyuniversityidandcategoryid")
	public String getDiscountingGoodsInfoListByUniversityIdAndCategoryId();
	
	/**
	 * 根据传入的商铺id和商品种类id，获取该商铺中该商品种类的全部打折的商品
	 */
	@POST
	@Path("/getdiscountinggoodsinfolistbyshopsidandcategoryid")
	public String getDiscountingGoodsInfoListByShopsIdAndCategoryId();
	
	
	/******************************不打折***********************************/
	
	/**
	 * 根据传入的高校id，获取该校全部的商品
	 */
	@POST
	@Path("/getgoodsinfolistbyuniversityid")
	public String getGoodsInfoListByUniversityId();
	
	
	/**
	 * 根据传入的商铺id，获取该商铺全部的商品
	 */
	@POST
	@Path("/getgoodsinfolistbyshopsid")
	public String getGoodsInfoListByShopsId();
	
	/**
	 * 根据传入的高校id和商品种类id，获取该高校中该商品种类的全部的商品
	 */
	@POST
	@Path("/getgoodsinfolistbyuniversityidandcategoryid")
	public String getGoodsInfoListByUniversityIdAndCategoryId();
	
	/**
	 * 根据传入的商铺id和商品种类id，获取该商铺中该商品种类的全部的商品
	 */
	@POST
	@Path("/getgoodsinfolistbyshopsidandcategoryid")
	public String getGoodsInfoListByShopIdAndCategoryId();
	
	
}
