<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Student Group">
	<jsp:attribute name="content_area">
	<h1>Add Student Group</h1>
		<h2>Student Group:</h2>
		<form action="<c:url value="/studentGroups/addNewStudentGroup.do"></c:url>" method="post">
		<b>Student Group Name:</b><br>
		<input type="text" name="studentGroupName"><br>		
		<p><input type="submit" value="Save"></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>	
</jsp:attribute>
</t:generalManager>