package com.hust.qiudai.web.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dormitory")
@Produces(MediaType.TEXT_PLAIN)
public interface IDormitoryService {
	
	
	/**
	 * 存储宿舍号至用户信息表中
	 */
	@POST
	@Path("/uploadDormNumber")
	public String saveDormitoryNumberInUserInfo();
	
	/**
	 * 根据传入的campusId获取该校区的宿舍列表
	 */
	@POST
	@Path("/dormitoryList")
	public String getDorimitoryList();
	

}
