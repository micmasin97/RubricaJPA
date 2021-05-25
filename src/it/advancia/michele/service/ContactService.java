package it.advancia.michele.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import it.advancia.michele.dao.EMFactoryProvider;
import it.advancia.michele.dao.RubricaOperations;
import it.advancia.michele.model.Account;
import it.advancia.michele.model.Contatto;

@WebService
public class ContactService
{
	@Resource
	private WebServiceContext wsc;
	
	public String auth()
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
	
	@WebMethod
	@WebResult(name="Contatto")
	public List<Contatto> showAll()
	{
		String user = auth();
		return RubricaOperations.showContact(user);
	}

	@WebMethod
	public void create(String nome, String cognome, String numero)
	{
		String user = auth();
		RubricaOperations.createContact(user, nome, cognome, numero);
		
	}

	@WebMethod
	public void delete(int id)
	{
		String user = auth();
		RubricaOperations.deleteContact(user, id);		
	}

	@WebMethod
	@WebResult(name="Contatto")
	public List<Contatto> search(String nome, String cognome)
	{
		String user = auth();
		return RubricaOperations.searchContact(user, nome, cognome);
	}

	@WebMethod
	public void update(int id, String nome, String cognome, String numero)
	{
		String user = auth();
		RubricaOperations.updateContact(user, id, nome, cognome, numero);		
	}

}
