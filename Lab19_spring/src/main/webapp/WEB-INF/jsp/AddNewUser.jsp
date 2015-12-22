<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Add User">
	<jsp:attribute name="content_area">
	<h1>Add User</h1>
		<h2>User:</h2>
		<form action="addNewUser.do" method="post">
		<b>Login:</b><br>
		<input type="text" name="login"><br>
		<b>Password:</b><br> <input type="text" name="password"><br>
		<b>E-mail:</b><br>
		<input type="email" name="email"><br>
		<b>Role:</b><br>
		<select name="role">
		<option>Select role</option>
		<option value="Administrator">Administrator</option>
		<option value="Manager">Manager</option>
		<option value="Teacher">Teacher</option>
		<option value="Student">Student</option>
		</select><br>
		<p>
				<input type="submit" value="Save">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</p>
		</form>	
</jsp:attribute>
</t:general>