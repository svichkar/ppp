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
		<table border="1">
			<caption>Journal:</caption>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Student First Name</th>
					<th>Student Last Name</th>
					<th>Subject</th>
					<th>Rate</th>
				</tr>
				<c:forEach items="${journal}" var="journ">
					<tr>
						<td>${journ.journalId}</td>
						<td>${journ.student.firstName}</td>
						<td>${journ.student.lastName}</td>
						<td>${journ.subject.subjectName}</td>
						<td>${journ.rate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>