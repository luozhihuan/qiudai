package com.hust.qiudai.web.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/associative")
@Produces(MediaType.TEXT_PLAIN)
public interface IAssociativeWordsService {
	
	/**
	 * 传入词语，获取联想词
	 */
	@POST
	@Path("/universities")
	public String getAssociativeUniversities();

}
