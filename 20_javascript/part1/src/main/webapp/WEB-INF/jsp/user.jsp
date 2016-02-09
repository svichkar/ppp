<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="userEdit" name="formForValidation" method="POST">
		<tr>
			<td>Username:</td>
			<td>
				<input type="text" name="username" value="${user.username}" />
			</td>
		</tr>
		<tr>
			<td>Password:</td>
			<td>
				<input type="text" name="password" value="${user.password}" />
			</td>
		</tr>
		<tr>
			<td>Role:</td>
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
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="user_id" value="${user.userId}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
		<div id="hint" />
	</jsp:attribute>
</t:general_template>