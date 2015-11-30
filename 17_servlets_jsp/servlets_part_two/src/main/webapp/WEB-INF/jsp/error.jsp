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
		<c:if test="${javax.servlet.error.status_code ne '500'}">
			<h3>Servlet Exception Handling</h3>
			<strong>Status Code:</strong> ${javax.servlet.error.status_code} <br>
			<strong>Requested URI:</strong> ${javax.servlet.error.request_uri}
			</c:if>
			<c:if test="${javax.servlet.error.status_code eq '500'}">
			<h3>Servlet Exception Handling</h3>
			<ul><li>Servlet Name: ${javax.servlet.error.servlet_name}</li>
			<li>Exception Name: ${javax.servlet.error.exception.class.name}</li>
			<li>Requested URI: ${javax.servlet.error.request_uri}</li>
			<li>Exception Message: ${javax.servlet.error.exception.message}</li>
			</ul>
			</c:if>
	</div>
</body>
</html>