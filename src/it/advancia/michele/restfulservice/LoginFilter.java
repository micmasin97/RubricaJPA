package it.advancia.michele.restfulservice;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import it.advancia.michele.dao.EMFactoryProvider;
import it.advancia.michele.model.Account;

@Provider
public class LoginFilter implements ContainerRequestFilter
{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException
	{
		String path = requestContext.getUriInfo().getPath();
		String user = path.split("/")[0];
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if (authHeader != null && !authHeader.isEmpty())
		{
			// se authheader contiene qualcosa
			String authToken = authHeader.get(0);
			// rimuovo la stringa token(così da avere poi solo la parte codificata)
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			// e la decodifico
			String decodedString = Base64.decodeAsString(authToken);
			StringTokenizer tokens = new StringTokenizer(decodedString, ":");
			String username = tokens.nextToken();
			String password = tokens.nextToken();
			if (username.equals(user))
			{
				EntityManager entityManager = EMFactoryProvider.getEM();
				entityManager.getTransaction().begin();
				Account account = entityManager.find(Account.class, user);
				if (account.getPassword().equals(password))
				{
					entityManager.close();
					return;
				}
				entityManager.close();
			}
		}
		Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access to the resource. ").build();
		// blocco l'invio della response e invio la risposta personalizzata
		requestContext.abortWith(unauthorizedStatus);
	}

}
