<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<section class="container">
		<div class="login">
			<h1>LOGIN</h1>
			<form method="post" action="auth_check">
				<p>
					<input type="text" name="email" placeholder="Email">
				</p>
				<p>
					<input type="password" name="password" placeholder="Password">
				</p>
				<p class="submit">
					<input type="submit" name="login" value="Login">
				</p>
				<c:if test="${loginfailed}"><p style="color:red">Email or password is not valid.</p></c:if>
			</form>
		</div>
	</section>
</body>
</html>
