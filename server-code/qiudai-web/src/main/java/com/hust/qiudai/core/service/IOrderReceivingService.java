package com.hust.qiudai.core.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/order")
@Produces(MediaType.TEXT_PLAIN)
public interface IOrderReceivingService {

	/**
	 * 接单操作
	 */
	@POST
	@Path("/orderreciving")
	public String orderReceiving();

}
