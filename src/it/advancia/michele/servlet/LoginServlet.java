package it.advancia.michele.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
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
	private static final String LOGGED_PAGE_JSP = "/loggedPage.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			request.getRequestDispatcher(ERROR_JSP).forward(request, response);
		} catch (ServletException | IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String operation = request.getParameter("submit");
		int results = action(user, password, operation);
		try
		{
			if (results == 1)
			{
				request.getSession().setAttribute("login", user);
				request.getRequestDispatcher(LOGGED_PAGE_JSP).forward(request, response);
			} else
			{
				switch (results)
				{
				case -1:
					request.setAttribute("error", "username inesistente");
					break;
				case -2:
					request.setAttribute("error", "password errata");
					break;
				case -3:
					request.setAttribute("error", "username già presente");
					break;
				default:
					request.setAttribute("error", "errore di sistema");
					break;
				}
				request.getRequestDispatcher(ERROR_JSP).forward(request, response);
			}
		} catch (Exception e)
		{
			try
			{
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher(ERROR_JSP).forward(request, response);
			} catch (ServletException | IOException ex)
			{
				ex.printStackTrace();
			}
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
			if (account == null)
			{
				entityManager.close();
				return -1;
			} else
			{
				if (account.getPassword().equals(password))
				{
					entityManager.close();
					return 1;
				} else
				{
					entityManager.close();
					return -2;
				}
			}
		case "register":
			account = entityManager.find(Account.class, user);
			if (account == null)
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
			} else
			{
				entityManager.close();
				return -3;
			}
		default:
			return 0;
		}

	}
}
