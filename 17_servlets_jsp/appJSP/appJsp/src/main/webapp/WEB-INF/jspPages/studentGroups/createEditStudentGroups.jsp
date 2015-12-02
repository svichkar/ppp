<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
	<jsp:include page="/WEB-INF/jspPages/studentGroups/viewStudentGroups.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
		<jsp:param name="studentGroups" value="${studentGroups}" />
	</jsp:include>
	<div align="center">
		<br> 
		<br>
		<c:if test="${createOrEdit == 'create'}">
		<form action="create_studentGroup" method="post">
			<label>Group Name:</label> <br> 
			<input type="text" name="groupName" required> 
			<br><br>
			<input type="submit" name="createNewStudentGroup" value="Create student group">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
		<c:if test="${createOrEdit == 'edit'}">
		<form action="edit_studentGroup" method="post">
			<label>Group Name:</label> <br> 
			<input type="text" name="groupName" value="${groupToEdit.groupName}" required> 
			<br> 
			<br>
			<input type="hidden" name="studentGroupId" value="${groupToEdit.studentGroupId}">
			<input type="submit" name="saveStudentGroupUpdates" value="Save">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
	</div>
</body>
</html>