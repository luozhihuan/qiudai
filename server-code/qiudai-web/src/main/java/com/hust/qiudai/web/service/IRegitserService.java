package com.hust.qiudai.web.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
@Produces(MediaType.TEXT_PLAIN)
public interface IRegitserService {
	
	/**
	 * 传入用户名密码，进行注册
	 */
	@POST
	@Path("/register")
	public String register();
	
	
	/**
	 * 传入用户名密码，进行注册
	 */
	@POST
	@Path("/username")
	public String usernameCommit();

}
