<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<c:url value="/style/style.css" var="cssLink"/>
<html>
<head>
    <title>Login Page</title>
    <link href="${cssLink}" type="text/css" rel="stylesheet"/>
    <script src="<c:url value="/js/validator.js" />"></script>
</head>
<body>
<form action="<c:url value="/j_spring_security_check"></c:url>" onsubmit="return validateForm(this,optionsForLoginForm);" method="post"/>
<div class="horizon">
    <div class="centeroid">
        <input type="text" name="login" placeholder="Login"><br>
        <input type="password" name="password" placeholder="Password"><br>
        <input type="submit" value="Sign in"><br>
        <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
            ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
        </c:if>
        <c:out value="${param.msg}"/>
        <div id="errorMsg"></div>
    </div>
</div>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>