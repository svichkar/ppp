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
		<input type="text" name="login"><span class="error">${loginMessage}</span><br>
		<b>Password:</b><br> <input type="password" name="password"><br>
		<b>Confirm Password:</b><br> <input type="password" name="confirmPassword">
		<span class="error">${passwordMessage}</span><br>
		<b>E-mail:</b><br>
		<input type="email" name="email"><br>
		<b>Role:</b><br>
		<select name="role">
		<option>Select role</option>
		<option value="Admin">Administrator</option>
		<option value="Manager">Manager</option>
		<option value="Teacher">Teacher</option>
		<option value="Student">Student</option>
		</select><span class="error">${roleMessage}</span><br>
		<input type="submit" value="Save">
		</form>	
</jsp:attribute>
</t:general>