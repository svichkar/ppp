<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Student">
	<jsp:attribute name="content_area">
	<h1>Add Student</h1>
		<h2>Student:</h2>
		<form action="<c:url value="/students/addNewStudent.do"></c:url>" method="post">
		<b>First Name:</b><br>
		<input type="text" name="firstName"><br>
		<b>Last Name:</b><br>
        <input type="text" name="lastName"><br>
        <b>Date Birthday:</b><br>
        <input type="date" name="bday" id="datepicker"></input><br>
        <b>Date Entry:</b><br>
        <input type="date" name="eday" id="datepicker"></input><br>
        <b>Student Group:</b><br>
        <select name="group">
        <c:forEach var="item" items="${groups}">
        <option value="${item.studentGroupName}">${item.studentGroupName}</option>
        </c:forEach>
        </select><br>
		<b>Term:</b><br>
		<select name="term">
		<c:forEach var="item" items="${terms}">
		<option value="${item.alias}">${item.alias}</option>		
		</c:forEach>
		</select><br>
		<b>Status:</b><br>
        <select name="status">
        <c:forEach var="item" items="${statuses}">
        <option value="${item.statusName}">${item.statusName}</option>
        </c:forEach>
        </select><br>
		<p>
				<input type="submit" value="Save">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</p>
		</form>	
</jsp:attribute>
</t:generalManager>