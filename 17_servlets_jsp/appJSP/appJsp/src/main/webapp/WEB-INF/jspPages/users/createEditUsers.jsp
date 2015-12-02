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
		<br>
		<c:if test="${createOrEdit == 'create'}">
		<form action="create_user" method="post">
			<label>Email:</label> <br> 
			<input type="text" name="email" required> 
			<br> 
			<label>Password:</label> 
			<br> 
			<input type="text" name="password" required> 
			<br> 
			<label>Role:</label>
			<br>
			<select name="role">
				<c:forEach items="${roles}" var="role">
					<option value="${role.roleName}">${role.roleName}</option>
				</c:forEach>
			</select> 
			<br> 
			<br>
			<input type="submit" name="createNewUser" value="Create user">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
		<c:if test="${createOrEdit == 'edit'}">
		<form action="edit_user" method="post">
			<label>Email:</label> <br> 
			<input type="text" name="email" value="${userToEdit.email}" required> 
			<br> 
			<label>Password:</label> 
			<br> 
			<input type="text" name="password" value="${userToEdit.password}" required> 
			<br> 
			<label>Role:</label>
			<br>
			<select name="role">
				<c:forEach items="${roles}" var="role">
				<c:choose>
				<c:when test="${userToEdit.roleId == role.roleId}">
				<option value="${role.roleName}" selected>${role.roleName}</option>
				</c:when>
				<c:otherwise>
				<option value="${role.roleName}">${role.roleName}</option>
				</c:otherwise>
				</c:choose>		
				</c:forEach>
			</select> 
			<br> 
			<br>
			<input type="hidden" name="userId" value="${userToEdit.userId}">
			<input type="submit" name="saveUserUpdates" value="Save">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
	</div>
</body>
</html>