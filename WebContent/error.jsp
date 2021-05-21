<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<%
	String errorType=(String)request.getAttribute("error");
	if(errorType==null || errorType.equals("wrong page"))
		out.println("Oooops, probabilmente sei finito nella pagina sbagliata");
	else
		out.println(errorType);
%>
</body>
</html>