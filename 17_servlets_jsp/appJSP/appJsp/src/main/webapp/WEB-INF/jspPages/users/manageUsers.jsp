<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
	<jsp:include page="/WEB-INF/jspPages/users/viewUsers.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
		<jsp:param name="users" value="${users}" />
		<jsp:param name="roles" value="${roles}" />
	</jsp:include>
	<div align="center">
		<br>
		<form>
			<input type="hidden" name="currentUser" value="${currentUser}"> 
			<button formmethod="get" formaction="create_user" name="createUserButton">Create user</button>
		</form>
	<p style="color:red">${message}</p>
	</div>
</body>
</html>