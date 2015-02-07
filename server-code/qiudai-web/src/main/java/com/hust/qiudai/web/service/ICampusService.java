package com.hust.qiudai.web.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/campus")
@Produces(MediaType.TEXT_PLAIN)
public interface ICampusService {
	
	
	
	/**
	 * 根据传入的universityId获取该校的校区链表
	 */
	@POST
	@Path("/campuslistbyuniversityid")
	public String getCampusListByUniversityId();
	
	
	
	/**
	 * 根据传入的universityId获取该校中所有有打折活动商铺的校区链表
	 */
	@POST
	@Path("/campuslisthasdiscotinshopsbyuniversityid")
	public String getCampusListHasDiscotinShopsByUniversityId();
	
	
}
