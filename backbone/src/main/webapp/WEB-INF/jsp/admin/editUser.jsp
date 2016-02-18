<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<t:general title="Edit User">
	<jsp:attribute name="content_area">
	<script src="<c:url value="/resources/js/form.js"/>"></script>
	<h1>Edit User</h1>
		<h2>User:</h2>		
		<form:form name="addUser" action="update-user" method="post" commandName="UserModel">
			<form:input type="hidden" path="id" value="${user.id}"/>
			<form:label path="userName">Login (email):</form:label><br>			
			<form:input type="text" path="userName" value="${user.userName}" tooltip="User e-mail"/><br>
			<form:label path="password">Password:</form:label><br>
			<form:input type="password" path="password" value="${user.password}" tooltip="Password"/><br>
			<label for="confirm">Confirm Password:</label><br>
			<input id="confirm" name="confirm" type="password" value="${user.password}" tooltip="Confirm Password"/><br>
			<form:label path="role.roleName">Role:</form:label><br>
			<form:select path="role.roleName" tooltip="User role">
  				 <form:option value="${user.role.roleName}" label="${user.role.roleName}"/>
  				 <form:options items="${roleList}" />
			</form:select><br>
			<input type="submit" value="Save" style="width: 150px;"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>			
</jsp:attribute>
</t:general>