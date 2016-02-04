<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
<form:form action="${loginUrl}" method="POST">
        <input type="text" name="j_username" placeholder="Login" maxlength="50">
        <input type="password" name="j_password" placeholder="Password" maxlength="20">
        <input type="submit" name="submit" class="login login-submit" value="Login">
</form:form>
</div>
</body>
</html>