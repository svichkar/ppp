<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Update User">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a class="active" href="<c:url value="/users"/>">Users</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">
       <h2>Update User</h2>
       <form action="updateUser" method="post">
       <input type="hidden" name="user_id"
				value="<c:out value="${updateUser.userId}"/>">
       <table class="form-table">
       <tr>
					<td>Login : </td>
       <td><input type="text" name="updatedLogin"
						value="<c:out value="${updateUser.login}"/>" required></td>
				</tr>
		<tr>
		<td>Email : </td>
		<td><input type="text" name="updatedEmail"
						value="<c:out value="${updateUser.email}"/>" required></td>
				</tr>
				<tr>
		<td>Password : </td>
		<td><input type="text" name="updatedPassword"
						value="<c:out value="${updateUser.password}"/>" required></td>
				</tr>
		<tr>
		<td>Role : </td>
		<td><select name="updatedRole" size="1" required>
       <c:forEach var="role" items="${roles}">
       <c:choose>
       <c:when test="${role.roleId == updateUser.role.roleId}">
       <option selected value="<c:out value="${role.roleId}"/>"><c:out
												value="${role.roleName}" /></option>
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${role.roleId}"/>"><c:out
												value="${role.roleName}" /></option>
       </c:otherwise>
       </c:choose>
       </c:forEach>
			</select></td>
				</tr>
		</table>
       <br /><input type="submit" name="update" value="Update">
       </form>         
    </jsp:attribute>
</t:template>