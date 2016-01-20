
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Manage users">
    <jsp:attribute name="content_area">
<h2>Manage users</h2>
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
				<form id="update" action="manageusers" method="post">
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
		<form id="create" action="manageusers" method="post">
			<tr>
				<td></td>
				<td><input type="text" name="username" required/></td>
				<td><input type="text" name="password" required/></td>
				<td><select name="selectrole" required>
						<option selected disabled value="">choose</option>
						<c:forEach var="role" items="${roles}">
							<option value="${role.name}">${role.name}</option>
						</c:forEach>
						<td><input type=submit value="create user" name="button"></td>
				</select></td>
			</tr>
		</form>
	</table>
	</jsp:attribute>
</t:general_template>