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
	<jsp:include page="/WEB-INF/jspPages/subjects/viewSubjects.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
		<jsp:param name="subjects" value="${subjects}" />
	</jsp:include>
	<div align="center">
		<br> 
		<br>
		<c:if test="${createOrEdit == 'create'}">
		<form action="create_subject" method="post">
			<label>Subject Name:</label> <br> 
			<input type="text" name="subjectName" required> 
			<br>
			<select name="term">
				<c:forEach items="${terms}" var="term">
					<option value="${term.termAlias}">${term.termAlias}</option>
				</c:forEach>
			</select>
			<br><br>
			<input type="submit" name="createNewSubject" value="Create subject">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
		<c:if test="${createOrEdit == 'edit'}">
		<form action="edit_subject" method="post">
			<label>Subject Name:</label> <br> 
			<input type="text" name="subjectName" value="${subjectToEdit.subjectName}" required>
			<br>
			<label>Term:</label>
			<br>
			<select name="term">
				<c:forEach items="${terms}" var="term">
				<c:choose>
				<c:when test="${subjectToEdit.term.termId == term.termId}">
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
			<input type="hidden" name="subjectId" value="${subjectToEdit.subjectId}">
			<input type="submit" name="saveSubjectUpdates" value="Save">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
	</div>
</body>
</html>