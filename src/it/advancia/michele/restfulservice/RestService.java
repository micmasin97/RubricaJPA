package it.advancia.michele.restfulservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.advancia.michele.dao.RubricaOperations;
import it.advancia.michele.model.Contatto;

@Path("{user}")
public class RestService
{
	@PathParam("user")
	private String user;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contatto> show()
	{
		return RubricaOperations.showContact(user);
	}

	@Path("delete/{id}")
	@DELETE
	public void delete(@PathParam("id") int id)
	{
		RubricaOperations.deleteContact(user, id);
	}

	@Path("update/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(@PathParam("id") int id, Contatto contatto)
	{
		RubricaOperations.updateContact(user, id, contatto.getNome(), contatto.getCognome(), contatto.getTelefono());
	}

	@Path("search/{partNome}/{partCognome}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Contatto> search(@PathParam("partNome") String nome, @PathParam("partCognome") String cognome)
	{
		if (nome.equals("-"))
			nome = "";
		if (cognome.equals("-"))
			cognome = "";
		return RubricaOperations.searchContact(user, nome, cognome);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Contatto contatto)
	{
		RubricaOperations.createContact(user, contatto.getNome(), contatto.getCognome(), contatto.getTelefono());
		return Response.status(Status.CREATED).entity(contatto).build();
	}

}
