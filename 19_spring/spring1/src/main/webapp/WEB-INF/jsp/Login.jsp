<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Login</title>
</head>
<body>
 <form action="j_spring_security_check" method="POST">
 <label for="username">User Name:</label>
 <input id="username" name="j_username" type="text" />
 <label for="password">Password:</label>
 <input id="password" name="j_password" type="password" />
 <input type="submit" value="Log In" />
 </form>
</body>
</html>