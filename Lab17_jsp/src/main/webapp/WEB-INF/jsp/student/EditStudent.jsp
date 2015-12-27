<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Student">
	<jsp:attribute name="content_area">
		<h1>Edit Student</h1>
		<h2>Student:</h2>
		<form action="editStudent.do" method="post">
			<b>First Name:</b><br>
			<input type="text" name="firstName" value="${student.firstName}"><br>
			<b>Last Name:</b><br>
        	<input type="text" name="lastName" value="${student.lastName}"><br>
        	<b>Date Birthday:</b><br>
        	<input type="text" name = "bday" id="datepicker" value="${student.dateBirthday}"></input><br>
        	<b>Date Entry:</b><br>
        	<input type="text" name = "eday" id="datepicker" value="${student.dateEntry}"></input><br>
        	<b>Student Group:</b><br>
        	<select name="group">
        	<c:forEach var="group" items="${groups}">
        	<option value="${group.studentGroupName}"<c:if test="${group.studentGroupName eq student.studentGroup}"> selected</c:if>>${group.studentGroupName}</option>
        	</c:forEach>
        	</select><br>
			<b>Term:</b><br>
			<select name="term">
			<c:forEach var="item" items="${terms}">
			<option value="${item.alias}"<c:if test="${item.alias eq student.term}"> selected</c:if>>${item.alias}</option>
			</c:forEach>
			</select><br>
			<b>Status:</b><br>
        	<select name="status">
        	<c:forEach var="status" items="${statuses}">
        	<option value="${status.statusName}"<c:if test="${status.statusName eq student.status}"> selected</c:if>>${status.statusName}</option>
        	</c:forEach>
        	</select><br>
			<input type="hidden" name="studentId" value="${student.id}"><br>
			<input type="submit" value="Save">
		</form>	
	</jsp:attribute>
</t:generalManager>