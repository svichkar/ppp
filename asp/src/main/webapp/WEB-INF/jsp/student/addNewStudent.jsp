<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Student">
	<jsp:attribute name="content_area">
	<h1>Add Student</h1>
		<h2>Student:</h2>
		<form action="<c:url value="/students/add-new-student"></c:url>" method="post">
		<b>First Name:</b><br>
		<input type="text" name="firstName" tooltip="First Name of student"><br>
		<b>Last Name:</b><br>
        <input type="text" name="lastName" tooltip="Last Name of student"><br>
        <b>Date Birthday:</b><br>
        <input type="date" name="bday" id="bday" tooltip="Date Birthday of student (Example: 1990-02-02)"></input><br>
        <b>Date Entry:</b><br>
        <input type="date" name="eday" id="eday" tooltip="Date Entry of student (Example: 2015-07-07)"></input><br>
        <b>Student Group:</b><br>
        <select name="group" tooltip="Choose a student group">
        <c:forEach var="item" items="${groups}">
        <option value="${item.studentGroupName}">${item.studentGroupName}</option>
        </c:forEach>
        </select><br>
		<b>Term:</b><br>
		<select name="term" tooltip="Choose a term">
		<c:forEach var="item" items="${terms}">
		<option value="${item.alias}">${item.alias}</option>		
		</c:forEach>
		</select><br>
		<b>Status:</b><br>
        <select name="status" tooltip="Choose a status">
        <c:forEach var="item" items="${statuses}">
        <option value="${item.statusName}">${item.statusName}</option>
        </c:forEach>
        </select><br>
		<p><input type="submit" value="Save"></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>	
</jsp:attribute>
</t:generalManager>