<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<%@ page import="it.advancia.michele.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<%if(session.getAttribute("login")!=null) 
{%>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<%if(session.getAttribute("login")!=null) %>
	<title>Welcome <%= session.getAttribute("login") %></title>
	</head>
	<body>
		<h1>Welcome <%= session.getAttribute("login") %></h1>
		<%
			List<Contatto> contatti = (List<Contatto>) request.getAttribute("list");
			if(contatti==null)
			{
			%>
				<jsp:forward page="ContattiServlet">
				<jsp:param value="show" name="submit"/>
				</jsp:forward>
			<%
			}
			else
			{
			%>
			<h3>I tuoi contatti</h3>
			<table>
				<thead>
				<tr>
					<th>
					ID
					</th>
					<th>
					Nome
					</th>
					<th>
					Cognome
					</th>
					<th>
					Numero
					</th>
				</tr>
				</thead>
				<tbody>
			<%
				for(Contatto c : contatti)
				{
					%>
						<tr>
							<td>
								<%= c.getId() %>
							</td>
							<td>
								<%= c.getNome() %>
							</td>
							<td>
								<%= c.getCognome() %>
							</td>
							<td>
								<%= c.getTelefono() %>
							</td>
						</tr>
					<%
				}
			}
		%>
		</tbody>
		</table>
		<form action="ContattiServlet" method="post">
		<input type="text" name="createNome">
		<input type="text" name="createCognome">
		<input type="text" name="createNumero">
		<input type="submit" name="submit" value="create">
		</form>
	</body>
	</html>
<%
}
else
{
	request.setAttribute("error", "wrong page");
	request.getRequestDispatcher("error.jsp").forward(request, response);
}
%>