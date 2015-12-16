<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Login page</title>
</head>
<body bgcolor="#F0FFFF">
<form action="<c:url value="/j_spring_security_check"></c:url>" method="post">
<div align="center"><h1> Please login </h1></div>
<div align="center">Login:&nbsp;<input type="text" name="login" ></div>
<div align="center">Password:&nbsp;<input type="password" name="password" ></div>
<br>
<div align="center"><input type="submit" value="Submit"></div>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
