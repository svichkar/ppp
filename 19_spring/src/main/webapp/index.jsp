<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<c:url value="/style/style.css" var="cssLink"/>
<html>
<head>
<title>Login Page</title>
<link href="${cssLink}" type="text/css" rel="stylesheet"/>
</head>
<body>
  <form action="homepage" method="post"/>
  <div class="horizon">
   <div class="centeroid">
    <input type="text" name="login" placeholder="Login"><br>
    <input type="password" name="pass" placeholder="Password"><br>
    <input type="submit" value="Sign in"><br>
    <c:out value="${param.message}"/>
    </div>
   </div>
  </form>
</body>
</html>