package it.advancia.michele.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import it.advancia.michele.dao.EMFactoryProvider;
import it.advancia.michele.model.Account;

@WebService(endpointInterface = "it.advancia.michele.service.WsAuth")
public class WsAuthImpl implements WsAuth
{

	@Resource
	private WebServiceContext wsc;
	
	@Override
	public String authTest()
	{
		MessageContext context = wsc.getMessageContext();
		Map head =(Map)context.get(MessageContext.HTTP_REQUEST_HEADERS);
		List userList =(List) head.get("username");
		List passwordList =(List) head.get("password");
		String user="";
		String password="";
		if(userList != null && passwordList != null)
		{
			user=(String) userList.get(0);
			password=(String) passwordList.get(0);
		
			EntityManager entityManager = EMFactoryProvider.getEM();
			Account account = entityManager.find(Account.class, user);
			if(account.getPassword().equals(password))
			{
				entityManager.close();
				return user;
			}
		}
		return "";
	}

}
