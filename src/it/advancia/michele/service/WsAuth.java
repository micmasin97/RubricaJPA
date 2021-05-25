package it.advancia.michele.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WsAuth
{
	@WebMethod
	public String authTest();
}
