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
public class ContattiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ContattiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Get").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operations = request.getParameter("submit");
		String user = (String)request.getSession().getAttribute("login");
		switch (operations)
		{
		case "show":
			List<Contatto> contatti = RubricaOperations.showContact(user);
			request.setAttribute("list", contatti);
			request.getRequestDispatcher("loggedPage.jsp").forward(request, response);
			break;
		case "create":

			String nome = request.getParameter("createNome");
			String cognome = request.getParameter("createCognome");
			String numero = request.getParameter("createNumero");
			RubricaOperations.createContact(user, nome, cognome, numero);
			request.getRequestDispatcher("loggedPage.jsp").forward(request, response);
			break;
		}
	}
	

}
