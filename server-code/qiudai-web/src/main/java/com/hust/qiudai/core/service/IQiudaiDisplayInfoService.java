package com.hust.qiudai.core.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/qiudaiinfo")
@Produces(MediaType.TEXT_PLAIN)
public interface IQiudaiDisplayInfoService {

	
	/**
	 * 获取求带信息展示链表
	 */
	@POST
	@Path("/qiudaidisplayinfolist")
	public String getQiudaiDisplayInfoList();

	
	/**
	 * 获取求带商品信息链表
	 */
	@POST
	@Path("/goodsinfolist")
	String getQiudaiGoodsInfoList();
	
	
	
}
