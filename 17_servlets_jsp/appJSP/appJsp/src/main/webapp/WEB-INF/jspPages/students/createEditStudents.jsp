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
	<jsp:include page="/WEB-INF/jspPages/students/viewStudents.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
		<jsp:param name="students" value="${students}" />
		<jsp:param name="groups" value="${groups}" />
		<jsp:param name="terms" value="${terms}" />
		<jsp:param name="statuses" value="${statuses}" />
	</jsp:include>
	<div align="center">
		<br> 
		<br>
		<c:if test="${createOrEdit == 'create'}">
		<form action="create_student" method="post">
			<label>First Name:</label> <br> 
			<input type="text" name="firstName" required> 
			<br> 
			<label>Last Name:</label> 
			<br> 
			<input type="text" name="lastName" required> 
			<br> 
			<label>Date Birthday:</label>
			<br>
			<input type="date" name="dateBirthday">
			<br>
			<label>Date Entry:</label>
			<br>
			<input type="date" name="dateEntry">
			<br>
			<label>Student Group:</label>
			<br>
			<select name="group">
				<c:forEach items="${groups}" var="group">
					<option value="${group.groupName}">${group.groupName}</option>
				</c:forEach>
			</select> 
			<br>
			<label>Status:</label> 
			<br>
			<select name="status">
				<c:forEach items="${statuses}" var="status">
					<option value="${status.statusName}">${status.statusName}</option>
				</c:forEach>
			</select>
			<br>
			<label>Term:</label> 
			<br>
			<select name="term">
				<c:forEach items="${terms}" var="term">
					<option value="${term.termAlias}">${term.termAlias}</option>
				</c:forEach>
			</select>
			<br><br>
			<input type="submit" name="createNewStudent" value="Create student">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
		<c:if test="${createOrEdit == 'edit'}">
		<form action="edit_student" method="post">
			<label>First Name:</label> <br> 
			<input type="text" name="firstName" value="${studentToEdit.firstName}" required> 
			<br> 
			<label>Last Name:</label> 
			<br> 
			<input type="text" name="lastName" value="${studentToEdit.lastName}" required> 
			<br> 
			<label>Date Birthday:</label>
			<br>
			<input type="date" name="dateBirthday" value="${studentToEdit.dateBirthday}">
			<br>
			<label>Date Entry:</label>
			<br>
			<input type="date" name="dateEntry" value="${studentToEdit.dateEntry}">
			<br>
			<label>Student Group:</label>
			<br>
			<select name="group">
				<c:forEach items="${groups}" var="group">
				<c:choose>
				<c:when test="${studentToEdit.studentGroupId == group.studentGroupId}">
				<option value="${group.groupName}" selected>${group.groupName}</option>
				</c:when>
				<c:otherwise>
				<option value="${group.groupName}">${group.groupName}</option>
				</c:otherwise>
				</c:choose>		
				</c:forEach>
			</select> 
			<br>
			<label>Status:</label>
			<br>
			<select name="status">
				<c:forEach items="${statuses}" var="status">
				<c:choose>
				<c:when test="${studentToEdit.statusId == status.statusId}">
				<option value="${status.statusName}" selected>${status.statusName}</option>
				</c:when>
				<c:otherwise>
				<option value="${status.statusName}">${status.statusName}</option>
				</c:otherwise>
				</c:choose>		
				</c:forEach>
			</select>
			<br>
			<label>Term:</label>
			<br>
			<select name="term">
				<c:forEach items="${terms}" var="term">
				<c:choose>
				<c:when test="${studentToEdit.termId == term.termId}">
				<option value="${term.termAlias}" selected>${term.termAlias}</option>
				</c:when>
				<c:otherwise>
				<option value="${term.termAlias}">${term.termAlias}</option>
				</c:otherwise>
				</c:choose>		
				</c:forEach>
			</select>  
			<br> 
			<br>
			<input type="hidden" name="studentId" value="${studentToEdit.studentId}">
			<input type="submit" name="saveStudentUpdates" value="Save">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
	</div>
</body>
</html>