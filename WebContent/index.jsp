<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		Username: <input type="text" name="user" placeholder="username">
		Password: <input type="password" name="password" placeholder="password">
		<input type="submit" name="submit" value="login">
		<input type="submit" name="submit" value="register">
	</form>
</body>
</html>