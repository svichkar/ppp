<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Edit User">
	<jsp:attribute name="content_area">
	<h1>Edit User</h1>
		<h2>User:</h2>
		<form action="editUser.do" method="post">
		<b>Login:</b><br>
		<input type="hidden" name="userId" value="${user.userId}"><br>
		<input type="text" name="login" value="${user.userName}" tooltip="Login"><br>
		<b>Password:</b><br>
		<input type="password" name="password" value="${user.password}" tooltip="Password"><br>
		<b>Confirm Password:</b>
		<br> <input id="confirmPassword" type="password" value="${user.password}" name="confirmPassword" tooltip="Confirm password"><br>
		<b>E-mail:</b><br>
		<input type="email" name="email" value="${user.email}" tooltip="User e-mail"><br>
		<b>Role:</b><br>
		<select name="role" tooltip="Choose a user role">
		<option value="${role.roleName}">${role.roleName}</option>
		<option value="Admin">Administrator</option>
		<option value="Manager">Manager</option>
		<option value="Teacher">Teacher</option>
		<option value="Student">Student</option>
		</select><br>
		<p><input type="submit" value="Save"></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>	
</jsp:attribute>
</t:general>