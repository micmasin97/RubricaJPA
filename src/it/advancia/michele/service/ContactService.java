package it.advancia.michele.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import it.advancia.michele.dao.RubricaOperations;
import it.advancia.michele.model.Contatto;

@WebService
public class ContactService
{

	@WebMethod
	@WebResult(name="Contatto")
	public List<Contatto> showAll(String user)
	{
		return RubricaOperations.showContact(user);
	}

	@WebMethod
	public void create(String user, String nome, String cognome, String numero)
	{
		RubricaOperations.createContact(user, nome, cognome, numero);
		
	}

	@WebMethod
	public void delete(String user, int id)
	{
		RubricaOperations.deleteContact(user, id);		
	}

	@WebMethod
	@WebResult(name="Contatto")
	public List<Contatto> search(String user, String nome, String cognome)
	{
		return RubricaOperations.searchContact(user, nome, cognome);
	}

	@WebMethod
	public void update(String user, int id, String nome, String cognome, String numero)
	{
		RubricaOperations.updateContact(user, id, nome, cognome, numero);		
	}

}
