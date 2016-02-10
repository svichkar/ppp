
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Manage users">
<jsp:attribute name="content_area">
<h2>Manage users</h2>
	<table class="users">
		<tr>
			<th class="users">user_id</th>
			<th class="users">user_name</th>
			<th class="users">user_password</th>
			<th class="users">user_role</th>
			<th class="users">action</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<form id="update" action="manageusers" onsubmit="return validate(this, addUserOpt)" method="post">
					<td class="users"><input type="text" name="userid" value="${user.userId}"
						readonly /></td>
					<td class="users"><input type="text" name="username"
						value="${user.userName}" data-tooltip="here my advice will be shown"/></td>
					<td class="users"><input type="text" name="password"
						value="${user.userPassword}" data-tooltip="here my advice will be shown"/></td>
					<c:choose>
						<c:when test="${user.role.roleId==1}">
							<td class="users"><input type="text" name="selectrole" value="admin"
								readonly /></td>
						</c:when>
						<c:otherwise>
							<td class="users"><select class="users" name="selectrole">
									<c:forEach var="role" items="${roles}">
										<c:choose>
											<c:when test="${role.roleId == user.role.roleId}">
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
						<c:when test="${user.role.roleId==1}">
							<td class="users"><input type="submit" value="edit user" name="button"></td>
						</c:when>
						<c:otherwise>
							<td class="users"><input type="submit" value="edit user" name="button"></td>
							<td class="users"><input class="users" type=submit value="delete user" name="button"></td>
						</c:otherwise>
					</c:choose>
				</form>
			</tr>
		</c:forEach>
		<form class="users" id="create" action="manageusers" onsubmit="return validate(this, addUserOpt)" method="post">
			<tr>
				<td class="users"></td>
				<td class="users"><input type="text" name="username" data-tooltip="here my advice will be shown"/></td> <!-- required -->
				<td class="users"><input type="text" name="password" data-tooltip="here my advice will be shown"/></td>
				<td class="users"><select class="users" name="selectrole" >
						<option selected disabled value="">choose</option>
						<c:forEach var="role" items="${roles}">
							<option value="${role.name}">${role.name}</option>
						</c:forEach>	
				</select></td>
				<td class="users"><input class="users" type=submit value="create user" name="button"></td>
			</tr>
		</form>
	</table>
	</jsp:attribute>
</t:general_template>