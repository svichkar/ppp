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
	<jsp:include page="/WEB-INF/jspPages/homePage.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
	</jsp:include>
	<br><br><br>
	<div align="center">
		<table border="1">
			<caption>Users:</caption>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Email</th>
					<th>Password</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.userId}</td>
						<td>${user.email}</td>
						<td>${user.password}</td>
						<c:forEach items="${roles}" var="userRole">
							<c:if test="${user.roleId == userRole.roleId}">
								<td>${userRole.roleName}</td>
							</c:if>
						</c:forEach>
						<td>
						<form>
							<input type="hidden" name="currentUser" value="${currentUser}">
							<button formmethod="get" formaction="edit_user" name="editUser"
									value="${user.userId}" style="width: 100%">Edit</button>
							<br>
							<button formmethod="post" formaction="delete_user" name="delete"
									value="${user.userId}" style="width: 100%">Delete</button>
							</form>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>