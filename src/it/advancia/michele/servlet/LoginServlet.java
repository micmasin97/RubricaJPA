package it.advancia.michele.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.advancia.michele.dao.EMFactoryProvider;
import it.advancia.michele.model.Account;
import it.advancia.michele.model.Rubrica;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String operation = request.getParameter("submit");
		int results = action(user, password, operation);
		if(results==1)
		{
			request.getSession().setAttribute("login", user);
			request.getRequestDispatcher("/loggedPage.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("error", "login o password falliti(in futuro saranno disponibili ulteriori informazioni)");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	private int action(String user, String password, String operation)
	{
		EntityManager entityManager = EMFactoryProvider.getEM();
		entityManager.getTransaction().begin();
		switch (operation)
		{
		case "login":
			Account account = entityManager.find(Account.class, user);
			if(account==null)
			{
				entityManager.close();
				return 0;
			}
			else
			{
				if(account.getPassword().equals(password))
				{
					entityManager.close();
					return 1;
				}
				else
				{
					entityManager.close();
					return 0;
				}
			}
		case "register":
			account = entityManager.find(Account.class, user);
			if(account==null)
			{
				account = new Account();
				account.setUsername(user);
				account.setPassword(password);
				Rubrica rubrica = new Rubrica();
				account.setRubrica(rubrica);
				entityManager.persist(rubrica);
				entityManager.persist(account);
				entityManager.getTransaction().commit();
				entityManager.close();
				return 1;
			}
			else
			{
				entityManager.close();
				return 0;
			}
		}
		return 0;
	}
}
