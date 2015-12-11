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
			<caption>Student Groups:</caption>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Group Name</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${studentGroups}" var="studentGroup">
					<tr>
						<td>${studentGroup.studentGroupId}</td>
						<td>${studentGroup.groupName}</td>
						<td>
							<form>
								<input type="hidden" name="currentUser" value="${currentUser}">
								<button formmethod="get" formaction="edit_studentGroup" name="editStudentGroup"
									value="${studentGroup.studentGroupId}" style="width: 100%">Edit</button>
								<br>
								<button formmethod="post" formaction="delete_studentGroup" name="delete"
									value="${studentGroup.studentGroupId}" style="width: 100%">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>