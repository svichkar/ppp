<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Add User">
	<jsp:attribute name="content_area">
	<h1>Add User</h1>
		<h2>User:</h2>
		<form:form action="create-user" method="post">
			<form:input type="hidden" path="userId"/>
			<form:label path="userName">Login (email):</form:label><br>
			<form:input type="text" path="userName"/><br>
			<form:label path="password">Password:</form:label><br>
			<form:input type="password" path="password"/><br>
			<form:label path="confirm">Confirm Password:</form:label><br>
			<input type="password" name="confirm"/><br>
			<form:label path="role.roleName">Role:</form:label><br>
			<form:select path="role.roleName">
  				 <form:option value="NONE" label="--- Select ---"/>
  				 <form:options items="${roleList}" />
			</form:select>		
			<input type="submit" value="Save">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>	
	</jsp:attribute>
</t:general>