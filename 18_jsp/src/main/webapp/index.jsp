<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<h2>Login page</h2>

	<form action="j_security_check" method=post>
		<p>
			<strong>Please enter Your login: </strong> <input type="text"
				name="username" size="25">
		<p>
		<p>
			<strong>Please enter Your password: </strong> <input type="password"
				size="15" name="password">
		<p>
			<font size="3" color="red">${error}</font>
		<p>
			<input type="submit" value="Submit"> <input type="reset"
				value="Reset">
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input
				type="submit" name="register" value="Register">
	</form>
</body>
</html>
