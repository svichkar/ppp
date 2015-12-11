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
	<jsp:include page="/WEB-INF/jspPages/students/viewStudents.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
		<jsp:param name="students" value="${students}" />
		<jsp:param name="groups" value="${groups}" />
	</jsp:include>
	<div align="center">
		<br>
		<form>
			<input type="hidden" name="currentUser" value="${currentUser}"> 
			<button formmethod="get" formaction="create_student" name="createStudentButton">Create student</button>
		</form>
	<p style="color:red">${message}</p>
	</div>
</body>
</html>