<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<label>username:</label><input type="text" name="username"/><br>
		<label>password:</label><input type="password" name="password"/><br>
		<label>rememberMe:</label><input type="checkbox" value="rememberMe" name="rememberMe"/><br>
		<input type="submit" value="Login"/>
	</form>
</body>
</html>