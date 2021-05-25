package it.advancia.michele.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.advancia.michele.dao.RubricaOperations;
import it.advancia.michele.model.Contatto;

@WebServlet("/ContattiServlet")
public class ContattiServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final String SUCCESS_PAGE = "loggedPage.jsp";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			actions(request, response);
		} catch (Exception e)
		{
			try
			{
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} catch (ServletException | IOException ex)
			{
				ex.printStackTrace();
			}

		}
	}

	private void actions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operations = request.getParameter("submit");
		String user = (String) request.getSession().getAttribute("login");
		switch (operations)
		{
		case "show":
			List<Contatto> contatti = RubricaOperations.showContact(user);
			request.setAttribute("list", contatti);
			request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
			break;
		case "create":
			String nome = request.getParameter("createNome");
			String cognome = request.getParameter("createCognome");
			String numero = request.getParameter("createNumero");
			RubricaOperations.createContact(user, nome, cognome, numero);
			request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
			break;
		case "search":
			nome = request.getParameter("searchNome");
			cognome = request.getParameter("searchCognome");
			contatti = RubricaOperations.searchContact(user, nome, cognome);
			request.setAttribute("list", contatti);
			request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
			break;
		case "delete":
			int id = Integer.parseInt(request.getParameter("deleteId"));
			RubricaOperations.deleteContact(user, id);
			request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
			break;
		case "update":
			id = Integer.parseInt(request.getParameter("updateId"));
			nome = request.getParameter("updateNome");
			cognome = request.getParameter("updateCognome");
			numero = request.getParameter("updateNumero");
			RubricaOperations.updateContact(user, id, nome, cognome, numero);
			request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
			break;
		default:
			return;
		}
	}

}
