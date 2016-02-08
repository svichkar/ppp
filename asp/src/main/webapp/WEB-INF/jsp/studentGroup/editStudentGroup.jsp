<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Student Group">
	<jsp:attribute name="content_area">
	<h1>Edit Student Group</h1>
		<h2>Student Group:</h2>
		<form action="<c:url value="/studentGroups/editStudentGroup.do"></c:url>" method="post">
		<b>Name:</b><br>
		<input tooltip="Name of sudent group" type="text" name="studentGroupName" value="${studentGroup.studentGroupName}"><br>	
		<input type="hidden" name="studentGroupId" value="${studentGroup.studentGroupId}"><br>	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<p><input type="submit" value="Save"></p>
		</form>	
</jsp:attribute>
</t:generalManager>