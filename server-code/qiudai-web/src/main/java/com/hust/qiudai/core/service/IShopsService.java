package com.hust.qiudai.core.service;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/shops")
@Produces(MediaType.TEXT_PLAIN)
public interface IShopsService {

	/**
	 * 根据高校id获取该高校的全部商铺，按校区分类组合成线性链表返回
	 */
	@POST
	@Path("/shopslistbyuniversityid")
	public String getShopsListGroupByCampusByUniversityId();


	/**
	 * 根据传入的商铺id获取该商铺的全部商品信息
	 */
	@POST
	@Path("/goodsinfolistbyshopsid")
	public String getGoodsInfoListByShopsId();

	
	
	/**
	 * 根据传入的商铺id获取该商铺的全部打折商品信息
	 */
	@POST
	@Path("/discountinggoodsinfolistbyshopsid")
	public String getDiscountingGoodsInfoListByShopsId();
	

}
