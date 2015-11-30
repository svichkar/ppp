<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Student Group">
	<jsp:attribute name="content_area">
	<h1>Edit Student Group</h1>
		<h2>Student Group:</h2>
		<form action="editStudentGroup.do" method="post">
		<b>Name:</b><br>
		<input type="text" name="studentGroupName" value="${studentGroup.studentGroupName}"><br>	
		<input type="hidden" name="studentGroupId" value="${studentGroup.id}"><br>	
		<p>
				<input type="submit" value="Save">
			</p>
		</form>	
</jsp:attribute>
</t:generalManager>