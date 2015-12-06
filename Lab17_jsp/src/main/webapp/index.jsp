<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<div id="login-form">
		<div align="center">
			<h1>Student grades</h1>
		</div>
		<div align="center">
			<h3>Sign on:</h3>
		</div>
		<form action="login.do" method="post">
			<div align="center">
				<p>
					<label for="login">Login:</label>
					<input type="text" name="login">
				</p>
			</div>
			<div align="center">
				<p>
					<label for="password">Password:</label>
					<input type="password" name="password">
				</p>
			</div>
			<div align="center">
				<p>
					<input type="submit" value="Log in">
				</p>
			</div>
		</form>
	</div>
</body>
</html>