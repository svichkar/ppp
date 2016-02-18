<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Authorisation</title>
</head>
<body>
	<p>Please enter your credentials</p>
	<div>
	<form action="auth" method="post">
		Name:<input type="text" name="username" />
		<br />
		<br /> 
		Password:<input type="password" name="userpass" />
		<br />
		<br />  
		<input type="hidden" name="hdnbt" /> 
		<input type="submit" name="button" value="login" /> 
		<input type="submit" name="button" value="register" />
	</form>
	</div>
	<c:if test="${not empty status}">
		<p>info: ${status}</p>
		</c:if>						
</body>
</html>
