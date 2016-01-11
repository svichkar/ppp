<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
	<h1>Welcome to the Admin page</h1>
	<p>Hi, ${usrName}!</p>
	<table>
		<tr>
			<td>user_id</td>
			<td>user_name</td>
			<td>user_password</td>
			<td>user_role</td>
			<td>action</td>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<form id="update" action="createupdate" method="post">
					<td><input type="text" name="userid" value="${user.userId}"
						readonly /></td>
					<td><input type="text" name="username"
						value="${user.userName}" /></td>
					<td><input type="text" name="password"
						value="${user.userPassword}" /></td>
					<c:choose>
						<c:when test="${user.roleId==1}">
							<td><input type="text" name="selectrole" value="admin"
								readonly /></td>
						</c:when>
						<c:otherwise>
							<td><select name="selectrole">
									<c:forEach var="role" items="${roles}">
										<c:choose>
											<c:when test="${role.roleId == user.roleId}">
												<option selected value="${role.name}">${role.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${role.name}">${role.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${user.roleId==1}">
							<td><input type=submit value="edit user" name="button"></td>
						</c:when>
						<c:otherwise>
							<td><input type=submit value="edit user" name="button"></td>
							<td><input type=submit value="delete user" name="button"></td>
						</c:otherwise>
					</c:choose>
				</form>
			</tr>
		</c:forEach>
		<form id="create" action="createupdate" method="post">
			<tr>
				<td></td>
				<td><input type="text" name="username" /></td>
				<td><input type="text" name="password" /></td>
				<td><select name="selectrole">
						<option disabled selected>choose</option>
						<c:forEach var="role" items="${roles}">
							<option value="${role.name}">${role.name}</option>
						</c:forEach>
						<td><input type=submit value="create user" name="button"></td>
				</select></td>
			</tr>
		</form>
	</table>
</body>
</html>