<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Login</title>
</head>
<body>
 <form action="j_spring_security_check" method="POST">
<p>
 <label for="username">User Name:</label>
 <input id="username" name="j_username" type="text" />
 </p>
 <p>
 <label for="password">Password:</label>
 <input id="password" name="j_password" type="password" />
 </p>
 <p>
 <input type="submit" value="Log In" />
 </p>
 </form>
 <p style="color: red">
 <c:if test="${param.error == 'invalidLoginAndPassord'}">
 Invalid login or password. Check and try again.
 </c:if>
 </p>
 <p>
 credentials to login: jax / jax
 </p>
</body>
</html>