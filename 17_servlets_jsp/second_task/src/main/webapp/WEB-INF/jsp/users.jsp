<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Users">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a class="active" href="<c:url value="/users"/>">Users</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">
	<div class="forms">
       <h2>Users</h2>
       <h3>Add User</h3>
       <form action="users" method="post">
       <table class="form-table">
       <tr>
					<td>Login : </td>
       <td><input type="text" name="newLogin" required></td>
				</tr>
       <tr>
					<td>Email : </td>
       <td><input type="text" name="newEmail" required></td>
				</tr>
       <tr>
					<td>Password : </td>
       <td><input type="password" name="newPassword" required></td>
				</tr>
       <tr>
					<td>Role : </td>
       <td><select name="newRole" size="1" required>
       <option selected disabled>--Select role--</option>
       <c:forEach var="role" items="${roles}">
       <option value="<c:out value="${role.roleId}"/>"><c:out
									value="${role.roleName}" /></option>
       </c:forEach></select></td>
				</tr>
       </table>
       <br /><input type="submit" name="add" value="Add">
       </form>
       </div> 
       <c:if test="${not empty param.message}">
       <div class="message-info" align="center">
				<c:out value="${param.message}" />
			</div>
       </c:if>      
       <h3>List of users</h3>       
        <table class="centered-table" border="1">
            <thead>
                <tr>
					<th>User ID</th>
					<th>Login</th>
					<th>Email</th>
					<th>Password</th>
					<th>Role</th>
					<th>Action</th>				
				</tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.user.userId}" /></td>
                    <td><c:out value="${user.user.login}" /></td>
                    <td><c:out value="${user.user.email}" /></td>
                    <td><c:out value="${user.user.password}" /></td>                    
                    <td><c:out value="${user.role.roleName}" /></td>                
                    <td><div class="action"><form action="updateUser" method="get">
                    <input type="hidden" name="user_id"
									value="<c:out value="${user.user.userId}"/>">
                    <input type="submit" name="edit" value="Edit">
							</form></div>
					<div class="action">
					<form action="users" method="post">
                    <input type="hidden" name="user_id"
									value="<c:out value="${user.user.userId}"/>">
                    <input type="submit" name="delete" value="Delete">
                    </form></div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>                            
    </jsp:attribute>
</t:template>