<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Students">
	<jsp:attribute name="content_area">
<h1>Students</h1>
<h2>Search</h2>
<h3>Search by student</h3>
<form action="<c:url value="/students/students.do"></c:url>" method="post">
  First Name:
  <input type="text" name="firstName" value="">
  Last Name:
  <input type="text" name="lastName" value="">
  <input type="submit" value="Search" style="width: 100px;">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
 </form>
 <h3>Search by student group</h3>
 <form action="<c:url value="/students/students.do"></c:url>" method="post">
  Student Group:
  <select name="studentGroup">
  <c:forEach var="group" items="${groups}">
  <option value="${group.studentGroupName}">${group.studentGroupName}</option>
  </c:forEach>
  </select>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" value="Search" style="width: 100px;">
</form> 
<h2>Students</h2>
<table border="1">
		<tr>
			<th width=10%>Student Id</th>
			<th width=15%>First Name</th>
			<th width=15%>Last Name</th>
			<th width=10%>Date Birthday</th>
			<th width=10%>Date Entry</th>
			<th width=10%>Student Group</th>
			<th width=10%>Term</th>
			<th width=10%>Status</th>
			<th width=5%>Action</th>
			<th width=5%>Grades</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.studentId}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.dateBirthday}</td>
				<td>${student.dateEntry}</td>
				<td>${student.studentGroup.studentGroupName}</td>
				<td>${student.term.alias}</td>
				<td>${student.status.statusName}</td>
				<td>
					<form action="<c:url value="/students/editStudent.do"></c:url>" method="get">
						<input type=submit name="edit" value="Edit" style="width: 100%; height: 50%;">
						<input type="hidden" name="studentId" value="${student.studentId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<form action="<c:url value="/students/deleteStudent.do"></c:url>" method="post">							
						<input type=submit name="delete" value="Delete"	style="width: 100%; height: 50%;">
						<input type="hidden" name="studentId" value="${student.studentId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</td>
				<td>
                    <form action="<c:url value="/journal/journal.do"></c:url>" method="post">
                	    <input type=submit name="edit" value="Grades">
                	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                		<input type="hidden" name="studentId" value="${student.studentId}">
                	</form>
                </td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<form action="<c:url value="/students/addNewStudent.do"></c:url>" method="get">
	<input type=submit name="create" value="Add new" style="width: 100px;">
	</form>
</jsp:attribute>
</t:generalManager>