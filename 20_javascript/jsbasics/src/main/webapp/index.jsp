<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/basic.js" var="jsbasicUrl" />
<c:url value="/js/login.js" var="jsloginUrl" />
<html>
<head>
<title>Login page</title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsbasicUrl}"></script>
<script type="text/javascript" src="${jsloginUrl}"></script>
</head>
<body bgcolor="#F0FFFF">
	<form action="<c:url value="/j_spring_security_check"></c:url>"
		method="post" class="focusableForm">
		<div align="center">
			<h1>Please login</h1>
		</div>
		<div align="center" class="formElement">
			Login:&nbsp;<input type="text" name="login">
			<div class="hidden">Please enter your login</div>
		</div>
		<div align="center" class="formElement">
			Password:&nbsp;<input type="password" name="password">
		</div>
		<br>
		<div align="center" class="formElement">
			<input type="submit" value="Submit">
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>
