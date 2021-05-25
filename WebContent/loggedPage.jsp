<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ page import="it.advancia.michele.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<%
if (session.getAttribute("login") != null)
{
%>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<%
if (session.getAttribute("login") != null)
%>
<title>Welcome <%=session.getAttribute("login")%></title>
</head>
<body>
	<h1>
		Welcome
		<%=session.getAttribute("login")%></h1>
	<%
	List<Contatto> contatti = (List<Contatto>) request.getAttribute("list");
	if (contatti == null)
	{
	%>
	<jsp:forward page="ContattiServlet">
		<jsp:param value="show" name="submit" />
	</jsp:forward>
	<%
	} else
	{
	%>
	<h3>I tuoi contatti</h3>
	<table>
		<caption>Lista Contatti</caption>
		<thead>
			<tr>
				<th scope="id">ID</th>
				<th scope="nome">Nome</th>
				<th scope="cognome">Cognome</th>
				<th scope="numero">Numero</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Contatto c : contatti)
			{
			%>
			<tr>
				<td><%=c.getId()%></td>
				<td><%=c.getNome()%></td>
				<td><%=c.getCognome()%></td>
				<td><%=c.getTelefono()%></td>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>
	Creazione_contatto
	<form action="ContattiServlet" method="post">
		<input type="text" name="createNome" placeholder="Nome"> <input
			type="text" name="createCognome" placeholder="Cognome"> <input
			type="text" name="createNumero" placeholder="Numero di telefono">
		<input type="submit" name="submit" value="create">
	</form>
	Ricerca_contatto
	<form action="ContattiServlet" method="post">
		<input type="text" name="searchNome" placeholder="Nome"> <input
			type="text" name="searchCognome" placeholder="Cognome"> <input
			type="submit" name="submit" value="search">
	</form>
	Elimina_contatto
	<form action="ContattiServlet" method="post">
		<input type="number" name="deleteId" placeholder="id"> <input
			type="submit" name="submit" value="delete">
	</form>
	Modifica_contatto
	<form action="ContattiServlet" method="post">
		<input type="number" name="updateId" placeholder="id"> <input
			type="text" name="updateNome" placeholder="Nome"> <input
			type="text" name="updateCognome" placeholder="Cognome"> <input
			type="text" name="updateNumero" placeholder="Numero di telefono">
		<input type="submit" name="submit" value="update">
	</form>
</body>
</html>
<%
} else
{
request.setAttribute("error", "wrong page");
request.getRequestDispatcher("error.jsp").forward(request, response);
}
%>