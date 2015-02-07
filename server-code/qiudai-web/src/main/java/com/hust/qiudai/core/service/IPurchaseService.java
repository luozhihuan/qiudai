package com.hust.qiudai.core.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/purchase")
@Produces(MediaType.TEXT_PLAIN)
public interface IPurchaseService {

	@POST
	@Path("/purchaseqiudai")
	public String purchaseqiudai();

}
