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
	<div align="right">
		<form method="get" action="logout_do">
			<label>You are logged as ${currentUser} </label> <input type="submit"
				name="logout" value="Logout">
		</form>
	</div>
	<c:if test="${currentUser == 'admin'}">
		<div align="center">
			<form>
				<input type="hidden" name="currentUser" value="${currentUser}">
				<button formmethod="get" formaction="view_all_users">Admin
					tool</button>
			</form>
		</div>
	</c:if>
	<c:if test="${currentUser == 'manager'}">
		<div align="center">
			<form>
				<input type="hidden" name="currentUser" value="${currentUser}">
				<button formmethod="get" formaction="manage_students">Manage students</button>
				<button formmethod="get" formaction="manage_student_groups">Manage student groups</button>
				<button formmethod="get" formaction="manage_terms">Manage terms</button>
				<button formmethod="get" formaction="manage_subjects">Manage subjects</button>
				<button formmethod="get" formaction="manage_journal">Manage journal</button>
			</form>
		</div>
	</c:if>
</body>
</html>