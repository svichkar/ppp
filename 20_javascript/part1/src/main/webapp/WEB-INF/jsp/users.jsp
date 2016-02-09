<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table id="main_table">
		<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Role</th>
				<th>Action</th>
				<th>
				<form action="userAdd" method="POST">
					<input type="hidden" name="action" value="Add"> 
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="submit" value="Add user">
				</form>
				</th>
		</tr>
		<c:forEach var="user" items="${users}">
		<form action="userEdit" method="POST">
		<tr>
			<td>
				<c:out value="${user.username}" />
			</td>
			<td><c:out value="${user.password}" /></td>
			<td>
				<select name="role_id">
					<c:forEach var="item" items="${roles}">
						<option value="${item.roleId}"
										<c:if test="${item.roleId eq user.role.roleId}">selected</c:if>>
										<c:out value="${item.rolename}"/>
						</option>
					</c:forEach>
				</select>
			</td>
			<td>
			<select name="action">
				<option name="edit">Edit</option>
				<option name="delete">Delete</option>
			</select>
			</td>
			<td>
				<input type="hidden" name="user_id" value="${user.userId}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Process">
			</td>
		</tr>
		</form>
		</c:forEach>
		</table>
	</jsp:attribute>
</t:general_template>