package com.hust.qiudai.web.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.TEXT_PLAIN)
public interface ILoginService {

	/**
	 * 传入用户名密码，进行注册
	 */
	@POST
	@Path("/login")
	public String login(@FormParam("EMAIL_PARAMETER_NAME_4_LoginController") String email, @FormParam("PASSWORD_PARAMETER_NAME_4_LoginController") String password);
}
