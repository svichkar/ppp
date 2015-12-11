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
	<jsp:include page="/WEB-INF/jspPages/homePage.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
	</jsp:include>
	<br>
	<br>
	<br>
	<div align="center">
		<form method="get" action="search_students">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<input type="radio" name="radioSearch" value="SearchByFirstAndLastName">Search by first and last name
			<br><input type="text" name="searchByFirstName" placeholder="First Name">
			<input type="text" name="searchByLastName" placeholder="Last Name">
			<br>
			<input type="radio" name="radioSearch" value="SearchByGroup"> Search by group
			<br>
			<select name="searchByGroup">
				<option value=""></option>
				<c:forEach items="${groups}" var="group">
					<option value="${group.groupName}">${group.groupName}</option>
				</c:forEach>
			</select>
			<br><br> 
			<input type="submit" name="searchStudentsButton" value="Search">
		</form>
		<br> <br>
		<table border="1">
			<caption>Students:</caption>
			<tbody>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Date Birthday</th>
					<th>Date Entry</th>
					<th>Group</th>
					<th>Status</th>
					<th>Term</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${students}" var="student">
					<tr>
						<td>${student.studentId}</td>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.dateBirthday}</td>
						<td>${student.dateEntry}</td>
						<td>${student.studentGroup.groupName}</td>
						<td>${student.status.statusName}</td>
						<td>${student.term.termAlias}</td>
						<td>
							<form>
								<input type="hidden" name="currentUser" value="${currentUser}">
								<button formmethod="get" formaction="edit_student"
									name="editStudent" value="${student.studentId}"
									style="width: 100%">Edit</button>
								<br>
								<button formmethod="post" formaction="delete_student"
									name="delete" value="${student.studentId}" style="width: 100%">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>