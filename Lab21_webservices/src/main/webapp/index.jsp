<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student grades</title>
</head>
<style>
body {
	background: url(images/bg.jpg);
	background-size: 100%;
}
</style>
<body>
	<center>
		<div class="container" style="width: 300px;">
			<form action="<c:url value="/j_spring_security_check"></c:url>"	method="post">
				<h3>Sign on:</h3>
				<div>
					<p>
						<label for="login">Login:</label> 
						<input type="text" name="login">
					</p>
				</div>
				<div>
					<p>
						<label for="password">Password:</label>
						<input type="password" name="password">
					</p>
				</div>
				<div>
					<p>
						<input type="submit" value="Log in">
					</p>
				</div>				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</center>
</body>
</html>