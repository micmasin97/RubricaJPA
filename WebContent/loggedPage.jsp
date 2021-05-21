<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
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
		Welcome <%= session.getAttribute("login") %>
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