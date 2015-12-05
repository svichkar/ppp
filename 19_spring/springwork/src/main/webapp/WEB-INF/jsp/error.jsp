<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:set var="title" value="Exception Handling" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p><c:out value="${title}" /></p>
		<div class="head1"></div>
		<c:if test="${statusCode ne 500}">
			<h3>Servlet Exception Handling</h3>
			<strong>Status Code:</strong> ${statusCode} <br>
			<strong>Requested URI:</strong> ${requestUri}
			</c:if>
			<c:if test="${statusCode eq 500}">
			<h3>Servlet Exception Handling</h3>
			<ul><li>Servlet Name: ${servletName}</li>
			<li>Exception Name: ${exceptionName}</li>
			<li>Requested URI: ${requestUri}</li>
			<li>Exception Message: ${exception.message}</li>
			</ul>
			</c:if>
	</div>
</body>
</html>