<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Handling example</title>
</head>
<body>
	<c:choose>
		<c:when test="${statusCode!='500'}">
			<h3>Servlet 3 Exception Handling</h3>
			<strong>Status Code</strong>:${statusCode}<br>
			<strong>Requested URI</strong>:${requestUri}
    </c:when>
		<c:otherwise>
			<h3>Servlet 3 Exception Handling</h3>
			<ul>
				<li>Servlet Name: ${servletName}</li>
				<li>Exception Name: ${exceptionName}</li>
				<li>Requested URI: ${requestUri}</li>
				<li>Exception Message: ${exceptionMessage}</li>
			</ul>
		</c:otherwise>
	</c:choose>
</body>
</html>