<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/j_spring_security_check" var="loginUrl" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="<c:url value="images/favicon.png"/>" rel="shortcut icon" type="shortcut/ico">
    <link rel="stylesheet" href="<c:url value="style/login-style.css"/>">
</head>
<body>
${error}
<div class="login-card">
    <h1>Student Grade Management</h1><br>
    <form name="loginForm" action="${loginUrl}" method="POST">
        <input type="text" name="j_username" placeholder="Login" maxlength="50">
        <input type="password" name="j_password" placeholder="Password" maxlength="20">
        <input type="submit" name="submit" class="login login-submit" value="Login">
    </form>
</div>
</body>
</html>