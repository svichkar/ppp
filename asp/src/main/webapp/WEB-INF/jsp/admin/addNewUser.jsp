<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Add User">
	<jsp:attribute name="content_area">
	<script src="<c:url value="/resources/js/form.js"/>"></script>
	<h1>Add User</h1>
		<h2>User:</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<form:form name="addUser" action="create-user" method="post" commandName="UserModel">
			<form:input type="hidden" path="userId"/>
			<form:label path="userName">Login (email):</form:label><br>
			<form:input type="text" path="userName" tooltip="User e-mail"/><br>
			<form:label path="password">Password:</form:label><br>
			<form:input type="password" path="password" tooltip="Password"/><br>
			<label for="confirm">Confirm Password:</label><br>
			<input name="confirm" id="confirm" type="password" tooltip="Confirm Password"/><br>
			<form:label path="role.roleName">Role:</form:label><br>
			<form:select path="role.roleName" tooltip="User role">
  				 <form:option value="NONE" label="--- Select ---"/>
  				 <form:options items="${roleList}" />
			</form:select><br>	
			<input type="submit" value="Save" style="width: 150px;"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>	
	</jsp:attribute>
</t:general>